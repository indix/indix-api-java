package com.indix.models.product.productAtStore;

import com.indix.models.product.productAtStore.offer.ProductOfferCatalog;

import java.util.List;

public class CatalogPremiumProductAtStore extends ProductAtStore {
    private List<ProductOfferCatalog> offers;

    public List<ProductOfferCatalog> getOffers() {
        return offers;
    }
}
