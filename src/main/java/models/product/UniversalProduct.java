package models.product;

import models.product.productAtStore.UniversalProductAtStore;

import java.util.Map;

public class UniversalProduct extends CatalogStandardProduct {
    private Map<String, UniversalProductAtStore> stores;

    public Map<String, UniversalProductAtStore> getStores() {
        return stores;
    }
}
