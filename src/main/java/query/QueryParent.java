package query;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class QueryParent<T> extends QueryBase{

    public QueryParent(){
        super();
    }
    
    private T t;

    enum Availability {
        IN_STOCK, OUT_OF_STOCK
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

    public T withAppId(String app_id) {
        parameters.add(new BasicNameValuePair("app_id", app_id));
        return (T)this;
    }

    public T withAppKey(String app_key) {
        parameters.add(new BasicNameValuePair("app_key", app_key));
        return (T)this;
    }

    public T withStoreId(List<Integer> storeId) {
        for (Integer sid : storeId) {
            parameters.add(new BasicNameValuePair("storeId", sid.toString()));
        }
        return (T)this;
    }

    public T withAlsoSoldAt(List<Integer> alsoSoldAt) {
        for (Integer asa : alsoSoldAt) {
            parameters.add(new BasicNameValuePair("alsoSoldAt", asa.toString()));
        }
        return (T)this;
    }

    public T withBrandId(List<Integer> brandId) {
        for (Integer bid : brandId) {
            parameters.add(new BasicNameValuePair("brandId", bid.toString()));
        }
        return (T)this;
    }

    public T withCategoryId(List<Integer> categoryId) {
        for (Integer cid : categoryId) {
            parameters.add(new BasicNameValuePair("categoryId", cid.toString()));
        }
        return (T)this;
    }

    public T withStartPrice(double startPrice) {
        parameters.add(new BasicNameValuePair("startPrice", String.valueOf(startPrice)));
        return (T)this;
    }

    public T withEndPrice(double endPrice) {
        parameters.add(new BasicNameValuePair("endPrice", String.valueOf(endPrice)));
        return (T)this;
    }

    public T withAvailability(Availability availability) {
        parameters.add(new BasicNameValuePair("availability", availability.name()));
        return (T)this;
    }

    public T withPriceHistoryAvailable(boolean priceHistoryAvailable) {
        parameters.add(new BasicNameValuePair("priceHistoryAvailable", String.valueOf(priceHistoryAvailable)));
        return (T)this;
    }

    public T withOnPromotion(boolean onPromotion) {
        parameters.add(new BasicNameValuePair("onPromotion", String.valueOf(onPromotion)));
        return (T)this;
    }

    public T withLastRecordedIn(int lastRecordedIn) {
        parameters.add(new BasicNameValuePair("lastRecordedIn", String.valueOf(lastRecordedIn)));
        return (T)this;
    }

    public T withStoresCount(int storesCount) {
        parameters.add(new BasicNameValuePair("storesCount", String.valueOf(storesCount)));
        return (T)this;
    }

    public T withPriceChange(PriceChange priceChange) {
        parameters.add(new BasicNameValuePair("priceChange", priceChange.name()));
        return (T)this;
    }

    public T withApplyFilterTo(ApplyFiltersTo applyFiltersTo) {
        parameters.add(new BasicNameValuePair("applyFiltersTo", applyFiltersTo.name()));
        return (T)this;
    }

    public T withSelectOffersFrom(SelectOffersFrom selectOffersFrom) {
        parameters.add(new BasicNameValuePair("selectOffersFrom", selectOffersFrom.name()));
        return (T)this;
    }

    public T withCountryCode(String countryCode){
        parameters.add(new BasicNameValuePair("countryCode",countryCode));
        return (T)this;
    }
}
