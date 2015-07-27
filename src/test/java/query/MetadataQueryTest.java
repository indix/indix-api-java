package query;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MetadataQueryTest {
    @Test
    public void testBasicQuery() {
        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("app_id", "123"));
        expectedValue.add(new BasicNameValuePair("app_key", "123"));
        expectedValue.add(new BasicNameValuePair("q", "ni"));

        MetadataQuery productDetailsQuery = QueryFactory.newMetadataQuery()
                .withAppId("123")
                .withAppKey("123")
                .withQ("ni");
        List<NameValuePair> actualParameters = productDetailsQuery.getParameters();

        Assert.assertTrue(actualParameters.containsAll(expectedValue));
    }
}
