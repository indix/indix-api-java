package com.indix.client;

import com.indix.client.impl.IndixApiClientFactory;
import com.indix.exception.IndixApiException;
import com.indix.httpClient.HttpClient;
import com.indix.models.suggestions.SuggestionsResult;
import org.junit.Test;
import com.indix.query.QueryFactory;
import com.indix.query.SuggestionsQuery;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class IndixApiClientSuggestionsTest {

    @Test
    public void getSuggestions()
            throws IndixApiException, IOException, URISyntaxException {
        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("suggestions-json-responses0/suggestionsResponse.json");
        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", mockHttpClient);

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
