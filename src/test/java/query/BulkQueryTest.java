package query;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BulkQueryTest {

    @Test
    public void testBasicBulkQuery() {
        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("app_id", "123"));
        expectedValue.add(new BasicNameValuePair("app_key", "123"));
        expectedValue.add(new BasicNameValuePair("countryCode", "US"));
        expectedValue.add(new BasicNameValuePair("storeId", "270"));
        expectedValue.add(new BasicNameValuePair("storeId", "275"));
        expectedValue.add(new BasicNameValuePair("alsoSoldAt", "24"));
        expectedValue.add(new BasicNameValuePair("brandId", "5575"));
        expectedValue.add(new BasicNameValuePair("categoryId", "10003"));
        expectedValue.add(new BasicNameValuePair("categoryId", "99999"));
        expectedValue.add(new BasicNameValuePair("categoryId", "123456"));
        expectedValue.add(new BasicNameValuePair("startPrice", "23.45"));
        expectedValue.add(new BasicNameValuePair("endPrice", "112.55"));
        expectedValue.add(new BasicNameValuePair("availability", "IN_STOCK"));
        expectedValue.add(new BasicNameValuePair("priceHistoryAvailable", "true"));
        expectedValue.add(new BasicNameValuePair("priceChange", "PRICE_INCREASED"));
        expectedValue.add(new BasicNameValuePair("onPromotion", "true"));
        expectedValue.add(new BasicNameValuePair("lastRecordedIn", "16"));
        expectedValue.add(new BasicNameValuePair("storesCount", "3"));
        expectedValue.add(new BasicNameValuePair("applyFiltersTo", "storeIdOrAlsoSoldAt"));
        expectedValue.add(new BasicNameValuePair("selectOffersFrom", "storeIdAndAlsoSoldAt"));

        Query bulkQuery = QueryFactory.newBulkQuery()
                .withAppId("123")
                .withAppKey("123")
                .withCountryCode("US")
                .withStoreId(Arrays.asList(270, 275))
                .withAlsoSoldAt(Arrays.asList(24))
                .withBrandId(Arrays.asList(5575))
                .withCategoryId(Arrays.asList(10003, 99999, 123456))
                .withStartPrice(23.45)
                .withEndPrice(112.55)
                .withAvailability(BulkSearchQuery.Availability.IN_STOCK)
                .withPriceHistoryAvailable(true)
                .withPriceChange(BulkSearchQuery.PriceChange.PRICE_INCREASED)
                .withOnPromotion(true)
                .withLastRecordedIn(16)
                .withStoresCount(3)
                .withApplyFilterTo(BulkSearchQuery.ApplyFiltersTo.storeIdOrAlsoSoldAt)
                .withSelectOffersFrom(BulkSearchQuery.SelectOffersFrom.storeIdAndAlsoSoldAt);
        List<NameValuePair> actualParameters = bulkQuery.getParameters();

        assertEquals(expectedValue, actualParameters);
    }

    @Test
    public void testBasicBulkLookupQuery() throws IOException{

        File file = new File("/home/indix/Desktop/repos/scarlet-client/src/test/resources/bulkQuery-json-responses0/bulkLookupInput.jsonl");
        FileInputStream inputStream =new FileInputStream(file);
        StringBuilder builder = new StringBuilder();
        int ch;
        while((ch = inputStream.read()) != -1){
            builder.append((char)ch);
        }

        String fileText= "{\"upc\":\"00049022632813\"}\n" +
                "{\"sku\":\"OMB0001-CHIBAR-S12PAC\",\"storeId\":323}\n" +
                "{\"mpn\":\"1C60NV\",\"brandId\":37600}";

        BulkLookupQuery bulkLookupQuery = QueryFactory.newBulkLookupQuery()
                .withAppId("123")
                .withAppKey("123")
                .withCountryCode("US")
                .withJsonFile(file);

        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("app_id", "123"));
        expectedValue.add(new BasicNameValuePair("app_key", "123"));
        expectedValue.add(new BasicNameValuePair("countryCode", "US"));

        List<NameValuePair> actualParameters = bulkLookupQuery.getParameters();
        assertEquals(expectedValue, actualParameters);
        assertEquals(builder.toString(),fileText);
    }

    @Test
    public void testJobStatusQuery() throws IOException{

       JobStatusQuery jobStatusQuery = QueryFactory.newJobStatusQuery()
                .withJobId(123);

        assertEquals(123, jobStatusQuery.getJobId());
    }

}
