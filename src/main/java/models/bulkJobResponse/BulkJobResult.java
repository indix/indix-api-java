package models.bulkJobResponse;

import models.product.UniversalProduct;

import java.util.List;

public class BulkJobResult {
    private int count;
    private List<UniversalProduct> products;

    public int getCount() {
        return count;
    }

    public List<UniversalProduct> getProducts() {
        return products;
    }
}
