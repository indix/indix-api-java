package query;

import org.apache.http.message.BasicNameValuePair;

/**
 * Index Metadata Query
 */
public class MetadataQuery extends QueryBase {

    public MetadataQuery() {
        super();
    }
    
    public MetadataQuery withQ(String _q) {
        parameters.add(new BasicNameValuePair("q", _q));
        return this;
    }
}
