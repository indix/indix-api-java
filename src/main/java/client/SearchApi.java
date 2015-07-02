package client;

import exception.IndixApiException;
import models.searchResponse.searchResult.*;
import query.Query;

public interface SearchApi {
    /*
     * Search for product and retrieve summary response
     */
    SummarySearchResult getProductsSummary(Query query) throws IndixApiException;

    /*
     * Search for product and retrieve catalog standard response
     */
    OffersSearchResult getProductsOffersStandard(Query query) throws IndixApiException;

    /*
     * Search for product and retrieve offers premium response
     */
    OffersSearchResult getProductsOffersPremium(Query query) throws IndixApiException;

    /*
     * Search for product and retrieve catalog standard response
     */
    CatalogStandardSearchResult getProductsCatalogStandard(Query query) throws IndixApiException;

    /*
     * Search for product and retrieve catalog premium response
     */
    CatalogPremiumSearchResult getProductsCatalogPremium(Query query) throws IndixApiException;

    /*
     * Search for product and retrieve universal response
     */
    UniversalSearchResult getProductsUniversal(Query query) throws IndixApiException;
}
