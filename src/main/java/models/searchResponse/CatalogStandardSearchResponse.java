package models.searchResponse;

import models.ResponseBase;
import models.searchResponse.searchResult.CatalogStandardSearchResult;

public class CatalogStandardSearchResponse extends ResponseBase {
    private CatalogStandardSearchResult result;

    public CatalogStandardSearchResult getSearchResult() {
        return result;
    }
}
