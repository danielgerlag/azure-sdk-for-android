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

package com.microsoft.azure.management.sql;

import android.util.Xml;
import com.microsoft.azure.AzureHttpStatus;
import com.microsoft.azure.core.ServiceOperations;
import com.microsoft.azure.core.datatype.DatatypeFactoryImpl;
import com.microsoft.azure.core.utils.BOMInputStream;
import com.microsoft.azure.exception.ServiceException;
import com.microsoft.azure.management.sql.models.RestoreDatabaseOperation;
import com.microsoft.azure.management.sql.models.RestoreDatabaseOperationCreateParameters;
import com.microsoft.azure.management.sql.models.RestoreDatabaseOperationCreateResponse;
import com.microsoft.azure.tracing.CloudTracing;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.xml.datatype.DatatypeConfigurationException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/**
* Contains the operation to create restore requests for Azure SQL Databases.
*/
public class RestoreDatabaseOperationsImpl implements ServiceOperations<SqlManagementClientImpl>, RestoreDatabaseOperations {
    /**
    * Initializes a new instance of the RestoreDatabaseOperationsImpl class.
    *
    * @param client Reference to the service client.
    */
    RestoreDatabaseOperationsImpl(SqlManagementClientImpl client) {
        this.client = client;
    }
    
    private SqlManagementClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.management.sql.SqlManagementClientImpl.
    * @return The Client value.
    */
    public SqlManagementClientImpl getClient() {
        return this.client;
    }
    
    /**
    * Issues a restore request for an Azure SQL Database.
    *
    * @param sourceServerName Required. The name of the Azure SQL Database
    * Server where the source database is, or was, hosted.
    * @param parameters Required. Additional parameters for the Create Restore
    * Database Operation request.
    * @return Contains the response to the Create Restore Database Operation
    * request.
    */
    @Override
    public Future<RestoreDatabaseOperationCreateResponse> createAsync(final String sourceServerName, final RestoreDatabaseOperationCreateParameters parameters) {
        return this.getClient().getExecutorService().submit(new Callable<RestoreDatabaseOperationCreateResponse>() { 
            @Override
            public RestoreDatabaseOperationCreateResponse call() throws Exception {
                return create(sourceServerName, parameters);
            }
         });
    }
    
    /**
    * Issues a restore request for an Azure SQL Database.
    *
    * @param sourceServerName Required. The name of the Azure SQL Database
    * Server where the source database is, or was, hosted.
    * @param parameters Required. Additional parameters for the Create Restore
    * Database Operation request.
    * @throws MalformedURLException Thrown in case of an invalid request URL
    * @throws ProtocolException Thrown if invalid request method
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred
    * @throws XmlPullParserException This exception is thrown to signal XML
    * Pull Parser related faults.
    * @throws DatatypeConfigurationException Invalid datatype configuration
    * @return Contains the response to the Create Restore Database Operation
    * request.
    */
    @Override
    public RestoreDatabaseOperationCreateResponse create(String sourceServerName, RestoreDatabaseOperationCreateParameters parameters) throws MalformedURLException, ProtocolException, ServiceException, IOException, XmlPullParserException, DatatypeConfigurationException {
        // Validate
        if (sourceServerName == null) {
            throw new NullPointerException("sourceServerName");
        }
        if (parameters == null) {
            throw new NullPointerException("parameters");
        }
        if (parameters.getSourceDatabaseName() == null) {
            throw new NullPointerException("parameters.SourceDatabaseName");
        }
        if (parameters.getTargetDatabaseName() == null) {
            throw new NullPointerException("parameters.TargetDatabaseName");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace) {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("sourceServerName", sourceServerName);
            tracingParameters.put("parameters", parameters);
            CloudTracing.enter(invocationId, this, "createAsync", tracingParameters);
        }
        
        // Construct URL
        String url = "/" + (this.getClient().getCredentials().getSubscriptionId() != null ? this.getClient().getCredentials().getSubscriptionId().trim() : "") + "/services/sqlservers/servers/" + sourceServerName.trim() + "/restoredatabaseoperations";
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
        httpRequest.setRequestMethod("POST");
        httpRequest.setDoOutput(true);
        
        // Set Headers
        httpRequest.setRequestProperty("Content-Type", "application/xml");
        httpRequest.setRequestProperty("x-ms-version", "2012-03-01");
        
        // Set Credentials
        this.getClient().getCredentials().processRequest(httpRequest);
        
        // Serialize Request
        String requestContent = null;
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();
        xmlSerializer.setOutput(stringWriter);
        xmlSerializer.startDocument("UTF-8", true);
        
        xmlSerializer.startTag("http://schemas.microsoft.com/windowsazure", "ServiceResource");
        
        xmlSerializer.startTag("http://schemas.microsoft.com/windowsazure", "SourceDatabaseName");
        xmlSerializer.text(parameters.getSourceDatabaseName());
        xmlSerializer.endTag("http://schemas.microsoft.com/windowsazure", "SourceDatabaseName");
        
        if (parameters.getSourceDatabaseDeletionDate() != null) {
            xmlSerializer.startTag("http://schemas.microsoft.com/windowsazure", "SourceDatabaseDeletionDate");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            xmlSerializer.text(simpleDateFormat.format(parameters.getSourceDatabaseDeletionDate().getTime()));
            xmlSerializer.endTag("http://schemas.microsoft.com/windowsazure", "SourceDatabaseDeletionDate");
        }
        
        if (parameters.getTargetServerName() != null) {
            xmlSerializer.startTag("http://schemas.microsoft.com/windowsazure", "TargetServerName");
            xmlSerializer.text(parameters.getTargetServerName());
            xmlSerializer.endTag("http://schemas.microsoft.com/windowsazure", "TargetServerName");
        }
        
        xmlSerializer.startTag("http://schemas.microsoft.com/windowsazure", "TargetDatabaseName");
        xmlSerializer.text(parameters.getTargetDatabaseName());
        xmlSerializer.endTag("http://schemas.microsoft.com/windowsazure", "TargetDatabaseName");
        
        if (parameters.getPointInTime() != null) {
            xmlSerializer.startTag("http://schemas.microsoft.com/windowsazure", "TargetUtcPointInTime");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
            xmlSerializer.text(simpleDateFormat2.format(parameters.getPointInTime().getTime()));
            xmlSerializer.endTag("http://schemas.microsoft.com/windowsazure", "TargetUtcPointInTime");
        }
        xmlSerializer.endTag("http://schemas.microsoft.com/windowsazure", "ServiceResource");
        xmlSerializer.endDocument();
        
        requestContent = stringWriter.toString();
        httpRequest.setRequestProperty("Content-Type", "application/xml");
        
        // Send Request
        try {
            httpRequest.setFixedLengthStreamingMode(requestContent.getBytes().length);
            OutputStream outputStream = httpRequest.getOutputStream();
            outputStream.write(requestContent.getBytes());
            outputStream.close();
            int statusCode = httpRequest.getResponseCode();
            if (statusCode != AzureHttpStatus.CREATED) {
                ServiceException ex = null;
                if (httpRequest.getResponseCode() < 400) {
                    ex = ServiceException.createFromXml(requestContent, httpRequest.getResponseMessage(), httpRequest.getResponseCode(), httpRequest.getContentType(), httpRequest.getInputStream());
                } else {
                    ex = ServiceException.createFromXml(requestContent, httpRequest.getResponseMessage(), httpRequest.getResponseCode(), httpRequest.getContentType(), httpRequest.getErrorStream());
                }
                if (shouldTrace) {
                    CloudTracing.error(invocationId, ex);
                }
                throw ex;
            }
            
            // Create Result
            RestoreDatabaseOperationCreateResponse result = null;
            // Deserialize Response
            InputStream responseContent = httpRequest.getInputStream();
            result = new RestoreDatabaseOperationCreateResponse();
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(new BOMInputStream(responseContent)));
            
            int eventType = xmlPullParser.getEventType();
            while ((eventType == XmlPullParser.END_DOCUMENT) != true) {
                if (eventType == XmlPullParser.START_TAG && "ServiceResource".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                    while ((eventType == XmlPullParser.END_TAG && "ServiceResource".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                        RestoreDatabaseOperation serviceResourceInstance;
                        if (result.getOperation() == null) {
                            serviceResourceInstance = new RestoreDatabaseOperation();
                            result.setOperation(serviceResourceInstance);
                        } else {
                            serviceResourceInstance = result.getOperation();
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "RequestID".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "RequestID".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                String requestIDInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    requestIDInstance = xmlPullParser.getText();
                                    serviceResourceInstance.setId(requestIDInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "SourceDatabaseName".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "SourceDatabaseName".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                String sourceDatabaseNameInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    sourceDatabaseNameInstance = xmlPullParser.getText();
                                    serviceResourceInstance.setSourceDatabaseName(sourceDatabaseNameInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "SourceDatabaseDeletionDate".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "SourceDatabaseDeletionDate".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                Calendar sourceDatabaseDeletionDateInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    sourceDatabaseDeletionDateInstance = DatatypeFactoryImpl.newInstance().newXMLGregorianCalendar(xmlPullParser.getText()).toGregorianCalendar();
                                    serviceResourceInstance.setSourceDatabaseDeletionDate(sourceDatabaseDeletionDateInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "TargetServerName".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "TargetServerName".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                String targetServerNameInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    targetServerNameInstance = xmlPullParser.getText();
                                    serviceResourceInstance.setTargetServerName(targetServerNameInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "TargetDatabaseName".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "TargetDatabaseName".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                String targetDatabaseNameInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    targetDatabaseNameInstance = xmlPullParser.getText();
                                    serviceResourceInstance.setTargetDatabaseName(targetDatabaseNameInstance);
                                }
                                
                                eventType = xmlPullParser.next();
                            }
                        }
                        
                        if (eventType == XmlPullParser.START_TAG && "TargetUtcPointInTime".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) {
                            while ((eventType == XmlPullParser.END_TAG && "TargetUtcPointInTime".equals(xmlPullParser.getName()) && "http://schemas.microsoft.com/windowsazure".equals(xmlPullParser.getNamespace())) != true) {
                                Calendar targetUtcPointInTimeInstance;
                                if (eventType == XmlPullParser.TEXT) {
                                    targetUtcPointInTimeInstance = DatatypeFactoryImpl.newInstance().newXMLGregorianCalendar(xmlPullParser.getText()).toGregorianCalendar();
                                    serviceResourceInstance.setPointInTime(targetUtcPointInTimeInstance);
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
