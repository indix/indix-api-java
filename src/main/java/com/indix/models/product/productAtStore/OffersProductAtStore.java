package com.indix.models.product.productAtStore;

import com.indix.models.product.productAtStore.offer.ProductOfferPricing;

import java.util.List;

public class OffersProductAtStore extends ProductAtStore {
    private List<ProductOfferPricing> offers;

    public List<ProductOfferPricing> getOffers() {
        return offers;
    }
}
