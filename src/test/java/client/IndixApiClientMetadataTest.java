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
        MockHttpCLient mockHttpClientInstance = new MockHttpCLient();
        HttpClient mockHttpClient = mockHttpClientInstance.mockGetClient(resource);
        return mockHttpClient;
    }

    @Test
    public void getStores() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                                            getMockHttpClient("metadata-json-responses0/storesResponse.json"));

        try {
            MetadataQuery metadataQuery = QueryFactory.newMetadataQuery()
                    .withAppId("cb7e91b3")
                    .withAppKey("7367fd8ed2856c6d44c4f30303963b9c")
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

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                                            getMockHttpClient("metadata-json-responses0/brandsResponse.json"));

        try {
            MetadataQuery metadataQuery = QueryFactory.newMetadataQuery()
                    .withAppId("123")
                    .withAppKey("123")
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

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                                            getMockHttpClient("metadata-json-responses0/categoriesResponse.json"));

        try {
            MetadataQuery metadataQuery = QueryFactory.newMetadataQuery()
                    .withAppId("123")
                    .withAppKey("123");
            CategoriesResult sr = indixApiClient.getCategories(metadataQuery);

            assertEquals(10161, sr.getCategories().get(0).getId());
            assertEquals("Computers & Accessories", sr.getCategories().get(0).getName());
        } finally {
            indixApiClient.close();
        }
    }

}
