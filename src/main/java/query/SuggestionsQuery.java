package query;

import org.apache.http.message.BasicNameValuePair;

public class SuggestionsQuery extends QueryBase {

    public SuggestionsQuery() {
        super();
    }

    public SuggestionsQuery withAppId(String _app_id) {
        parameters.add(new BasicNameValuePair("app_id", _app_id));
        return this;
    }

    public SuggestionsQuery withAppKey(String _app_key) {
        parameters.add(new BasicNameValuePair("app_key", _app_key));
        return this;
    }

    public SuggestionsQuery withQ(String _q) {
        parameters.add(new BasicNameValuePair("q", _q));
        return this;
    }

    public SuggestionsQuery withCountryCode(String _cc) {
        parameters.add(new BasicNameValuePair("countryCode", _cc));
        return this;
    }

}
