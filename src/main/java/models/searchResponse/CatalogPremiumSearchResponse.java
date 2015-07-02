package models.searchResponse;

import models.ResponseBase;
import models.searchResponse.searchResult.CatalogPremiumSearchResult;

public class CatalogPremiumSearchResponse extends ResponseBase {
    private CatalogPremiumSearchResult result;

    public CatalogPremiumSearchResponse() {
    }

    public CatalogPremiumSearchResult getResult() {
        return result;
    }
}
