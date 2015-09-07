package query;

import org.apache.http.message.BasicNameValuePair;

public class SuggestionsQuery extends QueryBase {

    public SuggestionsQuery() {
        super();
    }

    /**
     * Accepts query term for which suggestions are to be provided
     */
    public SuggestionsQuery withQ(String q) {
        parameters.add(new BasicNameValuePair("q", q));
        return this;
    }

    /**
     * Limits results to products of the geography with this code. Example 'US', 'GB', etc
     * If the user doesn’t pass a value, the default option returns the US countryCode data
     * @param cc String value specifying countrycode
     */
    public SuggestionsQuery withCountryCode(String cc) {
        parameters.add(new BasicNameValuePair("countryCode", cc));
        return this;
    }
}
