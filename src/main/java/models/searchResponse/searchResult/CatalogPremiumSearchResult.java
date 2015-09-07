package models.searchResponse.searchResult;

import models.product.CatalogPremiumProduct;

import java.util.List;

public class CatalogPremiumSearchResult extends SearchResult {
    private List<CatalogPremiumProduct> products;

    public List<CatalogPremiumProduct> getCatalogPremiumProducts() {
        return products;
    }
}
