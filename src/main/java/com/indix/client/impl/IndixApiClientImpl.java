package com.indix.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.indix.client.IndixApiClient;
import com.indix.client.ProductsViewType;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indix.exception.IndixApiException;
import com.indix.exception.InternalServerException;
import com.indix.httpClient.HttpClient;
import com.indix.httpClient.impl.HttpClientFactory;
import com.indix.models.jobs.JobInfo;
import com.indix.models.metadataResult.*;
import com.indix.models.productDetailsResult.*;
import com.indix.models.productHistoryResponse.ProductHistoryResult;
import com.indix.models.searchResult.*;
import com.indix.models.suggestions.SuggestionsResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.indix.query.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.indix.client.ProductsViewType.*;
import static com.indix.client.impl.IndixApiConstants.*;

/**
 * Indix Api Client implementation
 */
class IndixApiClientImpl implements IndixApiClient {

    private static final String APP_ID = "app_id";
    private static final String APP_KEY = "app_key";

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
     * @param appId  application id
     * @param appKey application key
     */
    public IndixApiClientImpl(String appId, String appKey) {
        this(appId, appKey, HttpClientFactory.newHttpClient(), getNewObjectMapper(), SCHEME, HOST);
    }

    /**
     * @param appId  application id
     * @param appKey application key
     */
    public IndixApiClientImpl(String appId, String appKey, String scheme, String host) {
        this(appId, appKey, HttpClientFactory.newHttpClient(), getNewObjectMapper(), scheme, host);
    }

    /**
     * @param appId  application id
     * @param appKey application key
     */
    public IndixApiClientImpl(String appId, String appKey, HttpClient httpClient) {
        this(appId, appKey, httpClient, getNewObjectMapper(), SCHEME, HOST);
    }

    // getter methods
    //
    private static ObjectMapper getNewObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    // utility functions
    //
    private URI buildURI(String resource, Query searchQuery) throws URISyntaxException {
        return new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(resource)
                .setParameters(searchQuery.getParameters())
                .addParameter(APP_ID, appId)
                .addParameter(APP_KEY, appKey)
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

        // populate app_id and app_key
        //
        List<NameValuePair> params = searchQuery.getParameters();
        params.add(new BasicNameValuePair(APP_ID, appId));
        params.add(new BasicNameValuePair(APP_KEY, appKey));

        return httpClient.POST(uri, params, file);
    }

    static String buildPath(String... pathFragments) {
        StringBuilder sb = new StringBuilder();

        for (String fragment : pathFragments) {
            sb.append("/");
            sb.append(fragment);
        }
        return sb.toString();
    }

    private String buildSearchResourcePath(ProductsViewType resourceView) {
        return buildPath(VERSION, String.valueOf(resourceView), PRODUCTS_RESOURCE);
    }

    private String buildProductDetailsPath(ProductsViewType resourceView, String mpid) {
        return buildPath(buildSearchResourcePath(resourceView), mpid);
    }

    private String buildProductHistoryPath(String mpid) {
        return buildPath(PRODUCT_HISTORY_RESOURCE, mpid);
    }

    private String buildBulkSearchResourcePath(ProductsViewType resourceView) {
        return buildPath(VERSION, String.valueOf(resourceView), BULK, PRODUCTS_RESOURCE);
    }

    private String buildBulkLookupResourcePath(ProductsViewType resourceView) {
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
     * Search Products - Retrieves a list of products matching a variety of query parameters with offers info from a
     * store
     *
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link OffersSearchResult}
     * @throws {@link IndixApiException}
     */
    public OffersSearchResult getProductsOffersStandard(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildSearchResourcePath(OFFERS_STANDARD);
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<OffersSearchResult> searchIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<OffersSearchResult>>() {
                    });
            return searchIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductsOffersStandard failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductsOffersStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their summary info
     *
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link SummarySearchResult}
     * @throws {@link IndixApiException}
     */
    public SummarySearchResult getProductsSummary(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildSearchResourcePath(SUMMARY);
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<SummarySearchResult> searchIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<SummarySearchResult>>() {
                    });
            return searchIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductsSummary failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductsSummary failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with offers info from a
     * store
     *
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link OffersSearchResult}
     * @throws {@link IndixApiException}
     */
    public OffersSearchResult getProductsOffersPremium(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildSearchResourcePath(OFFERS_PREMIUM);
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<OffersSearchResult> searchIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<OffersSearchResult>>() {
                    });
            return searchIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductsOffersPremium failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductsOffersPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their
     * aggregated catalog info
     *
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link CatalogStandardSearchResult}
     * @throws {@link IndixApiException}
     */
    public CatalogStandardSearchResult getProductsCatalogStandard(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildSearchResourcePath(CATALOG_STANDARD);
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<CatalogStandardSearchResult> searchIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<CatalogStandardSearchResult>>() {
                    });
            return searchIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductsCatalogStandard failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductsCatalogStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their
     * catalog info across stores
     *
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link CatalogPremiumSearchResult}
     * @throws {@link IndixApiException}
     */
    public CatalogPremiumSearchResult getProductsCatalogPremium(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildSearchResourcePath(CATALOG_PREMIUM);
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<CatalogPremiumSearchResult> searchIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<CatalogPremiumSearchResult>>() {
                    });
            return searchIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductsCatalogPremium failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductsCatalogPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their offers and
     * catalog info across stores
     *
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link UniversalSearchResult}
     * @throws {@link IndixApiException}
     */
    public UniversalSearchResult getProductsUniversal(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildSearchResourcePath(UNIVERSAL);
        try {

            String content = executeGET(resource, query);
            IndixApiResponse<UniversalSearchResult> searchIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<UniversalSearchResult>>() {
                    });

            return searchIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductsUniversal failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductsUniversal failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns summary information for a product
     *
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link SummaryProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public SummaryProductDetailsResult getProductDetailsSummary(ProductDetailsQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildProductDetailsPath(SUMMARY, query.getMpid());
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<SummaryProductDetailsResult> productDetailsIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<SummaryProductDetailsResult>>() {
                    });
            return productDetailsIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductDetailsSummary failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductDetailsSummary failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns offers standard information for a product from a single storeId
     *
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link OffersProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public OffersProductDetailsResult getProductDetailsOffersStandard(ProductDetailsQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildProductDetailsPath(OFFERS_STANDARD, query.getMpid());
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<OffersProductDetailsResult> productDetailsIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<OffersProductDetailsResult>>() {
                    });
            return productDetailsIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductDetailsOffersStandard failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductDetailsOffersStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns offers premium information for a product
     *
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link OffersProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public OffersProductDetailsResult getProductDetailsOffersPremium(ProductDetailsQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildProductDetailsPath(OFFERS_PREMIUM, query.getMpid());
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<OffersProductDetailsResult> productDetailsIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<OffersProductDetailsResult>>() {
                    });
            return productDetailsIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductDetailsOffersPremium failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductDetailsOffersPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns catalog standard information for a product
     *
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link CatalogPremiumProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public CatalogStandardProductDetailsResult getProductDetailsCatalogStandard(ProductDetailsQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildProductDetailsPath(CATALOG_STANDARD, query.getMpid());
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<CatalogStandardProductDetailsResult> productDetailsIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<CatalogStandardProductDetailsResult>>() {
                    });
            return productDetailsIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductDetailsCatalogStandard failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductDetailsCatalogStandard failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns catalog premium information for a product
     *
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link CatalogPremiumProductDetailsResult}
     * @throws {@link IndixApiException}
     */

    public CatalogPremiumProductDetailsResult getProductDetailsCatalogPremium(ProductDetailsQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildProductDetailsPath(CATALOG_PREMIUM, query.getMpid());
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<CatalogPremiumProductDetailsResult> productDetailsIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<CatalogPremiumProductDetailsResult>>() {
                    });
            return productDetailsIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductDetailsCatalogPremium failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductDetailsCatalogPremium failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product Details - Returns complete information for a product
     *
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link UniversalProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    public UniversalProductDetailsResult getProductDetailsUniversal(ProductDetailsQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildProductDetailsPath(UNIVERSAL, query.getMpid());
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<UniversalProductDetailsResult> productDetailsIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<UniversalProductDetailsResult>>() {
                    });
            return productDetailsIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductDetailsUniversal failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductDetailsUniversal failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Stores - Lists all stores along with their IDs
     *
     * @param query Instance of {@link MetadataQuery} with appropriate parameters
     * @return {@link StoresResult}
     * @throws {@link IndixApiException}
     */
    public StoresResult getStores(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        try {
            String content = executeGET(STORES_RESOURCE, query);
            IndixApiResponse<StoresResult> storesIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<StoresResult>>() {
                    });
            return storesIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getStores failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getStores failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Brands - Lists all brands along with their IDs
     *
     * @param query Instance of {@link MetadataQuery} with appropriate parameters
     * @return {@link BrandsResult}
     * @throws {@link IndixApiException}
     */
    public BrandsResult getBrands(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        try {
            String content = executeGET(BRANDS_RESOURCE, query);
            IndixApiResponse<BrandsResult> brandsIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<BrandsResult>>() {
                    });
            return brandsIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getBrands failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getBrands failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Export Categories - Lists all categories along with their IDs and path
     *
     * @param query Instance of {@link MetadataQuery} with appropriate parameters
     * @return {@link CategoriesResult}
     * @throws {@link IndixApiException}
     */
    public CategoriesResult getCategories(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        try {
            String content = executeGET(CATEGORIES_RESOURCE, query);
            IndixApiResponse<CategoriesResult> categoriesIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<CategoriesResult>>() {
                    });
            return categoriesIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getCategories failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getCategories failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Search Suggestions - Lists all product search suggestions
     *
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link SuggestionsResult}
     * @throws {@link IndixApiException}
     */
    public SuggestionsResult getSuggestions(Query query)
            throws IndixApiException, IOException, URISyntaxException {

        try {
            String content = executeGET(SUGGESTIONS_RESOURCE, query);
            IndixApiResponse<SuggestionsResult> suggestionsIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<SuggestionsResult>>() {
                    });
            return suggestionsIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getSuggestions failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getSuggestions failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Product History - Returns the historical price information recorded for the product
     *
     * @param query Instance of {@link ProductHistoryQuery} with appropriate parameters
     * @return {@link ProductHistoryResult}
     * @throws {@link IndixApiException}
     */
    public ProductHistoryResult getProductHistory(ProductHistoryQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildProductHistoryPath(query.getMpid());
        try {
            String content = executeGET(resource, query);
            IndixApiResponse<ProductHistoryResult> productHistoryIndixApiResponse = jsonMapper.readValue(content,
                    new TypeReference<IndixApiResponse<ProductHistoryResult>>() {
                    });
            return productHistoryIndixApiResponse.getResult();
        } catch (IndixApiException iae) {
            logger.error("getProductHistory failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getProductHistory failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * Posts a bulk job for the appropriate resource type for search cases
     *
     * @param productsViewType {@link ProductsViewType}
     * @param query            Instance of {@link BulkProductsQuery} with appropriate parameters
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    public JobInfo postBulkJob(ProductsViewType productsViewType, BulkProductsQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildBulkSearchResourcePath(productsViewType);
        try {
            String content = executePOST(resource, query);
            JobInfo job = jsonMapper.readValue(content, JobInfo.class);
            return job;
        } catch (IndixApiException iae) {
            logger.error("postBulkJob failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("postBulkJob failed: " + e.getMessage());
            throw new InternalServerException(e);

        }
    }

    /**
     * Posts a bulk job for the appropriate resource type for lookup cases
     *
     * @param productsViewType {@link ProductsViewType}
     * @param query            Instance of {@link BulkLookupQuery} with appropriate parameters
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    public JobInfo postBulkJob(ProductsViewType productsViewType, BulkLookupQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildBulkLookupResourcePath(productsViewType);
        try {
            String content = executePOST(resource, query, query.getInputFile());
            JobInfo job = jsonMapper.readValue(content, JobInfo.class);
            return job;
        } catch (IndixApiException iae) {
            logger.error("postBulkJob failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("postBulkJob failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * get status of job returned against bulk query
     *
     * @param query Instance of {@link JobQuery} with appropriate jobId
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    public JobInfo getBulkJobStatus(JobQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildBulkJobStatusPath(
                BULK_JOB_RESOURCE,
                String.valueOf(query.getJobId())
        );
        try {
            String content = executeGET(resource, query);
            JobInfo jobStatus = jsonMapper.readValue(content, JobInfo.class);
            return jobStatus;
        } catch (IndixApiException iae) {
            logger.error("getBulkJobStatus failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getBulkJobStatus failed: " + e.getMessage());
            throw new InternalServerException(e);
        }

    }

    /**
     * get output of job returned against bulk query
     *
     * @param query Instance of {@link JobQuery} with appropriate jobId
     * @return stream of data obtained as response from the bulk job
     * @throws {@link IndixApiException}
     */
    public InputStream getBulkJobOutput(JobQuery query)
            throws IndixApiException, IOException, URISyntaxException {

        String resource = buildBulkJobDownloadPath(
                BULK_JOB_RESOURCE,
                String.valueOf(query.getJobId())
        );
        try {
            return executeGETStream(resource, query);
        } catch (IndixApiException iae) {
            logger.error("getBulkJobOutput failed: " + iae.getMessage());
            throw iae;
        } catch (JsonProcessingException e) {
            logger.error("getBulkJobOutput failed: " + e.getMessage());
            throw new InternalServerException(e);
        }
    }

    /**
     * close the http client involved with the api client
     *
     * @throws IOException
     */
    public void close() throws IOException {
        httpClient.close();
    }
}
