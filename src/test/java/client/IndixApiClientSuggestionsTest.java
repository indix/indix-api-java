package client;

import client.impl.IndixApiClientFactory;
import common.ResourceUtils;
import exception.BadRequestException;
import exception.IndixApiException;
import httpClient.HttpClient;
import models.suggestions.SuggestionsResult;
import org.junit.Test;
import query.QueryFactory;
import query.SuggestionsQuery;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertEquals;

public class IndixApiClientSuggestionsTest {

    @Test
    public void getSuggestions() throws IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "suggestions-json-responses0/suggestionsResponse.json");
            }
            public String POST(URI uri) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }
            public String POST(URI uri, File file) throws IOException, IndixApiException {
                return null;
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
            SuggestionsResult sr = indixApiClient.getSuggestions(suggestionsQuery);

            assertEquals(32, sr.getSuggestions().size());
            assertEquals("nickel", sr.getSuggestions().get(0).getSuggestion());
        } finally {
            indixApiClient.close();
        }
    }

}
