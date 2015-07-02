package query;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsQueryTest {
    @Test
    public void testBasicQuery() {
        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("app_id", "123"));
        expectedValue.add(new BasicNameValuePair("app_key", "123"));
        expectedValue.add(new BasicNameValuePair("countryCode", "US"));
        expectedValue.add(new BasicNameValuePair("storeId", "2345"));
        expectedValue.add(new BasicNameValuePair("pageNumber", "5"));

        ProductDetailsQuery productDetailsQuery = QueryFactory.newProductDetailsQuery()
                .withAppId("123")
                .withAppKey("123")
                .withCountryCode("US")
                .withMpid("mpid1")
                .withStoreId(2345)
                .withPageNumber(5);
        List<NameValuePair> actualParameters = productDetailsQuery.getParameters();

        Assert.assertEquals("mpid1", productDetailsQuery.getMpid());
        Assert.assertEquals(expectedValue, actualParameters);
    }
}
