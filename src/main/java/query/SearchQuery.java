package query;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class SearchQuery extends QueryBase implements BulkProductsQuery {

    public SearchQuery() {
        super();
    }

    enum SortBy {
        PRICE_LOW_TO_HIGH, PRICE_HIGH_TO_LOW, MOST_RECENT
    }

    public SearchQuery withAppId(String app_id) {
        parameters.add(new BasicNameValuePair("app_id", app_id));
        return this;
    }

    public SearchQuery withAppKey(String app_key) {
        parameters.add(new BasicNameValuePair("app_key", app_key));
        return this;
    }

    public SearchQuery withStoreId(List<Integer> storeId) {
        for (Integer sid : storeId) {
            parameters.add(new BasicNameValuePair("storeId", sid.toString()));
        }
        return this;
    }

    public SearchQuery withAlsoSoldAt(List<Integer> alsoSoldAt) {
        for (Integer asa : alsoSoldAt) {
            parameters.add(new BasicNameValuePair("alsoSoldAt", asa.toString()));
        }
        return this;
    }

    public SearchQuery withBrandId(List<Integer> brandId) {
        for (Integer bid : brandId) {
            parameters.add(new BasicNameValuePair("brandId", bid.toString()));
        }
        return this;
    }

    public SearchQuery withCategoryId(List<Integer> categoryId) {
        for (Integer cid : categoryId) {
            parameters.add(new BasicNameValuePair("categoryId", cid.toString()));
        }
        return this;
    }

    public SearchQuery withStartPrice(double startPrice) {
        parameters.add(new BasicNameValuePair("startPrice", String.valueOf(startPrice)));
        return this;
    }

    public SearchQuery withEndPrice(double endPrice) {
        parameters.add(new BasicNameValuePair("endPrice", String.valueOf(endPrice)));
        return this;
    }

    public SearchQuery withAvailability(Availability availability) {
        parameters.add(new BasicNameValuePair("availability", availability.name()));
        return this;
    }

    public SearchQuery withPriceHistoryAvailable(boolean priceHistoryAvailable) {
        parameters.add(new BasicNameValuePair("priceHistoryAvailable", String.valueOf(priceHistoryAvailable)));
        return this;
    }

    public SearchQuery withOnPromotion(boolean onPromotion) {
        parameters.add(new BasicNameValuePair("onPromotion", String.valueOf(onPromotion)));
        return this;
    }

    public SearchQuery withLastRecordedIn(int lastRecordedIn) {
        parameters.add(new BasicNameValuePair("lastRecordedIn", String.valueOf(lastRecordedIn)));
        return this;
    }

    public SearchQuery withStoresCount(int storesCount) {
        parameters.add(new BasicNameValuePair("storesCount", String.valueOf(storesCount)));
        return this;
    }

    public SearchQuery withPriceChange(PriceChange priceChange) {
        parameters.add(new BasicNameValuePair("priceChange", priceChange.name()));
        return this;
    }

    public SearchQuery withApplyFilterTo(ApplyFiltersTo applyFiltersTo) {
        parameters.add(new BasicNameValuePair("applyFiltersTo", applyFiltersTo.name()));
        return this;
    }

    public SearchQuery withSelectOffersFrom(SelectOffersFrom selectOffersFrom) {
        parameters.add(new BasicNameValuePair("selectOffersFrom", selectOffersFrom.name()));
        return this;
    }

    public SearchQuery withCountryCode(String countryCode) {
        parameters.add(new BasicNameValuePair("countryCode", countryCode));
        return this;
    }

    public SearchQuery withUrl(String _url) {
        parameters.add(new BasicNameValuePair("url", _url));
        return this;
    }

    public SearchQuery withUpc(String _upc) {
        parameters.add(new BasicNameValuePair("upc", _upc));
        return this;
    }

    public SearchQuery withMpn(String _mpn) {
        parameters.add(new BasicNameValuePair("mpn", _mpn));
        return this;
    }

    public SearchQuery withSku(String _sku) {
        parameters.add(new BasicNameValuePair("sku", _sku));
        return this;
    }

    public SearchQuery withQ(String _q) {
        parameters.add(new BasicNameValuePair("q", _q));
        return this;
    }

    public SearchQuery withSortBy(SortBy _sortBy) {
        parameters.add(new BasicNameValuePair("sortBy", _sortBy.name()));
        return this;
    }

    public SearchQuery withFacetBy(List<String> _facetBy) {
        for (String _facet : _facetBy) {
            parameters.add(new BasicNameValuePair("facetBy", _facet));
        }
        return this;
    }

    public SearchQuery withPageNumber(int _pageNumber) {
        parameters.add(new BasicNameValuePair("pageNumber", String.valueOf(_pageNumber)));
        return this;
    }

    public SearchQuery withPageSize(int _pageSize) {
        parameters.add(new BasicNameValuePair("pageSize", String.valueOf(_pageSize)));
        return this;
    }
}
