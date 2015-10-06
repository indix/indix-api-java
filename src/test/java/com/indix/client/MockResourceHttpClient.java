package com.indix.client;

import com.indix.common.ResourceUtils;
import com.indix.exception.IndixApiException;
import com.indix.httpClient.HttpClient;
import org.apache.http.NameValuePair;

import java.io.*;
import java.net.URI;
import java.util.List;

public class MockResourceHttpClient {

    public HttpClient getMockClient(String resource) throws IOException, IndixApiException {

        final String resourceName = resource;

        //create mocks for all the HTTP request types with specified resource
        //
        HttpClient mockhttpclient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), resourceName);
            }

            public InputStream GETStream(URI uri) throws IOException, IndixApiException {
                InputStream content = ResourceUtils.getTestStream(getClass().getClassLoader(), resourceName);
                return content;
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), resourceName);
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair, File file) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), resourceName);
            }

            public void close() throws IOException {

            }
        };
        return mockhttpclient;
    }

}
