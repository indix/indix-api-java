package models.product;

import models.product.productAtStore.ProductHistoryAtStore;

import java.util.Map;

public class ProductHistory {

    private int categoryId;
    private int brandId;
    private Map<String, ProductHistoryAtStore> stores;


    public int getCategoryId(){
        return categoryId;
    }

    public int getBrandId(){
        return brandId;
    }

    public Map<String, ProductHistoryAtStore> getStores() {
        return stores;
    }

}
