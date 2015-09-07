package models.searchResponse;

import models.ResponseBase;
import models.searchResponse.searchResult.SummarySearchResult;

public class SummarySearchResponse extends ResponseBase {
    private SummarySearchResult result;

    public SummarySearchResult getSearchResult() {
        return result;
    }
}
