package models.searchResponse.searchResult;

import models.product.UniversalProduct;

import java.util.List;

public class UniversalSearchResult extends SearchResult {
    private List<UniversalProduct> products;

    public UniversalSearchResult() {
    }

    public List<UniversalProduct> getProducts() {
        return products;
    }
}
