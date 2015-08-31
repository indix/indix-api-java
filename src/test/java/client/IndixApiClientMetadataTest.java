package client;

import client.impl.IndixApiClientFactory;
import exception.IndixApiException;
import httpClient.HttpClient;
import models.metadataResponse.metadataResult.BrandsResult;
import models.metadataResponse.metadataResult.CategoriesResult;
import models.metadataResponse.metadataResult.StoresResult;
import org.junit.Test;
import query.MetadataQuery;
import query.QueryFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class IndixApiClientMetadataTest {

    public HttpClient getMockHttpClient(String resource) throws IOException, IndixApiException {
        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient(resource);
        return mockHttpClient;
    }

    @Test
    public void getStores() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("metadata-json-responses0/storesResponse.json"));

        try {
            MetadataQuery metadataQuery = QueryFactory.newMetadataQuery()
                    .withQ("ama");
            StoresResult sr = indixApiClient.getStores(metadataQuery);

            assertEquals("US", sr.getStores().get(0).getCountryCode());
            assertEquals(3341, sr.getStores().get(0).getId());
            assertEquals("CPO Delta", sr.getStores().get(0).getName());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getBrands() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("metadata-json-responses0/brandsResponse.json"));

        try {
            MetadataQuery metadataQuery = QueryFactory.newMetadataQuery()
                    .withQ("ama");
            BrandsResult sr = indixApiClient.getBrands(metadataQuery);

            assertEquals(73, sr.getBrands().get(0).getId());
            assertEquals("Amanda Uprichard", sr.getBrands().get(0).getName());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getCategories() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("metadata-json-responses0/categoriesResponse.json"));

        try {
            MetadataQuery metadataQuery = QueryFactory.newMetadataQuery();
            CategoriesResult sr = indixApiClient.getCategories(metadataQuery);

            assertEquals(10161, sr.getCategories().get(0).getId());
            assertEquals("Computers & Accessories", sr.getCategories().get(0).getName());
        } finally {
            indixApiClient.close();
        }
    }

}
