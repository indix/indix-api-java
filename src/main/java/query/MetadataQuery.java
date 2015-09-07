package query;

import org.apache.http.message.BasicNameValuePair;

/**
 * Index Metadata Query
 */
public class MetadataQuery extends QueryBase {

    public MetadataQuery() {
        super();
    }

    /**
     * Search term to match against metadata query. Partial match. Case insensitive.
     */
    public MetadataQuery withQ(String q) {
        parameters.add(new BasicNameValuePair("q", q));
        return this;
    }
}
