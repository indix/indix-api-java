package query;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 * Index SearchQuery
 */
public class SearchQuery extends QueryBase {

    enum Availability {
        IN_STOCK, OUT_OF_STOCK
    }

    enum SortBy {
        PRICE_LOW_TO_HIGH, PRICE_HIGH_TO_LOW, MOST_RECENT
    }

    enum PriceChange {
        PRICE_INCREASED, PRICE_DECREASED, EITHER
    }

    enum ApplyFiltersTo {
        storeId, alsoSoldAt, storeIdOrAlsoSoldAt, any
    }

    enum SelectOffersFrom {
        storeId, alsoSoldAt, storeIdAndAlsoSoldAt, all
    }

    public SearchQuery() {
        super();
    }
    
    public SearchQuery withAppId(String _app_id) {
        parameters.add(new BasicNameValuePair("app_id", _app_id));
        return this;
    }

    public SearchQuery withAppKey(String _app_key) {
        parameters.add(new BasicNameValuePair("app_key", _app_key));
        return this;
    }

    public SearchQuery withCountryCode(String _cc) {
        parameters.add(new BasicNameValuePair("countryCode", _cc));
        return this;
    }

    public SearchQuery withQ(String _q) {
        parameters.add(new BasicNameValuePair("q", _q));
        return this;
    }

    public SearchQuery withStoreId(List<Integer> _storeId) {
        for (Integer _sid : _storeId) {
            parameters.add(new BasicNameValuePair("storeId", _sid.toString()));
        }
        return this;
    }

    public SearchQuery withAlsoSoldAt(List<Integer> _alsoSoldAt) {
        for (Integer _asa : _alsoSoldAt) {
            parameters.add(new BasicNameValuePair("alsoSoldAt", _asa.toString()));
        }
        return this;
    }

    public SearchQuery withBrandId(List<Integer> _brandId) {
        for (Integer _bid : _brandId) {
            parameters.add(new BasicNameValuePair("brandId", _bid.toString()));
        }
        return this;
    }

    public SearchQuery withCategoryId(List<Integer> _categoryId) {
        for (Integer _cid : _categoryId) {
            parameters.add(new BasicNameValuePair("categoryId", _cid.toString()));
        }
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

    public SearchQuery withStartPrice(double _startPrice) {
        parameters.add(new BasicNameValuePair("startPrice", String.valueOf(_startPrice)));
        return this;
    }

    public SearchQuery withEndPrice(double _endPrice) {
        parameters.add(new BasicNameValuePair("endPrice", String.valueOf(_endPrice)));
        return this;
    }

    public SearchQuery withAvailability(Availability _availability) {
        parameters.add(new BasicNameValuePair("availability", _availability.name()));
        return this;
    }

    public SearchQuery withPriceHistoryAvailable(boolean _priceHistoryAvailable) {
        parameters.add(new BasicNameValuePair("priceHistoryAvailable", String.valueOf(_priceHistoryAvailable)));
        return this;
    }

    public SearchQuery withPriceChange(PriceChange _priceChange) {
        parameters.add(new BasicNameValuePair("priceChange", _priceChange.name()));
        return this;
    }

    public SearchQuery withOnPromotion(boolean _onPromotion) {
        parameters.add(new BasicNameValuePair("onPromotion", String.valueOf(_onPromotion)));
        return this;
    }

    public SearchQuery withLastRecordedIn(int _lastRecordedIn) {
        parameters.add(new BasicNameValuePair("lastRecordedIn", String.valueOf(_lastRecordedIn)));
        return this;
    }

    public SearchQuery withStoresCount(int _storesCount) {
        parameters.add(new BasicNameValuePair("storesCount", String.valueOf(_storesCount)));
        return this;
    }

    public SearchQuery withApplyFilterTo(ApplyFiltersTo _applyFiltersTo) {
        parameters.add(new BasicNameValuePair("applyFiltersTo", _applyFiltersTo.name()));
        return this;
    }

    public SearchQuery withSelectOffersFrom(SelectOffersFrom _selectOffersFrom) {
        parameters.add(new BasicNameValuePair("selectOffersFrom", _selectOffersFrom.name()));
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
