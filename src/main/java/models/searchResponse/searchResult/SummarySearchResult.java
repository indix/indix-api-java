package models.searchResponse.searchResult;

import models.product.SummaryProduct;

import java.util.List;

public class SummarySearchResult extends SearchResult {
    private List<SummaryProduct> products;

    public List<SummaryProduct> getSummaryProducts() {
        return products;
    }
}
