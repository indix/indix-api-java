package com.indix.models.product.productAtStore;

import com.indix.models.product.productAtStore.offer.ProductOfferUniversal;

import java.util.List;

public class UniversalProductAtStore extends ProductAtStore {
    private List<ProductOfferUniversal> offers;

    public List<ProductOfferUniversal> getOffers() {
        return offers;
    }
}
