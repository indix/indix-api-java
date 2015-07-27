package query;

import org.apache.http.message.BasicNameValuePair;

public class ProductHistoryQuery extends QueryBase{
    String mpid;

    public ProductHistoryQuery(){
        super();
        mpid = "";
    }

    public ProductHistoryQuery withAppId(String app_id) {
        parameters.add(new BasicNameValuePair("app_id", app_id));
        return this;
    }

    public ProductHistoryQuery withAppKey(String app_key) {
        parameters.add(new BasicNameValuePair("app_key", app_key));
        return this;
    }

    public ProductHistoryQuery withCountryCode(String cc) {
        parameters.add(new BasicNameValuePair("countryCode", cc));
        return this;
    }

    public ProductHistoryQuery withMpid(String mpid) {
        this.mpid = mpid;
        return this;
    }

    public ProductHistoryQuery withStoreId(int storeId) {
        parameters.add(new BasicNameValuePair("storeId", String.valueOf(storeId)));
        return this;
    }

    public String getMpid() { return mpid; }


}
