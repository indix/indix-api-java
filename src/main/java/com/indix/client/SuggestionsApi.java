package com.indix.client;

import com.indix.exception.IndixApiException;
import com.indix.models.suggestions.SuggestionsResult;
import com.indix.query.Query;
import com.indix.query.SearchQuery;

public interface SuggestionsApi {

    /**
     * Search Suggestions - Lists all product search suggestions
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link SuggestionsResult}
     * @throws {@link IndixApiException}
     */
    SuggestionsResult getSuggestions(Query query) throws IndixApiException;
}
