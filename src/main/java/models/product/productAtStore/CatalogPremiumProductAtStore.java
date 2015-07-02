package models.product.productAtStore;

import models.product.productAtStore.offer.ProductOfferCatalog;

import java.util.List;

public class CatalogPremiumProductAtStore extends ProductAtStore {
    private List<ProductOfferCatalog> offers;

    public CatalogPremiumProductAtStore() {
    }

    public List<ProductOfferCatalog> getOffers() {
        return offers;
    }
}
