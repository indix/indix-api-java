package client.impl;

import client.IndixApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.IndixApiException;
import exception.InternalServerException;
import httpClient.HttpClient;
import httpClient.impl.HttpClientFactory;
import models.jobs.JobInfo;
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
import query.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    String scheme = IndixApiConstants.SCHEME,
           host   = IndixApiConstants.HOST;

    final static Logger logger = LoggerFactory.getLogger(IndixApiClientImpl.class);

    private IndixApiClientImpl(HttpClient _httpClient, ObjectMapper _jsonMapper) {
        httpClient = _httpClient;
        jsonMapper = _jsonMapper;
    }

    public IndixApiClientImpl() {
        this(HttpClientFactory.newHttpClient(), getNewObjectMapper());
    }

    public IndixApiClientImpl(String scheme,String host) {
        this(HttpClientFactory.newHttpClient(), getNewObjectMapper());
        this.scheme = scheme;
        this.host = host;
    }

    public IndixApiClientImpl(HttpClient httpClient) {
        this(httpClient, getNewObjectMapper());
    }

    private static ObjectMapper getNewObjectMapper() {
        return new ObjectMapper();
    }

    private URI uriBuilder(String resource, Query searchQuery) throws URISyntaxException, IOException, IndixApiException {
        return new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(resource)
                .setParameters(searchQuery.getParameters())
                .build();
    }

    private String executeGET(String resource, Query searchQuery) throws URISyntaxException, IOException, IndixApiException {
        URI uri = uriBuilder(resource, searchQuery);
        return httpClient.GET(uri);
    }

    private InputStream executeGETStream(String resource, Query searchQuery) throws URISyntaxException, IOException, IndixApiException {
        URI uri = uriBuilder(resource, searchQuery);
        return httpClient.GETStream(uri);
    }

    private String executePOST(String resource, Query searchQuery) throws URISyntaxException, IOException, IndixApiException {
        URI uri = uriBuilder(resource, searchQuery);
        return httpClient.POST(uri, searchQuery.getParameters());
    }

    private String executePOST(String resource, Query searchQuery, File file) throws URISyntaxException, IOException, IndixApiException {
        URI uri = uriBuilder(resource, searchQuery);
        return httpClient.POST(uri, searchQuery.getParameters(), file);
    }

    public SummarySearchResult getProductsSummary(Query query) throws IndixApiException {
        try {
            String content = executeGET(IndixApiConstants.SUMMARY_PRODUCTS_RESOURCE, query);
            SummarySearchResponse searchResponse = jsonMapper.readValue(content, SummarySearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsSummary failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public OffersSearchResult getProductsOffersStandard(Query query) throws IndixApiException {
        try {
            String content = executeGET(IndixApiConstants.OFFERS_STANDARD_PRODUCTS_RESOURCE, query);
            OffersSearchResponse searchResponse = jsonMapper.readValue(content, OffersSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsOffersStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public OffersSearchResult getProductsOffersPremium(Query query) throws IndixApiException {
        try {
            String content = executeGET(IndixApiConstants.OFFERS_PREMIUM_PRODUCTS_RESOURCE, query);
            OffersSearchResponse searchResponse = jsonMapper.readValue(content, OffersSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsOffersPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public CatalogStandardSearchResult getProductsCatalogStandard(Query query) throws IndixApiException {
        try {
            String content = executeGET(IndixApiConstants.CATALOG_STANDARD_PRODUCTS_RESOURCE, query);
            CatalogStandardSearchResponse searchResponse = jsonMapper.readValue(content, CatalogStandardSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsCatalogStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public CatalogPremiumSearchResult getProductsCatalogPremium(Query query) throws IndixApiException {
        try {
            String content = executeGET(IndixApiConstants.CATALOG_PREMIUM_PRODUCTS_RESOURCE, query);
            CatalogPremiumSearchResponse searchResponse = jsonMapper.readValue(content, CatalogPremiumSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsCatalogPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public UniversalSearchResult getProductsUniversal(Query query) throws IndixApiException {
        try {
            String content = executeGET(IndixApiConstants.UNIVERSAL_PRODUCTS_RESOURCE, query);
            UniversalSearchResponse searchResponse = jsonMapper.readValue(content, UniversalSearchResponse.class);
            return searchResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsUniversal failed: " + e.getMessage());
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
            String content = executeGET(resource, query);
            SummaryProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    SummaryProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsSummary failed: " + e.getMessage());
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
            String content = executeGET(resource, query);
            OffersProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    OffersProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsOffersStandard failed: " + e.getMessage());
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
            String content = executeGET(resource, query);
            OffersProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    OffersProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsOffersPremium failed: " + e.getMessage());
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
            String content = executeGET(resource, query);
            CatalogStandardProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    CatalogStandardProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsCatalogStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public CatalogPremiumProductDetailsResult getProductDetailsCatalogPremium(ProductDetailsQuery query)
            throws IndixApiException{

        String resource = IndixApiConstants.buildProductDetailsPath(
                IndixApiConstants.CATALOG_PREMIUM_PRODUCT_DETAILS_RESOURCE,
                query.getMpid()
        );

        try {
            String content = executeGET(resource, query);
            CatalogPremiumProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    CatalogPremiumProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsCatalogPremium failed: " + e.getMessage());
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
            String content = executeGET(resource, query);
            UniversalProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    UniversalProductDetailsResponse.class
            );
            return productDetailsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsUniversal failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public StoresResult getStores(Query query) throws IndixApiException {

        try {
            String content = executeGET(IndixApiConstants.STORES_RESOURCE, query);
            StoresResponse storesResponse = jsonMapper.readValue(content, StoresResponse.class);
            return storesResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getStores failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public BrandsResult getBrands(Query query) throws IndixApiException {

        try {
            String content = executeGET(IndixApiConstants.BRANDS_RESOURCE, query);
            BrandsResponse brandsResponse = jsonMapper.readValue(content, BrandsResponse.class);
            return brandsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getBrands failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public CategoriesResult getCategories(Query query) throws IndixApiException {

        try {
            String content = executeGET(IndixApiConstants.CATEGORIES_RESOURCE, query);
            CategoriesResponse categoriesResponse = jsonMapper.readValue(content, CategoriesResponse.class);
            return categoriesResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getCategories failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public SuggestionsResult getSuggestions(Query query) throws IndixApiException {

        try {
            String content = executeGET(IndixApiConstants.SUGGESTIONS_RESOURCE, query);
            SuggestionsResponse suggestionsResponse = jsonMapper.readValue(content, SuggestionsResponse.class);
            return suggestionsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getSuggestions failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public ProductHistoryResult getProductHistory(ProductHistoryQuery query) throws IndixApiException {
        String resource = IndixApiConstants.buildProductDetailsPath(
                IndixApiConstants.PRODUCT_HISTORY_RESOURCE,
                query.getMpid()
        );
        try {
            String content = executeGET(resource, query);
            ProductHistoryResponse productHistoryResponse = jsonMapper.readValue(content, ProductHistoryResponse.class);
            return productHistoryResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductHistory failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public JobInfo postBulkJob(String resource, BulkProductsQuery query) throws IndixApiException {
        try {
            String content = executePOST(resource, query);
            JobInfo job = jsonMapper.readValue(content, JobInfo.class);
            return job;
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getBulkQueryJob failed: " + e.getMessage());
            throw new InternalServerException(e);

        }
    }

    public JobInfo postBulkJob(String resource, BulkLookupQuery query) throws IndixApiException {
        try {
            String content = executePOST(resource, query, query.getInputFile());
            JobInfo job = jsonMapper.readValue(content, JobInfo.class);
            return job;
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getBulkQueryJob failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    public JobInfo getBulkJobStatus(JobStatusQuery query) throws IndixApiException {

        String resource = IndixApiConstants.buildBulkJobStatusPath(
                IndixApiConstants.BULK_JOB_RESOURCE,
                String.valueOf(query.getJobId())
        );
        try {
            String content = executeGET(resource, query);
            JobInfo jobStatus = jsonMapper.readValue(content, JobInfo.class);
            return jobStatus;
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getJobStatus failed: " + e.getMessage());
            throw new InternalServerException(e);
        }

    }

    public InputStream getBulkJobOutput(JobStatusQuery query) throws IndixApiException {

        String resource = IndixApiConstants.buildBulkJobDownloadPath(
                IndixApiConstants.BULK_JOB_RESOURCE,
                String.valueOf(query.getJobId())
        );
        try {
            return executeGETStream(resource, query);
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getJobStatus failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }


    public void close() throws IOException {
        httpClient.close();
    }
}
