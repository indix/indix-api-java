package com.indix.client;

import com.indix.exception.IndixApiException;
import com.indix.models.offersResult.OffersResult;
import com.indix.query.SqlQuery;

import java.io.IOException;
import java.net.URISyntaxException;

public interface OffersApi {
    /**
     * Offers - Retrieves a list of offers matching the given sql query
     * @param query Instance of {@link SqlQuery}
     * @return {@link OffersResult}
     * @throws {@link IndixApiException}
     */
    OffersResult getOffers(SqlQuery query) throws IndixApiException, IOException, URISyntaxException;
}
