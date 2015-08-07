package client;

import client.impl.IndixApiClientFactory;
import exception.IndixApiException;
import exception.InternalServerException;
import httpClient.HttpClient;
import models.searchResponse.searchResult.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.Query;
import query.QueryFactory;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class IndixApiClientSearchTest {

    final static Logger logger = LoggerFactory.getLogger(IndixApiClientSearchTest.class);

    public HttpClient getMockHttpClient(String resource) throws IOException, IndixApiException {
        MockHttpClient mockHttpClientInstance = new MockHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.mockGetClient(resource);
        return mockHttpClient;
    }

    @Test
    public void getProductsUniversal() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            UniversalSearchResult sr = indixApiClient.getProductsUniversal(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsOffersPremium() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/offersPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersPremium(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsOffersStandard() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/offersStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withStoreId(Arrays.asList(270))
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersStandard(searchQuery);
            assertEquals(11624, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsCatalogStandard() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/catalogStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogStandardSearchResult sr = indixApiClient.getProductsCatalogStandard(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsCatalogPremium() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/catalogPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogPremiumSearchResult sr = indixApiClient.getProductsCatalogPremium(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsSummary() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/summarySearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            SummarySearchResult sr = indixApiClient.getProductsSummary(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsCatalogPremiumFailsIfInputResponseIsMalformed0() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));
        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsCatalogPremium(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsCatalogPremiumFailsIfInputResponseIsMalformed1() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/offersPremiumSearchResponse.json"));
        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsCatalogPremium(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsCatalogPremiumFailsIfInputResponseIsMalformed2() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/offersStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsCatalogPremium(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsCatalogStandardFailsIfInputResponseIsMalformed0() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsCatalogStandard(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsCatalogStandardFailsIfInputResponseIsMalformed1() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/catalogPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsCatalogStandard(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsCatalogStandardFailsIfInputResponseIsMalformed2() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/offersPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsCatalogStandard(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsCatalogStandardFailsIfInputResponseIsMalformed3() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/offersStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsCatalogStandard(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsOffersPremiumFailsIfInputResponseIsMalformed0() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsOffersPremium(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsOffersPremiumFailsIfInputResponseIsMalformed1() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/catalogPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsOffersPremium(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsOffersPremiumFailsIfInputResponseIsMalformed2() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/catalogStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsOffersPremium(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsOffersStandardFailsIfInputResponseIsMalformed0() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsOffersStandard(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsOffersStandardFailsIfInputResponseIsMalformed1() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/catalogPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsOffersStandard(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsOffersStandardFailsIfInputResponseIsMalformed2() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/catalogStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsOffersStandard(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed0() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsSummary(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed1() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/catalogPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsSummary(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed2() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/catalogStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsSummary(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed3() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/offersPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsSummary(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed4() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(
                getMockHttpClient("search-json-responses0/offersStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            indixApiClient.getProductsSummary(searchQuery);

        } finally {
            indixApiClient.close();
        }
    }
}
