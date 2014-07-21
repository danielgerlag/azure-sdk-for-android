/**
 * Copyright Microsoft Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.azure.management.compute;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.xmlpull.v1.XmlPullParserException;

import com.microsoft.azure.*;
import com.microsoft.azure.core.OperationResponse;
import com.microsoft.azure.core.utils.KeyStoreType;
import com.microsoft.azure.exception.ServiceException;
import com.microsoft.azure.management.configuration.*;
import com.microsoft.azure.management.*;
import com.microsoft.azure.management.models.LocationAvailableServiceNames;
import com.microsoft.azure.management.models.LocationsListResponse;
import com.microsoft.azure.management.storage.StorageManagementClient;
import com.microsoft.azure.management.storage.StorageManagementService;
import com.microsoft.azure.management.storage.models.StorageAccountCreateParameters;
import com.microsoft.azure.management.storage.models.StorageAccountGetKeysResponse;
import com.microsoft.azure.management.storage.models.StorageAccountGetResponse;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudPageBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

public abstract class ComputeManagementIntegrationTestBase extends TestCase {
    protected static String testVMPrefix = "aztst";
    protected static String testStoragePrefix = "aztst";
    protected static String testHostedServicePrefix = "azhst";
    protected static String storageAccountKey = "";
    protected static String vmLocation = "West US";
    protected static String blobhost = "";
    
    protected static ComputeManagementClient computeManagementClient;
    protected static StorageManagementClient storageManagementClient;
    protected static ManagementClient managementClient;

    protected static void createComputeManagementClient() throws Exception {
        Configuration config = createConfiguration();
        computeManagementClient = ComputeManagementService.create(config);
    }
    
    protected static void createStorageManagementClient() throws Exception {
        Configuration config = createConfiguration();
        storageManagementClient = StorageManagementService.create(config);
    }
    
    protected static void createManagementClient() throws Exception {
        Configuration config = createConfiguration();
        managementClient = ManagementService.create(config);
    }
   
    protected static Configuration createConfiguration() throws Exception {
        String baseUri = System.getenv(ManagementConfiguration.URI);
        return ManagementConfiguration.configure(
            baseUri != null ? new URI(baseUri) : null,
            "db1ab6f0-4769-4b27-930e-01e2ef9c123c",
            "/assets/clientcert.bks",
            "test123",
            KeyStoreType.fromString("bks")
        );
    }
    
    protected static String randomString(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i=0; i<length; i++) {
                stringBuilder.append((char)('a' + random.nextInt(26)));
        }
        return stringBuilder.toString();
    }
    
    protected static void createStorageAccount(String storageAccountName, String storageContainer) throws Exception {
        //String storageAccountCreateName = testStoragePrefix + randomString(10);
        String storageAccountLabel = storageAccountName + "Label1";

        //Arrange
        StorageAccountCreateParameters createParameters = new StorageAccountCreateParameters();
        //required
        createParameters.setName(storageAccountName);
        //required
        createParameters.setLabel(storageAccountLabel);
        //required if no affinity group has set
        createParameters.setLocation(vmLocation);

        //act
        OperationResponse operationResponse = storageManagementClient.getStorageAccountsOperations().create(createParameters); 

        //Assert
        Assert.assertEquals(200, operationResponse.getStatusCode());

        //use container inside storage account, needed for os image storage.
        StorageAccountGetKeysResponse storageAccountGetKeysResponse = storageManagementClient.getStorageAccountsOperations().getKeys(storageAccountName);
        storageAccountKey = storageAccountGetKeysResponse.getPrimaryKey();
        CloudBlobClient blobClient = createBlobClient(storageAccountName, storageAccountKey);
        CloudBlobContainer container = blobClient.getContainerReference(storageContainer);      
        container.createIfNotExists();

        //make sure it created and available, otherwise vm deployment will fail with storage/container still creating
        boolean found = false;
        while(found == false) {
            Iterable<CloudBlobContainer> listContainerResult = blobClient.listContainers(storageContainer);
            for (CloudBlobContainer item : listContainerResult) {
                blobhost =item.getUri().getHost();
                if (item.getName().contains(storageContainer) == true) {
                    blobhost =item.getUri().getHost();
                    found = true;
                }
            }

            if (found == false) {
                Thread.sleep(1000 * 30);
            }
            else {
                Thread.sleep(1000 * 60);
            }
        }
    }

    protected static CloudBlobClient createBlobClient(String storageAccountName, String storageAccountKey) throws InvalidKeyException, URISyntaxException {
        String storageconnectionstring = "DefaultEndpointsProtocol=http;AccountName="+ storageAccountName +";AccountKey=" + storageAccountKey;
        CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageconnectionstring);

        // Create the blob client
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
        return blobClient;
    }

    protected static void uploadFileToBlob(String storageAccountName, String storageContainer, String fileName, String filePath) throws InvalidKeyException, URISyntaxException, StorageException, InterruptedException, IOException {
        CloudBlobClient blobClient = createBlobClient(storageAccountName, storageAccountKey);
        CloudBlobContainer container = blobClient.getContainerReference(storageContainer);

        CloudPageBlob pageblob = container.getPageBlobReference(fileName);
        File source = new File(filePath + fileName);
        pageblob.upload(new FileInputStream(source), source.length());

        //make sure it created and available, otherwise vm deployment will fail with storage/container still creating
        boolean found = false;
        while(found == false) {
            // Loop over blobs within the container and output the URI to each of them
            for (ListBlobItem item : container.listBlobs()) {
                if (item.getUri().getPath().contains(fileName) == true) {
                    found = true;
                }
            }
  
            if (found == false) {
                Thread.sleep(1000 * 10);
            }
            else {
                Thread.sleep(1000 * 20);
            }
        }
    }

    protected static void getLocation() throws Exception {
        //has to be a location that support compute, storage, vm, some of the locations are not, need to find out the right one
        ArrayList<String> serviceName = new ArrayList<String>();
        serviceName.add(LocationAvailableServiceNames.COMPUTE);
        serviceName.add(LocationAvailableServiceNames.PERSISTENTVMROLE);
        serviceName.add(LocationAvailableServiceNames.STORAGE);

        LocationsListResponse locationsListResponse = managementClient.getLocationsOperations().list();
        for (LocationsListResponse.Location location : locationsListResponse) {
            ArrayList<String> availableServicelist = location.getAvailableServices();
            String locationName = location.getName();
            if ((availableServicelist.containsAll(serviceName) == true) && (locationName.contains("US") == true)) {                       
                vmLocation = locationName;
            }
        }
    }

    protected static void cleanBlob(String storageAccountName, String storageContainer) {
        // Create the blob client
        CloudBlobClient blobClient = null;
        try {
            blobClient = createBlobClient(storageAccountName, storageAccountKey);
        } catch (InvalidKeyException e) {
        } catch (URISyntaxException e) {
        }

        // Retrieve reference to a previously created container
        if (blobClient != null)
        {
            CloudBlobContainer container = null;
            try {
                container = blobClient.getContainerReference(storageContainer);
            } catch (URISyntaxException e) {
            } catch (StorageException e) {
            }

            try {
                container.breakLease(300);
            } catch (StorageException e) {
            }
            try {
                container.delete();
            } catch (StorageException e) {
            }
        }
    }

    protected static void cleanStorageAccount(String storageAccountName) {
        StorageAccountGetResponse storageAccountGetResponse = null;
        try {
            storageAccountGetResponse = storageManagementClient.getStorageAccountsOperations().get(storageAccountName); 
        } catch (ServiceException e) {
        } catch (XmlPullParserException e) {
        } catch (DatatypeConfigurationException e) {
        } catch (IOException e) {
        } catch (URISyntaxException e) {
        }

        if ((storageAccountGetResponse != null) && (storageAccountGetResponse.getStorageAccount().getName().contains(storageAccountName))) {
            OperationResponse operationResponse = null;
            try {
                operationResponse = storageManagementClient.getStorageAccountsOperations().delete(storageAccountName);
            } catch (IOException e) {
            } catch (ServiceException e) {
            }
            if (operationResponse != null) {
                Assert.assertEquals(200, operationResponse.getStatusCode());
            }
        }
    }
}