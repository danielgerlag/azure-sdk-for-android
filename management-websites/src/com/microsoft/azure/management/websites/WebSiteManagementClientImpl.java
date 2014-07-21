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

package com.microsoft.azure.management.websites;

import com.microsoft.azure.AzureHttpStatus;
import com.microsoft.azure.Configuration;
import com.microsoft.azure.core.OperationResponse;
import com.microsoft.azure.core.ServiceClient;
import com.microsoft.azure.core.datatype.DatatypeFactoryImpl;
import com.microsoft.azure.core.utils.BOMInputStream;
import com.microsoft.azure.credentials.SubscriptionCloudCredentials;
import com.microsoft.azure.exception.ServiceException;
import com.microsoft.azure.management.configuration.ManagementConfiguration;
import com.microsoft.azure.management.websites.models.WebSiteOperationStatus;
import com.microsoft.azure.management.websites.models.WebSiteOperationStatusResponse;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.xml.datatype.DatatypeConfigurationException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
* The Web Sites Management API provides a RESTful set of web services that
* interact with the Microsoft Azure Web Sites service to manage your web sites.
* The API has entities that capture the relationship between an end user and
* Microsoft Azure Web Sites service.  (see
* http://msdn.microsoft.com/en-us/library/windowsazure/dn166981.aspx for more
* information)
*/
public class WebSiteManagementClientImpl extends ServiceClient<WebSiteManagementClient> implements WebSiteManagementClient {
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
    
    private ServerFarmOperations serverFarms;
    
    /**
    * Operations for managing the server farm in a web space.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn194277.aspx for
    * more information)
    * @return The ServerFarmsOperations value.
    */
    public ServerFarmOperations getServerFarmsOperations() {
        return this.serverFarms;
    }
    
    private WebSiteOperations webSites;
    
    /**
    * Operations for managing the web sites in a web space.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn166981.aspx for
    * more information)
    * @return The WebSitesOperations value.
    */
    public WebSiteOperations getWebSitesOperations() {
        return this.webSites;
    }
    
    private WebSpaceOperations webSpaces;
    
    /**
    * Operations for managing web spaces beneath your subscription.
    * @return The WebSpacesOperations value.
    */
    public WebSpaceOperations getWebSpacesOperations() {
        return this.webSpaces;
    }
    
    /**
    * Initializes a new instance of the WebSiteManagementClientImpl class.
    *
    * @param configuration The service configurations.
    * @param executorService The executor service.
    */
    public WebSiteManagementClientImpl(Configuration configuration, ExecutorService executorService) {
        super(configuration, executorService);
        this.serverFarms = new ServerFarmOperationsImpl(this);
        this.webSites = new WebSiteOperationsImpl(this);
        this.webSpaces = new WebSpaceOperationsImpl(this);
        
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
    * Initializes a new instance of the WebSiteManagementClientImpl class.
    *
    * @param configuration The service configurations.
    * @param executorService The executor service.
    */
    protected WebSiteManagementClientImpl newInstance(Configuration configuration, ExecutorService executorService) {
        return new WebSiteManagementClientImpl(configuration, executorService);
    }
    
    /**
    * The Get Operation Status operation returns the status of the specified
    * operation. After calling a long-running operation, you can call Get
    * Operation Status to determine whether the operation has succeeded,
    * failed, timed out, or is still in progress.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460783.aspx for
    * more information)
    *
    * @param webSpaceName Required. The name of the webspace for the website
    * where the operation was targeted.
    * @param siteName Required. The name of the site where the operation was
    * targeted.
    * @param operationId Required. The operation ID for the operation you wish
    * to track. The operation ID is returned in the ID field in the body of
    * the response for long-running operations.
    * @return The response body contains the status of the specified
    * long-running operation, indicating whether it has succeeded, is
    * inprogress, has timed out, or has failed. Note that this status is
    * distinct from the HTTP status code returned for the Get Operation Status
    * operation itself. If the long-running operation failed, the response
    * body includes error information regarding the failure.
    */
    @Override
    public Future<WebSiteOperationStatusResponse> getOperationStatusAsync(final String webSpaceName, final String siteName, final String operationId) {
        return this.getExecutorService().submit(new Callable<WebSiteOperationStatusResponse>() { 
            @Override
            public WebSiteOperationStatusResponse call() throws Exception {
                return getOperationStatus(webSpaceName, siteName, operationId);
            }
         });
    }
    
    /**
    * The Get Operation Status operation returns the status of the specified
    * operation. After calling a long-running operation, you can call Get
    * Operation Status to determine whether the operation has succeeded,
    * failed, timed out, or is still in progress.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460783.aspx for
    * more information)
    *
    * @param webSpaceName Required. The name of the webspace for the website
    * where the operation was targeted.
    * @param siteName Required. The name of the site where the operation was
    * targeted.
    * @param operationId Required. The operation ID for the operation you wish
    * to track. The operation ID is returned in the ID field in the body of
    * the response for long-running operations.
    * @throws MalformedURLException Thrown in case of an invalid request URL
    * @throws ProtocolException Thrown if invalid request method
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred
    * @throws XmlPullParserException This exception is thrown to signal XML
    * Pull Parser related faults.
    * @throws DatatypeConfigurationException Invalid datatype configuration
    * @return The response body contains the status of the specified
    * long-running operation, indicating whether it has succeeded, is
    * inprogress, has timed out, or has failed. Note that this status is
    * distinct from the HTTP status code returned for the Get Operation Status
    * operation itself. If the long-running operation failed, the response
    * body includes error information regarding the failure.
    */
    @Override
    public WebSiteOperationStatusResponse getOperationStatus(String webSpaceName, String siteName, String operationId) throws MalformedURLException, ProtocolException, ServiceException, IOException, XmlPullParserException, DatatypeConfigurationException {
        // Validate
        if (webSpaceName == null) {
            throw new NullPointerException("webSpaceName");
        }
        if (siteName == null) {
            throw new NullPointerException("siteName");
        }
        if (operationId == null) {
            throw new NullPointerException("operationId");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace) {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("webSpaceName", webSpaceName);
            tracingParameters.put("siteName", siteName);
            tracingParameters.put("operationId", operationId);
            CloudTracing.enter(invocationId, this, "getOperationStatusAsync", tracingParameters);
        }
        
        // Construct URL
        String url = "/" + (this.getCredentials().getSubscriptionId() != null ? this.getCredentials().getSubscriptionId().trim() : "") + "/services/WebSpaces/" + webSpaceName.trim() + "/sites/" + siteName.trim() + "/operations/" + operationId.trim();
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
        httpRequest.setRequestProperty("x-ms-version", "2013-08-01");
        
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
            WebSiteOperationStatusResponse result = null;
            // Deserialize Response
            InputStream responseContent = httpRequest.getInputStream();
            result = new WebSiteOperationStatusResponse();
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(new BOMInputStream(responseContent)));
            
            int eventType = xmlPullParser.getEventType();
            while ((eventType == XmlPullParser.END_DOCUMENT) != true) {
                if (eventType == XmlPullParser.START_TAG && "Operation".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                    while ((eventType == XmlPullParser.END_TAG && "Operation".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                        if (eventType == XmlPullParser.START_TAG && "CreatedTime".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "CreatedTime".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                Calendar createdTimeInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    createdTimeInstance = DatatypeFactoryImpl.newInstance().newXMLGregorianCalendar(xmlPullParser.getText()).toGregorianCalendar();
                                    result.setCreatedTime(createdTimeInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "Errors".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "Errors".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                if (eventType == XmlPullParser.START_TAG && "Error".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                    WebSiteOperationStatusResponse.Error errorInstance;
                                    errorInstance = new WebSiteOperationStatusResponse.Error();
                                    result.getErrors().add(errorInstance);
                                    
                                    while ((eventType == XmlPullParser.END_TAG && "Error".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
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
                                        
                                        if (eventType == XmlPullParser.START_TAG && "ExtendedCode".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                            while ((eventType == XmlPullParser.END_TAG && "ExtendedCode".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                                String extendedCodeInstance;
                                                if (eventType == XmlPullParser.TEXT) {
                                                    extendedCodeInstance = xmlPullParser.getText();
                                                    errorInstance.setExtendedCode(extendedCodeInstance);
                                                }
                                                
                                                eventType = xmlPullParser.next();
                                            }
                                        }
                                        
                                        if (eventType == XmlPullParser.START_TAG && "MessageTemplate".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                            while ((eventType == XmlPullParser.END_TAG && "MessageTemplate".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                                String messageTemplateInstance;
                                                if (eventType == XmlPullParser.TEXT) {
                                                    messageTemplateInstance = xmlPullParser.getText();
                                                    errorInstance.setMessageTemplate(messageTemplateInstance);
                                                }
                                                
                                                eventType = xmlPullParser.next();
                                            }
                                        }
                                        
                                        if (eventType == XmlPullParser.START_TAG && "Parameters".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                            while ((eventType == XmlPullParser.END_TAG && "Parameters".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                                if (eventType == XmlPullParser.TEXT) {
                                                    errorInstance.getParameters().add(xmlPullParser.getText());
                                                }
                                                
                                                eventType = xmlPullParser.next();
                                            }
                                        }
                                        
                                        if (eventType == XmlPullParser.START_TAG && "InnerErrors".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                                            while ((eventType == XmlPullParser.END_TAG && "InnerErrors".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                                String innerErrorsInstance;
                                                if (eventType == XmlPullParser.TEXT) {
                                                    innerErrorsInstance = xmlPullParser.getText();
                                                    errorInstance.setInnerErrors(innerErrorsInstance);
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
                        
                        if (eventType == XmlPullParser.START_TAG && "ExpirationTime".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "ExpirationTime".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                Calendar expirationTimeInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    expirationTimeInstance = DatatypeFactoryImpl.newInstance().newXMLGregorianCalendar(xmlPullParser.getText()).toGregorianCalendar();
                                    result.setExpirationTime(expirationTimeInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "GeoMasterOperationId".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "GeoMasterOperationId".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                String geoMasterOperationIdInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    geoMasterOperationIdInstance = xmlPullParser.getText();
                                    result.setGeoMasterOperationId(geoMasterOperationIdInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "Id".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "Id".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                String idInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    idInstance = xmlPullParser.getText();
                                    result.setOperationId(idInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "ModifiedTime".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "ModifiedTime".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                Calendar modifiedTimeInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    modifiedTimeInstance = DatatypeFactoryImpl.newInstance().newXMLGregorianCalendar(xmlPullParser.getText()).toGregorianCalendar();
                                    result.setModifiedTime(modifiedTimeInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "Name".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "Name".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                String nameInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    nameInstance = xmlPullParser.getText();
                                    result.setName(nameInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "Status".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "Status".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                WebSiteOperationStatus statusInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    statusInstance = WebSiteOperationStatus.valueOf(xmlPullParser.getText());
                                    result.setStatus(statusInstance);
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
    * Register your subscription to use Azure Web Sites.
    *
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<OperationResponse> registerSubscriptionAsync() {
        return this.getExecutorService().submit(new Callable<OperationResponse>() { 
            @Override
            public OperationResponse call() throws Exception {
                return registerSubscription();
            }
         });
    }
    
    /**
    * Register your subscription to use Azure Web Sites.
    *
    * @throws MalformedURLException Thrown in case of an invalid request URL
    * @throws ProtocolException Thrown if invalid request method
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public OperationResponse registerSubscription() throws MalformedURLException, ProtocolException, ServiceException, IOException {
        // Validate
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace) {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            CloudTracing.enter(invocationId, this, "registerSubscriptionAsync", tracingParameters);
        }
        
        // Construct URL
        String url = "/" + (this.getCredentials().getSubscriptionId() != null ? this.getCredentials().getSubscriptionId().trim() : "") + "/services" + "?";
        url = url + "service=website";
        url = url + "&" + "action=register";
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
        httpRequest.setRequestMethod("PUT");
        httpRequest.setDoOutput(true);
        
        // Set Headers
        httpRequest.setRequestProperty("Content-Type", "application/xml");
        httpRequest.setRequestProperty("x-ms-version", "2013-08-01");
        
        // Set Credentials
        this.getCredentials().processRequest(httpRequest);
        
        // Send Request
        try {
            httpRequest.setFixedLengthStreamingMode(0);
            int statusCode = httpRequest.getResponseCode();
            if (statusCode != AzureHttpStatus.ACCEPTED) {
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
            OperationResponse result = null;
            result = new OperationResponse();
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
    * Unregister your subscription to use Azure Web Sites.
    *
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<OperationResponse> unregisterSubscriptionAsync() {
        return this.getExecutorService().submit(new Callable<OperationResponse>() { 
            @Override
            public OperationResponse call() throws Exception {
                return unregisterSubscription();
            }
         });
    }
    
    /**
    * Unregister your subscription to use Azure Web Sites.
    *
    * @throws MalformedURLException Thrown in case of an invalid request URL
    * @throws ProtocolException Thrown if invalid request method
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public OperationResponse unregisterSubscription() throws MalformedURLException, ProtocolException, ServiceException, IOException {
        // Validate
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace) {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            CloudTracing.enter(invocationId, this, "unregisterSubscriptionAsync", tracingParameters);
        }
        
        // Construct URL
        String url = "/" + (this.getCredentials().getSubscriptionId() != null ? this.getCredentials().getSubscriptionId().trim() : "") + "/services" + "?";
        url = url + "service=website";
        url = url + "&" + "action=unregister";
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
        httpRequest.setRequestMethod("PUT");
        httpRequest.setDoOutput(true);
        
        // Set Headers
        httpRequest.setRequestProperty("Content-Type", "application/xml");
        httpRequest.setRequestProperty("x-ms-version", "2013-08-01");
        
        // Set Credentials
        this.getCredentials().processRequest(httpRequest);
        
        // Send Request
        try {
            httpRequest.setFixedLengthStreamingMode(0);
            int statusCode = httpRequest.getResponseCode();
            if (statusCode != AzureHttpStatus.ACCEPTED) {
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
            OperationResponse result = null;
            result = new OperationResponse();
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
