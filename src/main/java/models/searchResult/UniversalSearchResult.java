package models.searchResult;

import models.product.UniversalProduct;

import java.util.List;

public class UniversalSearchResult extends SearchResult {
    private List<UniversalProduct> products;

    public List<UniversalProduct> getProducts() {
        return products;
    }
}
