package client;

import client.impl.IndixApiClientFactory;
import common.ResourceUtils;
import exception.IndixApiException;
import httpClient.HttpClient;
import models.suggestions.SuggestionsResult;
import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;
import query.SuggestionsQuery;
import query.QueryFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class IndixApiClientSuggestionsTest {

    @Test
    public void getSuggestions() throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "suggestions-json-responses0/suggestionsResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SuggestionsQuery suggestionsQuery = QueryFactory.newSuggestionsQuery()
                    .withAppId("123")
                    .withAppKey("123")
                    .withCountryCode("US")
                    .withQ("ni");
            SuggestionsResult sr = indixApiClient.searchSuggestions(suggestionsQuery);

            Assert.assertEquals(32, sr.getSuggestions().size());
            Assert.assertEquals("nickel", sr.getSuggestions().get(0).getSuggestion());
        } finally {
            indixApiClient.close();
        }
    }

}
