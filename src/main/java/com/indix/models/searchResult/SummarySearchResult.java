package com.indix.models.searchResult;

import com.indix.models.product.SummaryProduct;

import java.util.List;

public class SummarySearchResult extends SearchResult {
    private List<SummaryProduct> products;

    public List<SummaryProduct> getProducts() {
        return products;
    }
}
