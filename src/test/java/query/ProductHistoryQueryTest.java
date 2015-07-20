package query;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductHistoryQueryTest {

    @Test
    public void testBasicQuery(){
        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("app_id", "111"));
        expectedValue.add(new BasicNameValuePair("app_key", "111"));
        expectedValue.add(new BasicNameValuePair("countryCode", "US"));
        expectedValue.add(new BasicNameValuePair("storeId", "2345"));

        ProductHistoryQuery productHistoryQuery = QueryFactory.newProductHistoryQuery()
                .withAppId("111")
                .withAppKey("111")
                .withCountryCode("US")
                .withStoreId(2345)
                .withMpid("mpid1");
        List<NameValuePair> actualParameters = productHistoryQuery.getParameters();

        assertEquals(expectedValue, actualParameters);
        assertEquals("mpid1", productHistoryQuery.getMpid());
    }
}
