package com.indix.query;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductDetailsQueryTest {
    @Test
    public void testBasicQuery() {
        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("countryCode", "US"));
        expectedValue.add(new BasicNameValuePair("storeId", "2345"));
        expectedValue.add(new BasicNameValuePair("pageNumber", "5"));

        ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                .withCountryCode("US")
                .withMpid("mpid1")
                .withStoreId(2345)
                .withPageNumber(5);
        List<NameValuePair> actualParameters = productDetailsQuery.getParameters();

        assertEquals("mpid1", productDetailsQuery.getMpid());
        assertTrue(actualParameters.containsAll(expectedValue));
    }
}
