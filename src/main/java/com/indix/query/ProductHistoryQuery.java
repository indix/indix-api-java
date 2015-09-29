package com.indix.query;

import org.apache.http.message.BasicNameValuePair;

public class ProductHistoryQuery extends QueryBase {
    String mpid;

    public ProductHistoryQuery() {
        super();
        mpid = "";
    }

    /**
     * Limits results to products of the geography with this code. Example: 'US', 'GB', etc
     * If the user doesn’t pass a value, the default option returns the US countryCode data
     */
    public ProductHistoryQuery withCountryCode(String cc) {
        parameters.add(new BasicNameValuePair("countryCode", cc));
        return this;
    }

    /**
     * The product identifier - should be a 32-digit HEX value retrieved via the Product Search API
     */
    public ProductHistoryQuery withMpid(String mpid) {
        this.mpid = mpid;
        return this;
    }

    /**
     * Limit result to price history of the product at this store
     */
    public ProductHistoryQuery withStoreId(int storeId) {
        parameters.add(new BasicNameValuePair("storeId", String.valueOf(storeId)));
        return this;
    }

    public String getMpid() {
        return mpid;
    }
}
