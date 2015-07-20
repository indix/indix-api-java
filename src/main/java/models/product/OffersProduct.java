package models.product;

import models.product.productAtStore.OffersProductAtStore;

import java.util.Map;

public class OffersProduct extends SummaryProduct {
    private Map<String, OffersProductAtStore> stores;

     public Map<String, OffersProductAtStore> getStores() {
        return stores;
    }
}
