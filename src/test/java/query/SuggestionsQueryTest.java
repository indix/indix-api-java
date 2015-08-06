package query;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsQueryTest {
    @Test
    public void testBasicQuery() {
        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("app_id", "123"));
        expectedValue.add(new BasicNameValuePair("app_key", "123"));
        expectedValue.add(new BasicNameValuePair("q", "ad"));
        expectedValue.add(new BasicNameValuePair("countryCode", "IN"));

        SuggestionsQuery productDetailsQuery = QueryFactory.newSuggestionsQuery()
                .withAppId("123")
                .withAppKey("123")
                .withQ("ad")
                .withCountryCode("IN");
        List<NameValuePair> actualParameters = productDetailsQuery.getParameters();

        Assert.assertTrue(actualParameters.containsAll(expectedValue));
    }
}
