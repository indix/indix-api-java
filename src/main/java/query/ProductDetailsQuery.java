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

    /**
     * Limits results to products of the geography with this code. Example: 'US', 'GB', etc.
     * If the user doesn’t pass a value, the default option returns the US countryCode data
     */
    public ProductDetailsQuery withCountryCode(String cc) {
        parameters.add(new BasicNameValuePair("countryCode", cc));
        return this;
    }

    /**
     * The Indix product identifier - should be a 32-digit HEX value retrieved via the Product Search API
     */
    public ProductDetailsQuery withMpid(String mpid) {
        this.mpid = mpid;
        return this;
    }

    /**
     * Limits results to offers from this store
     */
    public ProductDetailsQuery withStoreId(int storeId) {
        parameters.add(new BasicNameValuePair("storeId", String.valueOf(storeId)));
        return this;
    }

    /**
     * The page number of the result set to return. 50 results per page, plus similar items
     */
    public ProductDetailsQuery withPageNumber(int pageNumber) {
        parameters.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
        return this;
    }

    public String getMpid() {
        return mpid;
    }
}
