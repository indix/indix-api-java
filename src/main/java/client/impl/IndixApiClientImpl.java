package client.impl;

import client.IndixApiClient;
import client.ResourceType;
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
import static client.impl.IndixApiConstants.*;
import static client.impl.IndixApiConstants.buildPath;
import static client.ResourceType.*;

/**
 * Indix Api Client implementation
 */
class IndixApiClientImpl implements IndixApiClient {

    // http client
    //
    HttpClient httpClient;

    // converts json to class instances
    //
    ObjectMapper jsonMapper;

    // for defining the scheme and host of api
    //
    String scheme;
    String host;

    // Authorization parameters
    //
    String appId;
    String appKey;

    final static Logger logger = LoggerFactory.getLogger(IndixApiClientImpl.class);

    // constructors
    //
    private IndixApiClientImpl(String appId, String appKey, HttpClient httpClient, ObjectMapper jsonMapper,
                               String scheme, String host) {
        this.appId = appId;
        this.appKey = appKey;
        this.httpClient = httpClient;
        this.jsonMapper = jsonMapper;
        this.scheme = scheme;
        this.host = host;
    }

    /**
     * @param appId application id
     * @param appKey application key
     */
    public IndixApiClientImpl(String appId, String appKey) {
        this(appId, appKey, HttpClientFactory.newHttpClient(), getNewObjectMapper(), SCHEME, HOST);
    }

    /**
     * @param appId application id
     * @param appKey application key
     */
    public IndixApiClientImpl(String appId, String appKey, String scheme, String host) {
        this(appId, appKey, HttpClientFactory.newHttpClient(), getNewObjectMapper(), scheme, host);
    }

    /**
     * @param appId application id
     * @param appKey application key
     */
    public IndixApiClientImpl(String appId, String appKey, HttpClient httpClient) {
        this(appId, appKey, httpClient, getNewObjectMapper(), SCHEME, HOST);
    }

    // getter methods
    //
    private static ObjectMapper getNewObjectMapper() {
        return new ObjectMapper();
    }

    // utility functions
    //
    private URI buildURI(String resource, Query searchQuery) throws URISyntaxException, IOException, IndixApiException {
        return new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(resource)
                .setParameters(searchQuery.getParameters())
                .addParameter("app_id", appId)
                .addParameter("app_key", appKey)
                .build();
    }

    private String executeGET(String resource, Query searchQuery)
            throws URISyntaxException, IOException, IndixApiException {
        URI uri = buildURI(resource, searchQuery);
        return httpClient.GET(uri);
    }

    private InputStream executeGETStream(String resource, Query searchQuery)
            throws URISyntaxException, IOException, IndixApiException {
        URI uri = buildURI(resource, searchQuery);
        return httpClient.GETStream(uri);
    }

    private String executePOST(String resource, Query searchQuery)
            throws URISyntaxException, IOException, IndixApiException {
        URI uri = buildURI(resource, searchQuery);
        return httpClient.POST(uri, searchQuery.getParameters());
    }

    private String executePOST(String resource, Query searchQuery, File file)
            throws URISyntaxException, IOException, IndixApiException {
        URI uri = buildURI(resource, searchQuery);
        return httpClient.POST(uri, searchQuery.getParameters(), file);
    }

    private String buildSearchResourcePath(ResourceType resourceView) {
        return buildPath(VERSION, String.valueOf(resourceView), PRODUCTS_RESOURCE);
    }

    private String buildProductDetailsPath(ResourceType resourceView, String mpid) {
        return buildPath(buildSearchResourcePath(resourceView), mpid);
    }

    private String buildProductHistoryPath(String mpid) {
        return buildPath(PRODUCT_HISTORY_RESOURCE, mpid);
    }

    private String buildBulkSearchResourcePath(ResourceType resourceView) {
        return buildPath(VERSION, String.valueOf(resourceView), BULK, PRODUCTS_RESOURCE);
    }

    private String buildBulkLookupResourcePath(ResourceType resourceView) {
        return buildPath(VERSION, String.valueOf(resourceView), BULK, LOOKUP_VIEW);
    }

    private String buildBulkJobStatusPath(String resource, String jobId) {
        return buildPath(resource, jobId);
    }

    private static String buildBulkJobDownloadPath(String resource, String jobId) {
        return buildPath(resource, jobId, DOWNLOAD_PATH);
    }

    // response handlers
    //

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their summary info
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link SummarySearchResult}
     * @throws {@link IndixApiException}
     */
    public SummarySearchResult getProductsSummary(Query query) throws IndixApiException {

        String resource = buildSearchResourcePath(SUMMARY);
        try {
            String content = executeGET(resource, query);
            SummarySearchResponse searchResponse = jsonMapper.readValue(content, SummarySearchResponse.class);
            return searchResponse.getSearchResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsSummary failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with offers info from a
     * store
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link OffersSearchResult}
     * @throws {@link IndixApiException}
     */
    public OffersSearchResult getProductsOffersStandard(Query query) throws IndixApiException {

        String resource = buildSearchResourcePath(OFFERS_STANDARD);
        try {
            String content = executeGET(resource, query);
            OffersSearchResponse searchResponse = jsonMapper.readValue(content, OffersSearchResponse.class);
            return searchResponse.getSearchResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsOffersStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with offers info from a
     * store
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link OffersSearchResult}
     * @throws {@link IndixApiException}
     */
    public OffersSearchResult getProductsOffersPremium(Query query) throws IndixApiException {

        String resource = buildSearchResourcePath(OFFERS_PREMIUM);
        try {
            String content = executeGET(resource, query);
            OffersSearchResponse searchResponse = jsonMapper.readValue(content, OffersSearchResponse.class);
            return searchResponse.getSearchResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsOffersPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their
     * aggregated catalog info
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link CatalogStandardSearchResult}
     * @throws {@link IndixApiException}
     */
    public CatalogStandardSearchResult getProductsCatalogStandard(Query query) throws IndixApiException {

        String resource = buildSearchResourcePath(CATALOG_STANDARD);
        try {
            String content = executeGET(resource, query);
            CatalogStandardSearchResponse searchResponse = jsonMapper.readValue(
                    content,
                    CatalogStandardSearchResponse.class);
            return searchResponse.getSearchResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsCatalogStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their
     * catalog info across stores
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link CatalogPremiumSearchResult}
     * @throws {@link IndixApiException}
     */
    public CatalogPremiumSearchResult getProductsCatalogPremium(Query query) throws IndixApiException {

        String resource = buildSearchResourcePath(CATALOG_PREMIUM);
        try {
            String content = executeGET(resource, query);
            CatalogPremiumSearchResponse searchResponse = jsonMapper.readValue(
                    content,
                    CatalogPremiumSearchResponse.class);
            return searchResponse.getSearchResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsCatalogPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their offers and
     * catalog info across stores
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link UniversalSearchResult}
     * @throws {@link IndixApiException}
     */
    public UniversalSearchResult getProductsUniversal(Query query) throws IndixApiException {

        String resource = buildSearchResourcePath(UNIVERSAL);
        try {
            String content = executeGET(resource, query);
            UniversalSearchResponse searchResponse = jsonMapper.readValue(content, UniversalSearchResponse.class);
            return searchResponse.getSearchResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductsUniversal failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns summary information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link SummaryProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public SummaryProductDetailsResult getProductDetailsSummary(ProductDetailsQuery query) throws IndixApiException {

        String resource = buildProductDetailsPath(SUMMARY, query.getMpid());
        try {
            String content = executeGET(resource, query);
            SummaryProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    SummaryProductDetailsResponse.class
            );
            return productDetailsResponse.getProductDetailsResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsSummary failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns offers standard information for a product from a single storeId
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link OffersProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public OffersProductDetailsResult getProductDetailsOffersStandard(ProductDetailsQuery query)
            throws IndixApiException {

        String resource = buildProductDetailsPath(OFFERS_STANDARD, query.getMpid());
        try {
            String content = executeGET(resource, query);
            OffersProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    OffersProductDetailsResponse.class
            );
            return productDetailsResponse.getProductDetailsResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsOffersStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns offers premium information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link OffersProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public OffersProductDetailsResult getProductDetailsOffersPremium(ProductDetailsQuery query)
            throws IndixApiException {

        String resource = buildProductDetailsPath(OFFERS_PREMIUM, query.getMpid());
        try {
            String content = executeGET(resource, query);
            OffersProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    OffersProductDetailsResponse.class
            );
            return productDetailsResponse.getProductDetailsResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsOffersPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns catalog standard information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link CatalogPremiumProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public CatalogStandardProductDetailsResult getProductDetailsCatalogStandard(ProductDetailsQuery query)
            throws IndixApiException {

        String resource = buildProductDetailsPath(CATALOG_STANDARD, query.getMpid());
        try {
            String content = executeGET(resource, query);
            CatalogStandardProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    CatalogStandardProductDetailsResponse.class
            );
            return productDetailsResponse.getProductDetailsResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsCatalogStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns catalog premium information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link CatalogPremiumProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public CatalogPremiumProductDetailsResult getProductDetailsCatalogPremium(ProductDetailsQuery query)
            throws IndixApiException {

        String resource = buildProductDetailsPath(CATALOG_PREMIUM, query.getMpid());
        try {
            String content = executeGET(resource, query);
            CatalogPremiumProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    CatalogPremiumProductDetailsResponse.class
            );
            return productDetailsResponse.getProductDetailsResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsCatalogPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns complete information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link UniversalProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public UniversalProductDetailsResult getProductDetailsUniversal(ProductDetailsQuery query)
            throws IndixApiException {

        String resource = buildProductDetailsPath(UNIVERSAL, query.getMpid());
        try {
            String content = executeGET(resource, query);
            UniversalProductDetailsResponse productDetailsResponse = jsonMapper.readValue(
                    content,
                    UniversalProductDetailsResponse.class
            );
            return productDetailsResponse.getProductDetailsResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getProductDetailsUniversal failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Stores - Lists all stores along with their IDs
     * @param query Instance of {@link MetadataQuery} with appropriate parameters
     * @return {@link StoresResult}
     * @throws {@link IndixApiException}
     */
    public StoresResult getStores(Query query) throws IndixApiException {

        try {
            String content = executeGET(STORES_RESOURCE, query);
            StoresResponse storesResponse = jsonMapper.readValue(content, StoresResponse.class);
            return storesResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getStores failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Brands - Lists all brands along with their IDs
     * @param query Instance of {@link MetadataQuery} with appropriate parameters
     * @return {@link BrandsResult}
     * @throws {@link IndixApiException}
     */
    public BrandsResult getBrands(Query query) throws IndixApiException {

        try {
            String content = executeGET(BRANDS_RESOURCE, query);
            BrandsResponse brandsResponse = jsonMapper.readValue(content, BrandsResponse.class);
            return brandsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getBrands failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Export Categories - Lists all categories along with their IDs and path
     * @param query Instance of {@link MetadataQuery} with appropriate parameters
     * @return {@link CategoriesResult}
     * @throws {@link IndixApiException}
     */
    public CategoriesResult getCategories(Query query) throws IndixApiException {

        try {
            String content = executeGET(CATEGORIES_RESOURCE, query);
            CategoriesResponse categoriesResponse = jsonMapper.readValue(content, CategoriesResponse.class);
            return categoriesResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getCategories failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Suggestions - Lists all product search suggestions
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link SuggestionsResult}
     * @throws {@link IndixApiException}
     */
    public SuggestionsResult getSuggestions(Query query) throws IndixApiException {

        try {
            String content = executeGET(SUGGESTIONS_RESOURCE, query);
            SuggestionsResponse suggestionsResponse = jsonMapper.readValue(content, SuggestionsResponse.class);
            return suggestionsResponse.getResult();
        } catch (IndixApiException iae) {
            throw iae;
        } catch (Exception e) {
            logger.error("getSuggestions failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product History - Returns the historical price information recorded for the product
     * @param query Instance of {@link ProductHistoryQuery} with appropriate parameters
     * @return {@link ProductHistoryResult}
     * @throws {@link IndixApiException}
     */
    public ProductHistoryResult getProductHistory(ProductHistoryQuery query) throws IndixApiException {

        String resource = buildProductHistoryPath(query.getMpid());
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

    /**
     * Posts a bulk job for the appropriate resource type for search cases
     * @param resourceType {@link ResourceType}
     * @param query Instance of {@link BulkProductsQuery} with appropriate parameters
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    public JobInfo postBulkJob(ResourceType resourceType, BulkProductsQuery query) throws IndixApiException {

        String resource = buildBulkSearchResourcePath(resourceType);
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

    /**
     * Posts a bulk job for the appropriate resource type for lookup cases
     * @param resourceType {@link ResourceType}
     * @param query Instance of {@link BulkLookupQuery} with appropriate parameters
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    public JobInfo postBulkJob(ResourceType resourceType, BulkLookupQuery query) throws IndixApiException {

        String resource = buildBulkLookupResourcePath(resourceType);
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

    /**
     * get status of job returned against bulk query
     * @param query Instance of {@link JobStatusQuery} with appropriate jobId
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    public JobInfo getBulkJobStatus(JobStatusQuery query) throws IndixApiException {

        String resource = buildBulkJobStatusPath(
                BULK_JOB_RESOURCE,
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

    /**
     * get output of job returned against bulk query
     * @param query Instance of {@link JobStatusQuery} with appropriate jobId
     * @return stream of data obtained as response from the bulk job
     * @throws {@link IndixApiException}
     */
    public InputStream getBulkJobOutput(JobStatusQuery query) throws IndixApiException {

        String resource = buildBulkJobDownloadPath(
                BULK_JOB_RESOURCE,
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


    /**
     * close the http client involved with the api client
     * @throws IOException
     */
    public void close() throws IOException {
        httpClient.close();
    }
}
