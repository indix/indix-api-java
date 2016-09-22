package com.indix.httpClient.impl;

import com.indix.httpClient.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Instantiates http client instances
 */
public class HttpClientFactory {

    public static HttpClient newHttpClient() {
        return new HttpClientImpl();
    }

    public static HttpClient newHttpClient(CloseableHttpClient httpClient) {
        return new HttpClientImpl(httpClient);
    }
}
