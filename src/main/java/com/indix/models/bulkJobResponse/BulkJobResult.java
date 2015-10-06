package com.indix.models.bulkJobResponse;

import java.util.List;

public class BulkJobResult<T> {
    private int count;
    private List<T> products;

    public int getCount() {
        return count;
    }

    public List<T> getProducts() {
        return products;
    }
}
