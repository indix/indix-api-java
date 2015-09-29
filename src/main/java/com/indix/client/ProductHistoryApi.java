package com.indix.client;

import com.indix.exception.IndixApiException;
import com.indix.models.productHistoryResponse.ProductHistoryResult;
import com.indix.query.ProductHistoryQuery;

public interface ProductHistoryApi {
    /**
     * Product History - Returns the historical price information recorded for the product
     * @param query Instance of {@link ProductHistoryQuery} with appropriate parameters
     * @return {@link ProductHistoryResult}
     * @throws {@link IndixApiException}
     */
    ProductHistoryResult getProductHistory(ProductHistoryQuery query) throws IndixApiException;
}
