package models.searchResponse;

import models.ResponseBase;
import models.searchResponse.searchResult.CatalogStandardSearchResult;

public class CatalogStandardSearchResponse extends ResponseBase {
    private CatalogStandardSearchResult result;

    public CatalogStandardSearchResponse() {
    }

    public CatalogStandardSearchResult getResult() {
        return result;
    }
}
