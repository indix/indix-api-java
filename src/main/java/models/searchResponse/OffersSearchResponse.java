package models.searchResponse;

import models.ResponseBase;
import models.searchResponse.searchResult.OffersSearchResult;

public class OffersSearchResponse extends ResponseBase {
    private OffersSearchResult result;

    public OffersSearchResponse() {
    }

    public OffersSearchResult getResult() {
        return result;
    }
}
