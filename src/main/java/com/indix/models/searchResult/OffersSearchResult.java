package com.indix.models.searchResult;

import com.indix.models.product.OffersProduct;

import java.util.List;

public class OffersSearchResult extends SearchResult {
    private List<OffersProduct> products;

    public List<OffersProduct> getProducts() {
        return products;
    }
}
