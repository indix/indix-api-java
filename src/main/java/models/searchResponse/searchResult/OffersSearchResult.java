package models.searchResponse.searchResult;

import models.product.OffersProduct;

import java.util.List;

public class OffersSearchResult extends SearchResult {
    private List<OffersProduct> products;

    public OffersSearchResult() {
    }

    public List<OffersProduct> getProducts() {
        return products;
    }
}
