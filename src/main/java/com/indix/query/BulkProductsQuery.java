package com.indix.query;

import java.util.List;

public interface BulkProductsQuery extends ApiParameters {

    /**
     * Limits results to products sold at store with id. Supports multiple values (acts as OR operator)
     */
    public SearchQuery withStoreId(List<Integer> storeId);

    /**
     * Combined with storeId, limits results to products sold at both storeIds and alsoSoldAt storeIds.
     * @param alsoSoldAt - The list of alsoSoldAt values predefined in {@link com.indix.query.ApiParameters}
     */
    public SearchQuery withAlsoSoldAt(List<Integer> alsoSoldAt);

    /**
     * Limits results to products of brands with given ids.
     */
    public SearchQuery withBrandId(List<Integer> brandId);

    /**
     * Limits results to products of categories with given id.
     */
    public SearchQuery withCategoryId(List<Integer> categoryId);

    /**
     * Combined with end_price, limits results to products sold by at least one store at a price between start and end
     */
    public SearchQuery withStartPrice(double startPrice);

    /**
     * Combined with end_price, limits results to products sold by at least one store at a price between start and end
     */
    public SearchQuery withEndPrice(double endPrice);

    /**
     * Limits results to either in-stock or out-of-stock items. Accepted values: 'IN_STOCK','OUT_OF_STOCK'
     */
    public SearchQuery withAvailability(Availability availability);

    /**
     * Limits results to product that have price history available through the products/{:id}/prices endpoint.
     */
    public SearchQuery withPriceHistoryAvailable(boolean priceHistoryAvailable);

    /**
     * Limits results to products which are on Promotion. Accepts boolean values
     */
    public SearchQuery withOnPromotion(boolean onPromotion);

    /**
     * Limits results to products which have price recorded in the last xx days. Accepted Value is a
     * non-negative integer
     */
    public SearchQuery withLastRecordedIn(int lastRecordedIn);

    /**
     * Limits results to products with at least a certain number of stores
     */
    public SearchQuery withStoresCount(int storesCount);

    /**
     * Limits results to products whose prices have been increased or decreased.
     * @param priceChange - The list of alsoSoldAt values predefined in {@link com.indix.query.ApiParameters}
     */
    public SearchQuery withPriceChange(PriceChange priceChange);

    /**
     * Specify the stores for which the offer level filter apply. Allowed values - storeId (apply filters to only
     * stores in storeId), alsoSoldAt (apply filters to only stores in alsoSoldAt), storeIdOrAlsoSoldAt (apply filters
     * to stores present in both storeId and alsoSoldAt), any (apply filters across all offers)
     * @param applyFiltersTo - {@link com.indix.query.ApiParameters.ApplyFiltersTo}
     */
    public SearchQuery withApplyFilterTo(ApplyFiltersTo applyFiltersTo);

    /**
     * Specify the stores for which offers will be returned in the response. Allowed values - storeId (select offers
     * matching filters from storeId), alsoSoldAt (select offers matching filters from alsoSoldAt), storeIdAndAlsoSoldAt
     * (select offers matching filters from stores in both storeId and alsoSoldAt), all (select all offers irrespective
     * of matching or storeId or alsoSoldAt data)
     * @param selectOffersFrom - {@link com.indix.query.ApiParameters.SelectOffersFrom}
     */
    public SearchQuery withSelectOffersFrom(SelectOffersFrom selectOffersFrom);

    /**
     * Limits results to products of the geography with this code. Example: 'US', 'GB', etc.
     * If the user doesn’t pass a value, the default option returns the US countryCode data
     * @param countryCode - String value specifying countrycode
     */
    public SearchQuery withCountryCode(String countryCode);
}
