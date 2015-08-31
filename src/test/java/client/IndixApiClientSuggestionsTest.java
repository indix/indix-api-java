package client;

import client.impl.IndixApiClientFactory;
import exception.IndixApiException;
import httpClient.HttpClient;
import models.suggestions.SuggestionsResult;
import org.junit.Test;
import query.QueryFactory;
import query.SuggestionsQuery;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class IndixApiClientSuggestionsTest {

    @Test
    public void getSuggestions() throws IOException, IndixApiException {
        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("suggestions-json-responses0/suggestionsResponse.json");
        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123", mockHttpClient);

        try {
            SuggestionsQuery suggestionsQuery = QueryFactory.newSuggestionsQuery()
                    .withCountryCode("US")
                    .withQ("ni");
            SuggestionsResult sr = indixApiClient.getSuggestions(suggestionsQuery);

            assertEquals(32, sr.getSuggestions().size());
            assertEquals("nickel", sr.getSuggestions().get(0).getSuggestion());
        } finally {
            indixApiClient.close();
        }
    }

}
