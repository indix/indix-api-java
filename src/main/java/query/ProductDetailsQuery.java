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
