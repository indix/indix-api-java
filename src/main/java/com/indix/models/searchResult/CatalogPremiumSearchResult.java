package com.indix.models.searchResult;

import com.indix.models.product.CatalogPremiumProduct;

import java.util.List;

public class CatalogPremiumSearchResult extends SearchResult {
    private List<CatalogPremiumProduct> products;

    public List<CatalogPremiumProduct> getProducts() {
        return products;
    }
}
