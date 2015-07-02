package query;

import org.apache.http.message.BasicNameValuePair;

/**
 * Index SearchQuery
 */
public class ProductDetailsQuery extends QueryBase {
    String mpid;

    public ProductDetailsQuery() {
        super();
        mpid = "";
    }
    
    public ProductDetailsQuery withAppId(String _app_id) {
        parameters.add(new BasicNameValuePair("app_id", _app_id));
        return this;
    }

    public ProductDetailsQuery withAppKey(String _app_key) {
        parameters.add(new BasicNameValuePair("app_key", _app_key));
        return this;
    }

    public ProductDetailsQuery withCountryCode(String _cc) {
        parameters.add(new BasicNameValuePair("countryCode", _cc));
        return this;
    }

    public ProductDetailsQuery withMpid(String _mpid) {
        mpid = _mpid;
        return this;
    }

    public ProductDetailsQuery withStoreId(int _storeId) {
        parameters.add(new BasicNameValuePair("storeId", String.valueOf(_storeId)));
        return this;
    }

    public ProductDetailsQuery withPageNumber(int _pageNumber) {
        parameters.add(new BasicNameValuePair("pageNumber", String.valueOf(_pageNumber)));
        return this;
    }

    public String getMpid() {
        return mpid;
    }
}
