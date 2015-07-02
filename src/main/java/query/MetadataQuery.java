package query;

import org.apache.http.message.BasicNameValuePair;

/**
 * Index Metadata Query
 */
public class MetadataQuery extends QueryBase {

    public MetadataQuery() {
        super();
    }
    
    public MetadataQuery withAppId(String _app_id) {
        parameters.add(new BasicNameValuePair("app_id", _app_id));
        return this;
    }

    public MetadataQuery withAppKey(String _app_key) {
        parameters.add(new BasicNameValuePair("app_key", _app_key));
        return this;
    }

    public MetadataQuery withQ(String _q) {
        parameters.add(new BasicNameValuePair("q", _q));
        return this;
    }
}
