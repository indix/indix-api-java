package query;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductHistoryQueryTest {

    @Test
    public void testBasicQuery(){
        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("app_id", "111"));
        expectedValue.add(new BasicNameValuePair("app_key", "111"));
        expectedValue.add(new BasicNameValuePair("countryCode", "US"));
        expectedValue.add(new BasicNameValuePair("storeId", "2345"));
        expectedValue.add(new BasicNameValuePair("mpid", "mpid1"));

        ProductHistoryQuery query = QueryFactory.newProductHistoryQuery()
                .withAppId("111")
                .withAppKey("111")
                .withCountryCode("US")
                .withStoreId(2345)
                .withMpid("mpid1");
        List<NameValuePair> actualParameters = query.getParameters();

        Assert.assertEquals(expectedValue, actualParameters);
    }
}
