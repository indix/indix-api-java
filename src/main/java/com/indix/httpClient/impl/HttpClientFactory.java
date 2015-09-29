package com.indix.httpClient.impl;

import com.indix.httpClient.HttpClient;

/**
 * Instantiates http client instances
 */
public class HttpClientFactory {

    public static HttpClient newHttpClient() {
        return new HttpClientImpl();
    }
}
