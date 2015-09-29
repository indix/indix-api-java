package com.indix.models.product;

import com.indix.models.product.productAtStore.CatalogPremiumProductAtStore;

import java.util.Map;

public class CatalogPremiumProduct extends CatalogStandardProduct {
    private Map<String, CatalogPremiumProductAtStore> stores;

    public Map<String, CatalogPremiumProductAtStore> getStores() {
        return stores;
    }
}

