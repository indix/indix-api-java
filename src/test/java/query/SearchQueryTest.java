package query;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchQueryTest {

    @Test
    public void testBasicQuery() {
        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("app_id", "123"));
        expectedValue.add(new BasicNameValuePair("app_key", "123"));
        expectedValue.add(new BasicNameValuePair("countryCode", "US"));
        expectedValue.add(new BasicNameValuePair("q", "nike"));
        expectedValue.add(new BasicNameValuePair("storeId", "270"));
        expectedValue.add(new BasicNameValuePair("storeId", "275"));
        expectedValue.add(new BasicNameValuePair("alsoSoldAt", "24"));
        expectedValue.add(new BasicNameValuePair("brandId", "5575"));
        expectedValue.add(new BasicNameValuePair("categoryId", "10003"));
        expectedValue.add(new BasicNameValuePair("categoryId", "99999"));
        expectedValue.add(new BasicNameValuePair("categoryId", "123456"));
        expectedValue.add(new BasicNameValuePair("url", "url1"));
        expectedValue.add(new BasicNameValuePair("upc", "upc1"));
        expectedValue.add(new BasicNameValuePair("mpn", "mpn1"));
        expectedValue.add(new BasicNameValuePair("sku", "sku1"));
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
        expectedValue.add(new BasicNameValuePair("sortBy", "PRICE_HIGH_TO_LOW"));
        expectedValue.add(new BasicNameValuePair("facetBy", "storeId"));
        expectedValue.add(new BasicNameValuePair("facetBy", "brandId"));
        expectedValue.add(new BasicNameValuePair("pageNumber", "5"));
        expectedValue.add(new BasicNameValuePair("pageSize", "55"));

        Query searchQuery = QueryFactory.newSearchQuery()
                .withQ("nike")
                .withUrl("url1")
                .withUpc("upc1")
                .withMpn("mpn1")
                .withSku("sku1")
                .withSortBy(SearchQuery.SortBy.PRICE_HIGH_TO_LOW)
                .withFacetBy(Arrays.asList("storeId", "brandId"))
                .withPageNumber(5)
                .withPageSize(55)
                .withAppId("123")
                .withAppKey("123")
                .withCountryCode("US")
                .withStoreId(Arrays.asList(270, 275))
                .withAlsoSoldAt(Arrays.asList(24))
                .withBrandId(Arrays.asList(5575))
                .withCategoryId(Arrays.asList(10003, 99999, 123456))
                .withStartPrice(23.45)
                .withEndPrice(112.55)
                .withAvailability(SearchQuery.Availability.IN_STOCK)
                .withPriceHistoryAvailable(true)
                .withPriceChange(SearchQuery.PriceChange.PRICE_INCREASED)
                .withOnPromotion(true)
                .withLastRecordedIn(16)
                .withStoresCount(3)
                .withApplyFilterTo(SearchQuery.ApplyFiltersTo.storeIdOrAlsoSoldAt)
                .withSelectOffersFrom(SearchQuery.SelectOffersFrom.storeIdAndAlsoSoldAt);
        List<NameValuePair> actualParameters = searchQuery.getParameters();

        assertTrue(actualParameters.containsAll(expectedValue));
    }
}
