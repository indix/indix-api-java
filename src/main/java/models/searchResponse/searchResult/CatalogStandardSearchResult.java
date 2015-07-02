package models.searchResponse.searchResult;

import models.product.CatalogStandardProduct;

import java.util.List;

public class CatalogStandardSearchResult extends SearchResult {
    private List<CatalogStandardProduct> products;

    public CatalogStandardSearchResult() {
    }

    public List<CatalogStandardProduct> getProducts() {
        return products;
    }
}
