package models.product;

import models.product.productAtStore.ProductHistoryAtStore;

import java.util.Map;

public class ProductHistory {

    private Map<String, ProductHistoryAtStore> stores;


    public ProductHistory(){
    }

    public Map<String, ProductHistoryAtStore> getStores() {
        return stores;
    }


}
