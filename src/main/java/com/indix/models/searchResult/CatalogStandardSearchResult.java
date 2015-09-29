package com.indix.models.searchResult;

import com.indix.models.product.CatalogStandardProduct;

import java.util.List;

public class CatalogStandardSearchResult extends SearchResult {
    private List<CatalogStandardProduct> products;

    public List<CatalogStandardProduct> getProducts() {
        return products;
    }
}
