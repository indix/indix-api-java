package com.indix.query;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class SearchQuery extends QueryBase implements BulkProductsQuery {

    public SearchQuery() {
        super();
    }

    /**
     * Limits results to products sold at stores with given ids.
     * @param storeId The list of storeIds
     */
    public SearchQuery withStoreId(List<Integer> storeId) {
        for (Integer sid : storeId) {
            parameters.add(new BasicNameValuePair("storeId", sid.toString()));
        }
        return this;
    }

    /**
     * Combined with storeId, limits results to products sold at both storeIds and alsoSoldAt storeIds.
     * @param alsoSoldAt The list of storeIds
     */
    public SearchQuery withAlsoSoldAt(List<Integer> alsoSoldAt) {
        for (Integer asa : alsoSoldAt) {
            parameters.add(new BasicNameValuePair("alsoSoldAt", asa.toString()));
        }
        return this;
    }

    /**
     * Limits results to products of brands with given ids.
     * @param brandId The list of brandIds
     */
    public SearchQuery withBrandId(List<Integer> brandId) {
        for (Integer bid : brandId) {
            parameters.add(new BasicNameValuePair("brandId", bid.toString()));
        }
        return this;
    }

    /**
     * Limits results to products of categories with given id.
     * @param categoryId The list of categoryIds
     */
    public SearchQuery withCategoryId(List<Integer> categoryId) {
        for (Integer cid : categoryId) {
            parameters.add(new BasicNameValuePair("categoryId", cid.toString()));
        }
        return this;
    }

    /**
     * Combined with any of query/brand/category/store, limits results to products with specific attribute values
     * NOTE:
     * Examples of a few product attribute keys and values are:
     *      1. color -> red / blue / green / black
     *      2. size -> xs / s / m / l / xl
     *      3. gender -> mens / womens
     * A schema for the filterable keys and values isn't in place yet.
     * When the standard schema is published, the filterable keys for a category and the list of values for the same will be available.
     */
    public SearchQuery withAttrFilter(String attrFilterKey, List<String> attrFilterValues) {
        for (String attrValue: attrFilterValues) {
            parameters.add(new BasicNameValuePair("attr."+attrFilterKey, attrValue));
        }
        return this;
    }

    /**
     * Combined with end_price, limits results to products sold by at least one store at a price between start and end
     */
    public SearchQuery withStartPrice(double startPrice) {
        parameters.add(new BasicNameValuePair("startPrice", String.valueOf(startPrice)));
        return this;
    }

    /**
     * Combined with start_price, limits results to products sold by at least one store at a price between start and end
     */
    public SearchQuery withEndPrice(double endPrice) {
        parameters.add(new BasicNameValuePair("endPrice", String.valueOf(endPrice)));
        return this;
    }

    /**
     * Limits results to either in-stock or out-of-stock items.
     * @param availability {@link com.indix.query.ApiParameters.Availability }
     */
    public SearchQuery withAvailability(Availability availability) {
        parameters.add(new BasicNameValuePair("availability", availability.name()));
        return this;
    }

    /**
     * Limits results to product that have price history available through the products/{:id}/prices endpoint.
     * Accepted values: 'true','false'
     */
    public SearchQuery withPriceHistoryAvailable(boolean priceHistoryAvailable) {
        parameters.add(new BasicNameValuePair("priceHistoryAvailable", String.valueOf(priceHistoryAvailable)));
        return this;
    }

    /**
     * Limits results to products which are on Promotion. Accepted values: 'true','false'
     */
    public SearchQuery withOnPromotion(boolean onPromotion) {
        parameters.add(new BasicNameValuePair("onPromotion", String.valueOf(onPromotion)));
        return this;
    }

    /**
     * Limits results to products which have price recorded in the last xx days. Accepted Value is a
     * non-negative integer
     */
    public SearchQuery withLastRecordedIn(int lastRecordedIn) {
        parameters.add(new BasicNameValuePair("lastRecordedIn", String.valueOf(lastRecordedIn)));
        return this;
    }

    /**
     * Limits results to products with at least a certain number of stores
     */
    public SearchQuery withStoresCount(int storesCount) {
        parameters.add(new BasicNameValuePair("storesCount", String.valueOf(storesCount)));
        return this;
    }

    /**
     * Limits results to products whose prices have been increased or decreased.
     * @param priceChange {@link com.indix.query.ApiParameters.PriceChange}
     */
    public SearchQuery withPriceChange(PriceChange priceChange) {
        parameters.add(new BasicNameValuePair("priceChange", priceChange.name()));
        return this;
    }

    /**
     * Specify the stores for which the offer level filter apply. Allowed values - storeId (apply filters to only
     * stores in storeId), alsoSoldAt (apply filters to only stores in alsoSoldAt), storeIdOrAlsoSoldAt (apply filters
     * to stores present in both storeId and alsoSoldAt), any (apply filters across all offers)
     * @param applyFiltersTo {@link com.indix.query.ApiParameters.ApplyFiltersTo}
     */
    public SearchQuery withApplyFilterTo(ApplyFiltersTo applyFiltersTo) {
        parameters.add(new BasicNameValuePair("applyFiltersTo", applyFiltersTo.name()));
        return this;
    }

    /**
     * Specify the stores for which offers will be returned in the response. Allowed values - storeId (select offers
     * matching filters from storeId), alsoSoldAt (select offers matching filters from alsoSoldAt), storeIdAndAlsoSoldAt
     * (select offers matching filters from stores in both storeId and alsoSoldAt), all (select all offers irrespective
     * of matching or storeId or alsoSoldAt data)
     * @param selectOffersFrom {@link com.indix.query.ApiParameters.SelectOffersFrom}
     */
    public SearchQuery withSelectOffersFrom(SelectOffersFrom selectOffersFrom) {
        parameters.add(new BasicNameValuePair("selectOffersFrom", selectOffersFrom.name()));
        return this;
    }

    /**
     * Limits results to products of the geography with this code. Example 'US', 'GB', etc
     * If the user doesn’t pass a value, the default option returns the US countryCode data
     * @param countryCode String value specifying countrycode
     */
    public SearchQuery withCountryCode(String countryCode) {
        parameters.add(new BasicNameValuePair("countryCode", countryCode));
        return this;
    }

    /**
     * Search by product URL
     */
    public SearchQuery withUrl(String url) {
        parameters.add(new BasicNameValuePair("url", url));
        return this;
    }

    /**
     * Limit results to products with this UPC
     */
    public SearchQuery withUpc(String upc) {
        parameters.add(new BasicNameValuePair("upc", upc));
        return this;
    }

    /**
     * Limit results to products with this MPN
     */
    public SearchQuery withMpn(String mpn) {
        parameters.add(new BasicNameValuePair("mpn", mpn));
        return this;
    }

    /**
     * Limit results to products with this SKU
     */
    public SearchQuery withSku(String sku) {
        parameters.add(new BasicNameValuePair("sku", sku));
        return this;
    }

    /**
     * Search products against the product search term
     */
    public SearchQuery withQ(String q) {
        parameters.add(new BasicNameValuePair("q", q));
        return this;
    }

    /**
     * Specifies the sorting strategy of the results
     * @param sortBy - {@link com.indix.query.ApiParameters.SortBy}
     */
    public SearchQuery withSortBy(SortBy sortBy) {
        parameters.add(new BasicNameValuePair("sortBy", sortBy.name()));
        return this;
    }

    /**
     * Facet by values - storeId, brandId, categoryId.
     */
    public SearchQuery withFacetBy(List<String> facetBy) {
        for (String facet : facetBy) {
            parameters.add(new BasicNameValuePair("facetBy", facet));
        }
        return this;
    }

    /**
     * Facet by product attribute values
     */
    public SearchQuery withAttrFacetBy(List<String> attrFacetBy) {
        for (String attrFacet : attrFacetBy) {
            parameters.add(new BasicNameValuePair("attrFacetBy", "attr."+attrFacet));
        }
        return this;
    }

    /**
     * Specifies the page number of the result set to return. 10 results per page.
     */
    public SearchQuery withPageNumber(int pageNumber) {
        parameters.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
        return this;
    }

    /**
     * Specifies the number of results per page. Allowed values - 10, 20, 50.
     */
    public SearchQuery withPageSize(int pageSize) {
        parameters.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
        return this;
    }
}
