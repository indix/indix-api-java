package com.indix.client;

import com.indix.client.impl.IndixApiClientFactory;
import com.indix.exception.IndixApiException;
import com.indix.httpClient.HttpClient;
import com.indix.models.productDetailsResult.*;
import org.junit.Test;
import com.indix.query.ProductDetailsQuery;
import com.indix.query.QueryFactory;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class IndixApiClientProductDetailsTest {

    public HttpClient getMockHttpClient(String resource)
            throws IndixApiException, IOException, URISyntaxException {
        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient(resource);
        return mockHttpClient;
    }

    @Test
    public void getProductDetailsSummary()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/summaryProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf");
            SummaryProductDetailsResult sr = indixApiClient.getProductDetailsSummary(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsOffersStandard()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/offersStandardProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf")
                    .withStoreId(2817);
            OffersProductDetailsResult sr = indixApiClient.getProductDetailsOffersStandard(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsOffersPremium()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/offersPremiumProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf");
            OffersProductDetailsResult sr = indixApiClient.getProductDetailsOffersPremium(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsCatalogStandard()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/catalogStandardProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf");
            CatalogStandardProductDetailsResult sr = indixApiClient.getProductDetailsCatalogStandard(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsCatalogPremium()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/catalogPremiumProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf");
            CatalogPremiumProductDetailsResult sr = indixApiClient.getProductDetailsCatalogPremium(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
            assertEquals("brandText1", sr.getProduct().getStores().get("2817").getOffers().get(0).getBrandText());
            assertEquals("breadCrumbs1", sr.getProduct().getStores().get("2817").getOffers().get(0).getBreadCrumbs());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsUniversal()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/universalProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf");
            UniversalProductDetailsResult sr = indixApiClient.getProductDetailsUniversal(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
            assertEquals("brandText1", sr.getProduct().getStores().get("2817").getOffers().get(0).getBrandText());
            assertEquals("breadCrumbs1", sr.getProduct().getStores().get("2817").getOffers().get(0).getBreadCrumbs());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsSummaryShouldNotFailIfInputHasAdditionalFields()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/universalProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf");
            SummaryProductDetailsResult sr = indixApiClient.getProductDetailsSummary(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsOffersStandardShouldNotFailIfInputHasAdditionalFields()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/universalProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf")
                    .withStoreId(2817);
            OffersProductDetailsResult sr = indixApiClient.getProductDetailsOffersStandard(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsOffersPremiumShouldNotFailIfInputHasAdditionalFields()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/universalProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf");
            OffersProductDetailsResult sr = indixApiClient.getProductDetailsOffersPremium(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsCatalogStandardShouldNotFailIfInputHasAdditionalFields()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/universalProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf");
            CatalogStandardProductDetailsResult sr = indixApiClient.getProductDetailsCatalogStandard(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductDetailsCatalogPremiumShouldNotFailIfInputHasAdditionalFields()
            throws IndixApiException, IOException, URISyntaxException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123",
                getMockHttpClient("productDetails-json-responses0/universalProductDetailsResponse.json"));

        try {
            ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                    .withCountryCode("AU")
                    .withMpid("c3c766f0b5fa6bfb9a6f5e2921779dcf");
            CatalogPremiumProductDetailsResult sr = indixApiClient.getProductDetailsCatalogPremium(productDetailsQuery);

            assertEquals("c3c766f0b5fa6bfb9a6f5e2921779dcf", sr.getProduct().getMpid());
            assertEquals("Kraft Vegemite Jar 380g, 150c", sr.getProduct().getTitle());
        } finally {
            indixApiClient.close();
        }
    }
}
