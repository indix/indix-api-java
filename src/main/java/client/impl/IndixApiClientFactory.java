package client.impl;

import client.IndixApiClient;
import httpClient.HttpClient;

/*
 * Instantiates IndixApiClient instances
 */
public class IndixApiClientFactory {

    public static IndixApiClient newIndixApiClient(String appId, String appKey) {
        return new IndixApiClientImpl(appId, appKey);
    }

    /*
     * Used for mocking http client for testing purpose.
     */
    public static IndixApiClient newIndixApiClient(String appId, String appKey, HttpClient httpClient) {
        return new IndixApiClientImpl(appId, appKey, httpClient);
    }

    /*
     * Used for setting server scheme and host for testing purpose.
     */
    public static IndixApiClient newIndixApiClient(String appId, String appKey, String scheme, String host) {
        return new IndixApiClientImpl(appId, appKey, scheme, host);
    }
}
