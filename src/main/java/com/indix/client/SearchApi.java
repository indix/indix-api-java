package com.indix.client;

import com.indix.exception.IndixApiException;
import com.indix.models.searchResult.*;
import com.indix.query.Query;
import com.indix.query.SearchQuery;

import java.io.IOException;
import java.net.URISyntaxException;

public interface SearchApi {
    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their summary info
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link SummarySearchResult}
     * @throws {@link IndixApiException}
     */
    SummarySearchResult getProductsSummary(Query query)
            throws IndixApiException, IOException, URISyntaxException;

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with offers info from a
     * store
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link OffersSearchResult}
     * @throws {@link IndixApiException}
     */
    OffersSearchResult getProductsOffersStandard(Query query)
            throws IndixApiException, URISyntaxException, IOException;

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with offers info from a
     * store
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link OffersSearchResult}
     * @throws {@link IndixApiException}
     */
    OffersSearchResult getProductsOffersPremium(Query query)
            throws IndixApiException, IOException, URISyntaxException;

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their
     * aggregated catalog info
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link CatalogStandardSearchResult}
     * @throws {@link IndixApiException}
     */
    CatalogStandardSearchResult getProductsCatalogStandard(Query query)
            throws IndixApiException, IOException, URISyntaxException;

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their
     * catalog info across stores
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link CatalogPremiumSearchResult}
     * @throws {@link IndixApiException}
     */
    CatalogPremiumSearchResult getProductsCatalogPremium(Query query)
            throws IndixApiException, IOException, URISyntaxException;

    /**
     * Search Products - Retrieves a list of products matching a variety of query parameters with their offers and
     * catalog info across stores
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link UniversalSearchResult}
     * @throws {@link IndixApiException}
     */
    UniversalSearchResult getProductsUniversal(Query query)
            throws IndixApiException, IOException, URISyntaxException;
}
