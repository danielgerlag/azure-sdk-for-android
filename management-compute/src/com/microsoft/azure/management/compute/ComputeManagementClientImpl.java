/**
 * 
 * Copyright (c) Microsoft and contributors.  All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.azure.management.compute;

import com.microsoft.azure.AzureHttpStatus;
import com.microsoft.azure.Configuration;
import com.microsoft.azure.core.OperationStatus;
import com.microsoft.azure.core.OperationStatusResponse;
import com.microsoft.azure.core.ServiceClient;
import com.microsoft.azure.core.utils.BOMInputStream;
import com.microsoft.azure.credentials.SubscriptionCloudCredentials;
import com.microsoft.azure.exception.ServiceException;
import com.microsoft.azure.management.compute.models.CertificateFormat;
import com.microsoft.azure.management.compute.models.LoadBalancerProbeTransportProtocol;
import com.microsoft.azure.management.configuration.ManagementConfiguration;
import com.microsoft.azure.tracing.CloudTracing;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
* The Service Management API provides programmatic access to much of the
* functionality available through the Management Portal. The Service
* Management API is a REST API. All API operations are performed over SSL, and
* are mutually authenticated using X.509 v3 certificates.  (see
* http://msdn.microsoft.com/en-us/library/windowsazure/ee460799.aspx for more
* information)
*/
public class ComputeManagementClientImpl extends ServiceClient<ComputeManagementClient> implements ComputeManagementClient {
    private String apiVersion;
    
    /**
    * Gets the API version.
    * @return The ApiVersion value.
    */
    public String getApiVersion() {
        return this.apiVersion;
    }
    
    private URI baseUri;
    
    /**
    * Gets the URI used as the base for all cloud service requests.
    * @return The BaseUri value.
    */
    public URI getBaseUri() {
        return this.baseUri;
    }
    
    private SubscriptionCloudCredentials credentials;
    
    /**
    * Gets subscription credentials which uniquely identify Microsoft Azure
    * subscription. The subscription ID forms part of the URI for every
    * service call.
    * @return The Credentials value.
    */
    public SubscriptionCloudCredentials getCredentials() {
        return this.credentials;
    }
    
    private int longRunningOperationInitialTimeout;
    
    /**
    * Gets or sets the initial timeout for Long Running Operations.
    * @return The LongRunningOperationInitialTimeout value.
    */
    public int getLongRunningOperationInitialTimeout() {
        return this.longRunningOperationInitialTimeout;
    }
    
    /**
    * Gets or sets the initial timeout for Long Running Operations.
    * @param longRunningOperationInitialTimeoutValue The
    * LongRunningOperationInitialTimeout value.
    */
    public void setLongRunningOperationInitialTimeout(final int longRunningOperationInitialTimeoutValue) {
        this.longRunningOperationInitialTimeout = longRunningOperationInitialTimeoutValue;
    }
    
    private int longRunningOperationRetryTimeout;
    
    /**
    * Gets or sets the retry timeout for Long Running Operations.
    * @return The LongRunningOperationRetryTimeout value.
    */
    public int getLongRunningOperationRetryTimeout() {
        return this.longRunningOperationRetryTimeout;
    }
    
    /**
    * Gets or sets the retry timeout for Long Running Operations.
    * @param longRunningOperationRetryTimeoutValue The
    * LongRunningOperationRetryTimeout value.
    */
    public void setLongRunningOperationRetryTimeout(final int longRunningOperationRetryTimeoutValue) {
        this.longRunningOperationRetryTimeout = longRunningOperationRetryTimeoutValue;
    }
    
    private DeploymentOperations deployments;
    
    /**
    * The Service Management API includes operations for managing the
    * deployments in your subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460812.aspx for
    * more information)
    * @return The DeploymentsOperations value.
    */
    public DeploymentOperations getDeploymentsOperations() {
        return this.deployments;
    }
    
    private ExtensionImageOperations extensionImages;
    
    /**
    * The Service Management API includes operations for managing the service
    * and virtual machine extension images in your publisher subscription.
    * @return The ExtensionImagesOperations value.
    */
    public ExtensionImageOperations getExtensionImagesOperations() {
        return this.extensionImages;
    }
    
    private HostedServiceOperations hostedServices;
    
    /**
    * The Service Management API includes operations for managing the hosted
    * services beneath your subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460812.aspx for
    * more information)
    * @return The HostedServicesOperations value.
    */
    public HostedServiceOperations getHostedServicesOperations() {
        return this.hostedServices;
    }
    
    private LoadBalancerOperations loadBalancers;
    
    /**
    * The Compute Management API includes operations for managing the load
    * balancers for your subscription.
    * @return The LoadBalancersOperations value.
    */
    public LoadBalancerOperations getLoadBalancersOperations() {
        return this.loadBalancers;
    }
    
    private OperatingSystemOperations operatingSystems;
    
    /**
    * Operations for determining the version of the Azure Guest Operating
    * System on which your service is running.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ff684169.aspx for
    * more information)
    * @return The OperatingSystemsOperations value.
    */
    public OperatingSystemOperations getOperatingSystemsOperations() {
        return this.operatingSystems;
    }
    
    private ServiceCertificateOperations serviceCertificates;
    
    /**
    * Operations for managing service certificates for your subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee795178.aspx for
    * more information)
    * @return The ServiceCertificatesOperations value.
    */
    public ServiceCertificateOperations getServiceCertificatesOperations() {
        return this.serviceCertificates;
    }
    
    private VirtualMachineDiskOperations virtualMachineDisks;
    
    /**
    * The Service Management API includes operations for managing the disks in
    * your subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/jj157188.aspx for
    * more information)
    * @return The VirtualMachineDisksOperations value.
    */
    public VirtualMachineDiskOperations getVirtualMachineDisksOperations() {
        return this.virtualMachineDisks;
    }
    
    private VirtualMachineExtensionOperations virtualMachineExtensions;
    
    /**
    * The Service Management API includes operations for managing the virtual
    * machine extensions in your subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/jj157206.aspx for
    * more information)
    * @return The VirtualMachineExtensionsOperations value.
    */
    public VirtualMachineExtensionOperations getVirtualMachineExtensionsOperations() {
        return this.virtualMachineExtensions;
    }
    
    private VirtualMachineOperations virtualMachines;
    
    /**
    * The Service Management API includes operations for managing the virtual
    * machines in your subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/jj157206.aspx for
    * more information)
    * @return The VirtualMachinesOperations value.
    */
    public VirtualMachineOperations getVirtualMachinesOperations() {
        return this.virtualMachines;
    }
    
    private VirtualMachineOSImageOperations virtualMachineOSImages;
    
    /**
    * The Service Management API includes operations for managing the OS images
    * in your subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/jj157175.aspx for
    * more information)
    * @return The VirtualMachineOSImagesOperations value.
    */
    public VirtualMachineOSImageOperations getVirtualMachineOSImagesOperations() {
        return this.virtualMachineOSImages;
    }
    
    private VirtualMachineVMImageOperations virtualMachineVMImages;
    
    /**
    * The Service Management API includes operations for managing the virtual
    * machine templates in your subscription.
    * @return The VirtualMachineVMImagesOperations value.
    */
    public VirtualMachineVMImageOperations getVirtualMachineVMImagesOperations() {
        return this.virtualMachineVMImages;
    }
    
    /**
    * Initializes a new instance of the ComputeManagementClientImpl class.
    *
    * @param configuration The service configurations.
    * @param executorService The executor service.
    */
    public ComputeManagementClientImpl(Configuration configuration, ExecutorService executorService) {
        super(configuration, executorService);
        this.deployments = new DeploymentOperationsImpl(this);
        this.extensionImages = new ExtensionImageOperationsImpl(this);
        this.hostedServices = new HostedServiceOperationsImpl(this);
        this.loadBalancers = new LoadBalancerOperationsImpl(this);
        this.operatingSystems = new OperatingSystemOperationsImpl(this);
        this.serviceCertificates = new ServiceCertificateOperationsImpl(this);
        this.virtualMachineDisks = new VirtualMachineDiskOperationsImpl(this);
        this.virtualMachineExtensions = new VirtualMachineExtensionOperationsImpl(this);
        this.virtualMachines = new VirtualMachineOperationsImpl(this);
        this.virtualMachineOSImages = new VirtualMachineOSImageOperationsImpl(this);
        this.virtualMachineVMImages = new VirtualMachineVMImageOperationsImpl(this);
        
        if (configuration.getProperty(ManagementConfiguration.SUBSCRIPTION_CLOUD_CREDENTIALS) == null) {
            throw new NullPointerException("credentials");
        } else {
            this.credentials = ((SubscriptionCloudCredentials) configuration.getProperty(ManagementConfiguration.SUBSCRIPTION_CLOUD_CREDENTIALS));
        }
        if (configuration.getProperty("BaseUri") == null) {
            try {
                this.baseUri = new URI("https://management.core.windows.net");
            }
            catch (URISyntaxException ex) {
            }
        } else {
            this.baseUri = ((URI) configuration.getProperty("BaseUri"));
        }
    }
    
    /**
    * Initializes a new instance of the ComputeManagementClientImpl class.
    *
    * @param configuration The service configurations.
    * @param executorService The executor service.
    */
    protected ComputeManagementClientImpl newInstance(Configuration configuration, ExecutorService executorService) {
        return new ComputeManagementClientImpl(configuration, executorService);
    }
    
    /**
    * The Get Operation Status operation returns the status of the specified
    * operation. After calling an asynchronous operation, you can call Get
    * Operation Status to determine whether the operation has succeeded,
    * failed, or is still in progress.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460783.aspx for
    * more information)
    *
    * @param requestId Required. The request ID for the request you wish to
    * track. The request ID is returned in the x-ms-request-id response header
    * for every request.
    * @return The response body contains the status of the specified
    * asynchronous operation, indicating whether it has succeeded, is
    * inprogress, or has failed. Note that this status is distinct from the
    * HTTP status code returned for the Get Operation Status operation itself.
    * If the asynchronous operation succeeded, the response body includes the
    * HTTP status code for the successful request. If the asynchronous
    * operation failed, the response body includes the HTTP status code for
    * the failed request and error information regarding the failure.
    */
    @Override
    public Future<OperationStatusResponse> getOperationStatusAsync(final String requestId) {
        return this.getExecutorService().submit(new Callable<OperationStatusResponse>() { 
            @Override
            public OperationStatusResponse call() throws Exception {
                return getOperationStatus(requestId);
            }
         });
    }
    
    /**
    * The Get Operation Status operation returns the status of the specified
    * operation. After calling an asynchronous operation, you can call Get
    * Operation Status to determine whether the operation has succeeded,
    * failed, or is still in progress.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460783.aspx for
    * more information)
    *
    * @param requestId Required. The request ID for the request you wish to
    * track. The request ID is returned in the x-ms-request-id response header
    * for every request.
    * @throws MalformedURLException Thrown in case of an invalid request URL
    * @throws ProtocolException Thrown if invalid request method
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred
    * @throws XmlPullParserException This exception is thrown to signal XML
    * Pull Parser related faults.
    * @return The response body contains the status of the specified
    * asynchronous operation, indicating whether it has succeeded, is
    * inprogress, or has failed. Note that this status is distinct from the
    * HTTP status code returned for the Get Operation Status operation itself.
    * If the asynchronous operation succeeded, the response body includes the
    * HTTP status code for the successful request. If the asynchronous
    * operation failed, the response body includes the HTTP status code for
    * the failed request and error information regarding the failure.
    */
    @Override
    public OperationStatusResponse getOperationStatus(String requestId) throws MalformedURLException, ProtocolException, ServiceException, IOException, XmlPullParserException {
        // Validate
        if (requestId == null) {
            throw new NullPointerException("requestId");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace) {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("requestId", requestId);
            CloudTracing.enter(invocationId, this, "getOperationStatusAsync", tracingParameters);
        }
        
        // Construct URL
        String url = "/" + (this.getCredentials().getSubscriptionId() != null ? this.getCredentials().getSubscriptionId().trim() : "") + "/operations/" + requestId.trim();
        String baseUrl = this.getBaseUri().toString();
        // Trim '/' character from the end of baseUrl and beginning of url.
        if (baseUrl.charAt(baseUrl.length() - 1) == '/') {
            baseUrl = baseUrl.substring(0, (baseUrl.length() - 1) + 0);
        }
        if (url.charAt(0) == '/') {
            url = url.substring(1);
        }
        url = baseUrl + "/" + url;
        url = url.replace(" ", "%20");
        
        // Create HTTP transport objects
        URL serverAddress = new URL(url);
        HttpURLConnection httpRequest = ((HttpURLConnection) serverAddress.openConnection());
        httpRequest.setRequestMethod("GET");
        httpRequest.setDoInput(true);
        
        // Set Headers
        httpRequest.setRequestProperty("x-ms-version", "2014-05-01");
        
        // Set Credentials
        this.getCredentials().processRequest(httpRequest);
        
        // Send Request
        try {
            int statusCode = httpRequest.getResponseCode();
            if (statusCode != AzureHttpStatus.OK) {
                ServiceException ex = null;
                if (httpRequest.getResponseCode() < 400) {
                    ex = ServiceException.createFromXml(null, httpRequest.getResponseMessage(), httpRequest.getResponseCode(), httpRequest.getContentType(), httpRequest.getInputStream());
                } else {
                    ex = ServiceException.createFromXml(null, httpRequest.getResponseMessage(), httpRequest.getResponseCode(), httpRequest.getContentType(), httpRequest.getErrorStream());
                }
                if (shouldTrace) {
                    CloudTracing.error(invocationId, ex);
                }
                throw ex;
            }
            
            // Create Result
            OperationStatusResponse result = null;
            // Deserialize Response
            InputStream responseContent = httpRequest.getInputStream();
            result = new OperationStatusResponse();
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(new BOMInputStream(responseContent)));
            
            int eventType = xmlPullParser.getEventType();
            while ((eventType == XmlPullParser.END_DOCUMENT) != true) {
                if (eventType == XmlPullParser.START_TAG && "Operation".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                    while ((eventType == XmlPullParser.END_TAG && "Operation".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                        if (eventType == XmlPullParser.START_TAG && "ID".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "ID".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                String idInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    idInstance = xmlPullParser.getText();
                                    result.setId(idInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "Status".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "Status".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                OperationStatus statusInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    statusInstance = OperationStatus.valueOf(xmlPullParser.getText());
                                    result.setStatus(statusInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "HttpStatusCode".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "HttpStatusCode".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                Integer httpStatusCodeInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    httpStatusCodeInstance = Integer.valueOf(xmlPullParser.getText());
                                    result.setHttpStatusCode(httpStatusCodeInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "Error".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "Error".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                OperationStatusResponse.ErrorDetails errorInstance;
                                if (result.getError() == null) {
                                    errorInstance = new OperationStatusResponse.ErrorDetails();
                                    result.setError(errorInstance);
                                } else {
                                    errorInstance = result.getError();
                                }
                                
                                if (eventType == XmlPullParser.START_TAG && "Code".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                    while ((eventType == XmlPullParser.END_TAG && "Code".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                        String codeInstance;
                                        if (eventType == XmlPullParser.TEXT) {
                                            codeInstance = xmlPullParser.getText();
                                            errorInstance.setCode(codeInstance);
                                        }
                                        
                                        eventType = xmlPullParser.next();
                                    }
                                }
                                
                                if (eventType == XmlPullParser.START_TAG && "Message".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                    while ((eventType == XmlPullParser.END_TAG && "Message".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                        String messageInstance;
                                        if (eventType == XmlPullParser.TEXT) {
                                            messageInstance = xmlPullParser.getText();
                                            errorInstance.setMessage(messageInstance);
                                        }
                                        
                                        eventType = xmlPullParser.next();
                                    }
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        eventType = xmlPullParser.next();
                    }
                }
                
                eventType = xmlPullParser.next();
            }
            
            result.setStatusCode(statusCode);
            result.setRequestId(httpRequest.getHeaderField("x-ms-request-id"));
            
            if (shouldTrace) {
                CloudTracing.exit(invocationId, result);
            }
            return result;
        } finally {
            if (httpRequest != null) {
                httpRequest.disconnect();
            }
        }
    }
    
    /**
    * Parse enum values for type CertificateFormat.
    *
    * @param value The value to parse.
    * @return The enum value.
    */
     static CertificateFormat parseCertificateFormat(String value) {
        if ("pfx".equalsIgnoreCase(value)) {
            return CertificateFormat.Pfx;
        }
        if ("cer".equalsIgnoreCase(value)) {
            return CertificateFormat.Cer;
        }
        throw new IllegalArgumentException("value");
    }
    
    /**
    * Convert an enum of type CertificateFormat to a string.
    *
    * @param value The value to convert to a string.
    * @return The enum value as a string.
    */
     static String certificateFormatToString(CertificateFormat value) {
        if (value == CertificateFormat.Pfx) {
            return "pfx";
        }
        if (value == CertificateFormat.Cer) {
            return "cer";
        }
        throw new IllegalArgumentException("value");
    }
    
    /**
    * Parse enum values for type LoadBalancerProbeTransportProtocol.
    *
    * @param value The value to parse.
    * @return The enum value.
    */
     static LoadBalancerProbeTransportProtocol parseLoadBalancerProbeTransportProtocol(String value) {
        if ("tcp".equalsIgnoreCase(value)) {
            return LoadBalancerProbeTransportProtocol.Tcp;
        }
        if ("http".equalsIgnoreCase(value)) {
            return LoadBalancerProbeTransportProtocol.Http;
        }
        throw new IllegalArgumentException("value");
    }
    
    /**
    * Convert an enum of type LoadBalancerProbeTransportProtocol to a string.
    *
    * @param value The value to convert to a string.
    * @return The enum value as a string.
    */
     static String loadBalancerProbeTransportProtocolToString(LoadBalancerProbeTransportProtocol value) {
        if (value == LoadBalancerProbeTransportProtocol.Tcp) {
            return "tcp";
        }
        if (value == LoadBalancerProbeTransportProtocol.Http) {
            return "http";
        }
        throw new IllegalArgumentException("value");
    }
}
