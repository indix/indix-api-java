package com.indix.models.product;

import com.indix.models.product.productAtStore.OffersProductAtStore;

import java.util.Map;

public class OffersProduct extends SummaryProduct {
    private Map<String, OffersProductAtStore> stores;

    public String getTitle() {
        return title;
    }

    private String title;


    public Map<String, OffersProductAtStore> getStores() {
        return stores;
    }
}
