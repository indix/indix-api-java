package models.searchResponse;

import models.ResponseBase;
import models.searchResponse.searchResult.UniversalSearchResult;

public class UniversalSearchResponse extends ResponseBase {
    private UniversalSearchResult result;

    public UniversalSearchResult getResult() {
        return result;
    }
}
