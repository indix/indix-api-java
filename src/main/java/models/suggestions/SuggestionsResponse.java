package models.suggestions;

import models.ResponseBase;

public class SuggestionsResponse extends ResponseBase {
    private SuggestionsResult result;

    public SuggestionsResponse() {
    }

    public SuggestionsResult getResult() {
        return result;
    }
}
