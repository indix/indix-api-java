package models.searchResponse;

import models.ResponseBase;
import models.searchResponse.searchResult.SummarySearchResult;

public class SummarySearchResponse extends ResponseBase {
    private SummarySearchResult result;

    public SummarySearchResponse() {
    }

    public SummarySearchResult getResult() {
        return result;
    }
}
