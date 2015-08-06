package client;

import exception.IndixApiException;
import models.productHistoryResponse.ProductHistoryResult;
import query.ProductHistoryQuery;

public interface ProductHistoryApi {
    /*
     * Retrieve a product's price history
     */
    ProductHistoryResult getProductHistory(ProductHistoryQuery query) throws IndixApiException;
}
