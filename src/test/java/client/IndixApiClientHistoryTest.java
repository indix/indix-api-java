package client;


import client.impl.IndixApiClientFactory;
import exception.IndixApiException;
import httpClient.HttpClient;
import models.product.productAtStore.ProductHistoryAtStore;
import models.product.productAtStore.offer.ProductOfferHistory;
import org.junit.Test;
import query.ProductHistoryQuery;
import query.QueryFactory;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IndixApiClientHistoryTest {

    @Test
    public void getProductHistory() throws IOException, IndixApiException {

        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("productHistory-json-responses0/productHistory.json");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            ProductHistoryQuery productHistoryQuery = QueryFactory.newProductHistoryQuery()
                    .withAppId("123")
                    .withAppKey("123")
                    .withCountryCode("US")
                    .withStoreId(78)
                    .withMpid("01758f");
            ProductHistoryAtStore productHistoryRecord = indixApiClient.getProductHistory(productHistoryQuery).getProduct().getStores().get("111");
            ProductOfferHistory productOfferHistory = productHistoryRecord.getOffers().get(0);

            assertThat(productOfferHistory.getSalePriceHistory(), hasItems(8.99, 9.99, 12.99));
            assertThat(productOfferHistory.getListPriceHistory(), hasItems(9.99, 10.99, 13.99));
            assertThat(productOfferHistory.getPid(), is("pid1"));
            assertThat(productOfferHistory.getTimestampHistory(), hasItems(1436918399999L, 1436399999999L, 1434585599999L));

            assertThat(productHistoryRecord.getStoreId(), is(111));
            assertThat(productHistoryRecord.getStoreName(), is("storeName"));


        } finally {
            indixApiClient.close();
        }
    }
}
