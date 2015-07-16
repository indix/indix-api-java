package client.impl;

import client.IndixApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.IndixApiException;
import exception.InternalServerException;
import httpClient.HttpClient;
import httpClient.impl.HttpClientFactory;
import models.metadataResponse.BrandsResponse;
import models.metadataResponse.CategoriesResponse;
import models.metadataResponse.StoresResponse;
import models.metadataResponse.metadataResult.BrandsResult;
import models.metadataResponse.metadataResult.CategoriesResult;
import models.metadataResponse.metadataResult.StoresResult;
import models.productDetailsResponse.*;
import models.productDetailsResponse.productDetailsResult.*;
import models.productHistoryResponse.ProductHistoryResponse;
import models.productHistoryResponse.ProductHistoryResult;
import models.searchResponse.*;
import models.searchResponse.searchResult.*;
import models.suggestions.SuggestionsResponse;
import models.suggestions.SuggestionsResult;
import org.apache.http.client.utils.URIBuilder;
import query.ProductDetailsQuery;
import query.ProductHistoryQuery;
import query.Query;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Indix Api Client
 */
class IndixApiClientImpl implements IndixApiClient {

    // http client
    //
    HttpClient httpClient;

    // converts json to class instances
    //
    ObjectMapper jsonMapper;

    private IndixApiClientImpl(HttpClient _httpClient, ObjectMapper _jsonMapper) {
        httpClient = _httpClient;
        jsonMapper = _jsonMapper;
    }

    public IndixApiClientImpl() {
        this(HttpClientFactory.newHttpClient(), getNewObjectMapper());
    }

    public IndixApiClientImpl(HttpClient httpClient) {
        this(httpClient, getNewObjectMapper());
    }

    private static ObjectMapper getNewObjectMapper() {
        return new ObjectMapper();
    }

    private String execute(String resource, Query searchQuery) throws URISyntaxException, IOException, IndixApiException {
        URI uri = new URIBuilder()
                .setScheme(IndixApiConstants.SCHEME)
                .setHost(IndixApiConstants.HOST)
                .setPath(resource)
                .setParameters(searchQuery.getParameters())
                .build();
        return httpClient.GET(uri);
    }

    public SummarySearchResult getProductsSummary(Query query) throws IndixApiException {
        try {
            String content = execute(IndixApiConstants.SUMMARY_PRODUCTS_RESOURCE, query);
            SummarySearchResponse searchResponse = jsonMapper.readValue(content, SummarySearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductsSummary failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public OffersSearchResult getProductsOffersStandard(Query query) throws IndixApiException {
        try {
            String content = execute(IndixApiConstants.OFFERS_STANDARD_PRODUCTS_RESOURCE, query);
            OffersSearchResponse searchResponse = jsonMapper.readValue(content, OffersSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductsOffersStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public OffersSearchResult getProductsOffersPremium(Query query) throws IndixApiException {
        try {
            String content = execute(IndixApiConstants.OFFERS_PREMIUM_PRODUCTS_RESOURCE, query);
            OffersSearchResponse searchResponse = jsonMapper.readValue(content, OffersSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductsOffersPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public CatalogStandardSearchResult getProductsCatalogStandard(Query query) throws IndixApiException {
        try {
            String content = execute(IndixApiConstants.CATALOG_STANDARD_PRODUCTS_RESOURCE, query);
            CatalogStandardSearchResponse searchResponse = jsonMapper.readValue(content, CatalogStandardSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductsCatalogStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public CatalogPremiumSearchResult getProductsCatalogPremium(Query query) throws IndixApiException {
        try {
            String content = execute(IndixApiConstants.CATALOG_PREMIUM_PRODUCTS_RESOURCE, query);
            CatalogPremiumSearchResponse searchResponse = jsonMapper.readValue(content, CatalogPremiumSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductsCatalogPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public UniversalSearchResult getProductsUniversal(Query query) throws IndixApiException {
        try {
            String content = execute(IndixApiConstants.UNIVERSAL_PRODUCTS_RESOURCE, query);
            UniversalSearchResponse searchResponse = jsonMapper.readValue(content, UniversalSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductsUniversal failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public SummaryProductDetailsResult getProductDetailsSummary(ProductDetailsQuery query)
            throws IndixApiException {
        String resource = IndixApiConstants.buildProductDetailsPath(
                IndixApiConstants.SUMMARY_PRODUCT_DETAILS_RESOURCE,
                query.getMpid()
        );
        
        try {
            String content = execute(resource, query);
            SummaryProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    SummaryProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductDetailsSummary failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public OffersProductDetailsResult getProductDetailsOffersStandard(ProductDetailsQuery query)
            throws IndixApiException {
        String resource = IndixApiConstants.buildProductDetailsPath(
                IndixApiConstants.OFFERS_STANDARD_PRODUCT_DETAILS_RESOURCE,
                query.getMpid()
        );
        
        try {
            String content = execute(resource, query);
            OffersProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    OffersProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductDetailsOffersStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public OffersProductDetailsResult getProductDetailsOffersPremium(ProductDetailsQuery query)
            throws IndixApiException {

        String resource = IndixApiConstants.buildProductDetailsPath(
                IndixApiConstants.OFFERS_PREMIUM_PRODUCT_DETAILS_RESOURCE,
                query.getMpid()
        );
        
        try {
            String content = execute(resource, query);
            OffersProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    OffersProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductDetailsOffersPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public CatalogStandardProductDetailsResult getProductDetailsCatalogStandard(ProductDetailsQuery query)
            throws IndixApiException {

        String resource = IndixApiConstants.buildProductDetailsPath(
                IndixApiConstants.CATALOG_STANDARD_PRODUCT_DETAILS_RESOURCE,
                query.getMpid()
        );
        
        try {
            String content = execute(resource, query);
            CatalogStandardProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    CatalogStandardProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductDetailsCatalogStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public CatalogPremiumProductDetailsResult getProductDetailsCatalogPremium(ProductDetailsQuery query)
            throws IndixApiException {

        String resource = IndixApiConstants.buildProductDetailsPath(
                IndixApiConstants.CATALOG_PREMIUM_PRODUCT_DETAILS_RESOURCE,
                query.getMpid()
        );
        
        try {
            String content = execute(resource, query);
            CatalogPremiumProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    CatalogPremiumProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductDetailsCatalogPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public UniversalProductDetailsResult getProductDetailsUniversal(ProductDetailsQuery query)
            throws IndixApiException {
        String resource = IndixApiConstants.buildProductDetailsPath(
                IndixApiConstants.UNIVERSAL_PRODUCT_DETAILS_RESOURCE,
                query.getMpid()
        );
        
        try {
            String content = execute(resource, query);
            UniversalProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    UniversalProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductDetailsUniversal failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public StoresResult getStores(Query query) throws IndixApiException {

        try {
            String content = execute(IndixApiConstants.STORES_RESOURCE, query);
            StoresResponse storesResponse = jsonMapper.readValue(content, StoresResponse.class);
            return storesResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getStores failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public BrandsResult getBrands(Query query) throws IndixApiException {

        try {
            String content = execute(IndixApiConstants.BRANDS_RESOURCE, query);
            BrandsResponse brandsResponse = jsonMapper.readValue(content, BrandsResponse.class);
            return brandsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getBrands failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public CategoriesResult getCategories(Query query) throws IndixApiException {

        try {
            String content = execute(IndixApiConstants.CATEGORIES_RESOURCE, query);
            CategoriesResponse categoriesResponse = jsonMapper.readValue(content, CategoriesResponse.class);
            return categoriesResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getCategories failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public SuggestionsResult getSuggestions(Query query) throws IndixApiException {

        try {
            String content = execute(IndixApiConstants.SUGGESTIONS_RESOURCE, query);
            SuggestionsResponse suggestionsResponse = jsonMapper.readValue(content, SuggestionsResponse.class);
            return suggestionsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getSuggestions failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public ProductHistoryResult getProductHistory(ProductHistoryQuery query) throws IndixApiException{

        try {
            String content = execute(IndixApiConstants.PRODUCT_HISTORY_RESOURCE , query);
            ProductHistoryResponse productHistoryResponse = jsonMapper.readValue(content, ProductHistoryResponse.class);
            return productHistoryResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            System.out.println("getProductHistory failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public void close() throws IOException {
        httpClient.close();
    }
}
