package httpClient.impl;

import httpClient.HttpClient;

/*
 * Instantiates http client instances
 */
public class HttpClientFactory {

    public static HttpClient newHttpClient() {
        return new HttpClientImpl();
    }
}
