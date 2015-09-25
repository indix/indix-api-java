package models.searchResult;

import models.product.CatalogPremiumProduct;

import java.util.List;

public class CatalogPremiumSearchResult extends SearchResult {
    private List<CatalogPremiumProduct> products;

    public List<CatalogPremiumProduct> getProducts() {
        return products;
    }
}
