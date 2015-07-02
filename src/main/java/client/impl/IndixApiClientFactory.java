package client.impl;

import client.IndixApiClient;
import httpClient.HttpClient;

/*
 * Instantiates IndixApiClient instances
 */
public class IndixApiClientFactory {

    public static IndixApiClient newIndixApiClient() {
        return new IndixApiClientImpl();
    }

    /*
     * Used for mocking http client for testing purpose.
     */
    public static IndixApiClient newIndixApiClient(HttpClient httpClient) {
        return new IndixApiClientImpl(httpClient);
    }
}
