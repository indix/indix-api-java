package com.indix.models.product.productAtStore;

import com.indix.models.product.productAtStore.offer.ProductOfferHistory;

import java.util.List;

public class ProductHistoryAtStore {

    private List<ProductOfferHistory> offers;
    private int storeId;
    private String storeName;


    public List<ProductOfferHistory> getOffers() {
        return offers;
    }

    public int getStoreId(){
        return storeId;
    }

    public String getStoreName(){
        return storeName;
    }

}
