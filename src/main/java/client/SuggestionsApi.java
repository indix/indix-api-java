package client;

import exception.IndixApiException;
import models.suggestions.SuggestionsResult;
import query.Query;
import query.SearchQuery;

public interface SuggestionsApi {

    /**
     * Search Suggestions - Lists all product search suggestions
     * @param query Instance of {@link SearchQuery} with appropriate parameters
     * @return {@link SuggestionsResult}
     * @throws {@link IndixApiException}
     */
    SuggestionsResult getSuggestions(Query query) throws IndixApiException;
}
