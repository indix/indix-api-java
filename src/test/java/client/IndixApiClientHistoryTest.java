package client;


import client.impl.IndixApiClientFactory;
import common.ResourceUtils;
import exception.IndixApiException;
import httpClient.HttpClient;
import models.product.productAtStore.ProductHistoryAtStore;
import models.product.productAtStore.offer.ProductOfferHistory;
import org.junit.Test;
import org.junit.Assert;
import query.ProductHistoryQuery;
import query.QueryFactory;

import java.io.IOException;
import java.net.URI;

public class IndixApiClientHistoryTest {

    @Test
    public void getProductHistory() throws IOException, IndixApiException{
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "productHistory-json-responses0/productHistory.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            ProductHistoryQuery productHistoryQuery = QueryFactory.newProductHistoryQuery()
                    .withAppId("123")
                    .withAppKey("123")
                    .withCountryCode("US")
                    .withStoreId(78)
                    .withMpid("01758f3b8f1ba9e1b49a3b7aaa93d425");
            ProductHistoryAtStore productHistoryRecord = indixApiClient.getProductHistory(productHistoryQuery).getProduct().getStores().get("111");
            ProductOfferHistory productOfferHistory = productHistoryRecord.getOffers().get(0);

            Assert.assertEquals(productOfferHistory.getListPriceHistory().toArray().length, 3);
            Assert.assertEquals(productOfferHistory.getSalePriceHistory().toArray().length,3);
            Assert.assertEquals(productOfferHistory.getPid(),"pid1");
            Assert.assertEquals(productOfferHistory.getTimestampHistory().toArray().length,3);

            Assert.assertEquals(productHistoryRecord.getStoreId(), 111);
            Assert.assertEquals(productHistoryRecord.getStoreName(),"storeName");


        } finally {
            indixApiClient.close();
        }
    }
}
