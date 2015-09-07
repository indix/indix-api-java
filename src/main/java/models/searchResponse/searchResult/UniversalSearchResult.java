package models.searchResponse.searchResult;

import models.product.UniversalProduct;

import java.util.List;

public class UniversalSearchResult extends SearchResult {
    private List<UniversalProduct> products;

    public List<UniversalProduct> getUniversalProducts() {
        return products;
    }
}
