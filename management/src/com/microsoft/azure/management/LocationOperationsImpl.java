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

package com.microsoft.azure.management;

import com.microsoft.azure.AzureHttpStatus;
import com.microsoft.azure.core.ServiceOperations;
import com.microsoft.azure.core.utils.BOMInputStream;
import com.microsoft.azure.exception.ServiceException;
import com.microsoft.azure.management.models.ComputeCapabilities;
import com.microsoft.azure.management.models.LocationsListResponse;
import com.microsoft.azure.tracing.CloudTracing;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.xml.datatype.DatatypeConfigurationException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
* The Service Management API includes operations for listing the available data
* center locations for a hosted service in your subscription.  (see
* http://msdn.microsoft.com/en-us/library/windowsazure/gg441299.aspx for more
* information)
*/
public class LocationOperationsImpl implements ServiceOperations<ManagementClientImpl>, LocationOperations {
    /**
    * Initializes a new instance of the LocationOperationsImpl class.
    *
    * @param client Reference to the service client.
    */
    LocationOperationsImpl(ManagementClientImpl client) {
        this.client = client;
    }
    
    private ManagementClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.management.ManagementClientImpl.
    * @return The Client value.
    */
    public ManagementClientImpl getClient() {
        return this.client;
    }
    
    /**
    * The List Locations operation lists all of the data center locations that
    * are valid for your subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg441293.aspx for
    * more information)
    *
    * @return The List Locations operation response.
    */
    @Override
    public Future<LocationsListResponse> listAsync() {
        return this.getClient().getExecutorService().submit(new Callable<LocationsListResponse>() { 
            @Override
            public LocationsListResponse call() throws Exception {
                return list();
            }
         });
    }
    
    /**
    * The List Locations operation lists all of the data center locations that
    * are valid for your subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg441293.aspx for
    * more information)
    *
    * @throws MalformedURLException Thrown in case of an invalid request URL
    * @throws ProtocolException Thrown if invalid request method
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred
    * @throws XmlPullParserException This exception is thrown to signal XML
    * Pull Parser related faults.
    * @throws DatatypeConfigurationException Invalid datatype configuration
    * @return The List Locations operation response.
    */
    @Override
    public LocationsListResponse list() throws MalformedURLException, ProtocolException, ServiceException, IOException, XmlPullParserException, DatatypeConfigurationException {
        // Validate
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace) {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            CloudTracing.enter(invocationId, this, "listAsync", tracingParameters);
        }
        
        // Construct URL
        String url = "/" + (this.getClient().getCredentials().getSubscriptionId() != null ? this.getClient().getCredentials().getSubscriptionId().trim() : "") + "/locations";
        String baseUrl = this.getClient().getBaseUri().toString();
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
        this.getClient().getCredentials().processRequest(httpRequest);
        
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
            LocationsListResponse result = null;
            // Deserialize Response
            InputStream responseContent = httpRequest.getInputStream();
            result = new LocationsListResponse();
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(new BOMInputStream(responseContent)));
            
            int eventType = xmlPullParser.getEventType();
            while ((eventType == XmlPullParser.END_DOCUMENT) != true) {
                if (eventType == XmlPullParser.START_TAG && "Locations".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                    while ((eventType == XmlPullParser.END_TAG && "Locations".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                        if (eventType == XmlPullParser.START_TAG && "Location".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            LocationsListResponse.Location locationInstance;
                            locationInstance = new LocationsListResponse.Location();
                            result.getLocations().add(locationInstance);
                            
                            while ((eventType == XmlPullParser.END_TAG && "Location".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                if (eventType == XmlPullParser.START_TAG && "Name".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                    while ((eventType == XmlPullParser.END_TAG && "Name".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                        String nameInstance;
                                        if (eventType == XmlPullParser.TEXT) {
                                            nameInstance = xmlPullParser.getText();
                                            locationInstance.setName(nameInstance);
                                        }
                                        
                                        eventType = xmlPullParser.next();
                                    }
                                }
                                
                                if (eventType == XmlPullParser.START_TAG && "DisplayName".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                    while ((eventType == XmlPullParser.END_TAG && "DisplayName".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                        String displayNameInstance;
                                        if (eventType == XmlPullParser.TEXT) {
                                            displayNameInstance = xmlPullParser.getText();
                                            locationInstance.setDisplayName(displayNameInstance);
                                        }
                                        
                                        eventType = xmlPullParser.next();
                                    }
                                }
                                
                                if (eventType == XmlPullParser.START_TAG && "AvailableServices".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                    while ((eventType == XmlPullParser.END_TAG && "AvailableServices".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                        if (eventType == XmlPullParser.TEXT) {
                                            locationInstance.getAvailableServices().add(xmlPullParser.getText());
                                        }
                                        
                                        eventType = xmlPullParser.next();
                                    }
                                }
                                
                                if (eventType == XmlPullParser.START_TAG && "ComputeCapabilities".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                    while ((eventType == XmlPullParser.END_TAG && "ComputeCapabilities".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                        ComputeCapabilities computeCapabilitiesInstance;
                                        if (locationInstance.getComputeCapabilities() == null) {
                                            computeCapabilitiesInstance = new ComputeCapabilities();
                                            locationInstance.setComputeCapabilities(computeCapabilitiesInstance);
                                        } else {
                                            computeCapabilitiesInstance = locationInstance.getComputeCapabilities();
                                        }
                                        
                                        if (eventType == XmlPullParser.START_TAG && "VirtualMachinesRoleSizes".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                            while ((eventType == XmlPullParser.END_TAG && "VirtualMachinesRoleSizes".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                                if (eventType == XmlPullParser.TEXT) {
                                                    computeCapabilitiesInstance.getVirtualMachinesRoleSizes().add(xmlPullParser.getText());
                                                }
                                                
                                                eventType = xmlPullParser.next();
                                            }
                                        }
                                        
                                        if (eventType == XmlPullParser.START_TAG && "WebWorkerRoleSizes".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                            while ((eventType == XmlPullParser.END_TAG && "WebWorkerRoleSizes".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                                if (eventType == XmlPullParser.TEXT) {
                                                    computeCapabilitiesInstance.getWebWorkerRoleSizes().add(xmlPullParser.getText());
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
}
