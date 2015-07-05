package client;

import exception.IndixApiException;
import models.suggestions.SuggestionsResult;
import query.Query;

public interface SuggestionsApi {

    /*
     * Search for product title suggestions
     */
    SuggestionsResult getSuggestions(Query query) throws IndixApiException;
}
