package query;

import org.apache.http.message.BasicNameValuePair;

public class ProductHistoryQuery extends QueryBase{
    String mpid;

    public ProductHistoryQuery(){
        super();
        mpid = "";
    }

    public ProductHistoryQuery withAppId(String _app_id) {
        parameters.add(new BasicNameValuePair("app_id", _app_id));
        return this;
    }

    public ProductHistoryQuery withAppKey(String _app_key) {
        parameters.add(new BasicNameValuePair("app_key", _app_key));
        return this;
    }

    public ProductHistoryQuery withCountryCode(String _cc) {
        parameters.add(new BasicNameValuePair("countryCode", _cc));
        return this;
    }

    public ProductHistoryQuery withMpid(String _mpid) {
        parameters.add(new BasicNameValuePair("mpid", _mpid));
        return this;
    }

    public ProductHistoryQuery withStoreId(int _storeId) {
        parameters.add(new BasicNameValuePair("storeId", String.valueOf(_storeId)));
        return this;
    }


}
