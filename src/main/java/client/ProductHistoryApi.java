package client;

import exception.IndixApiException;
import models.productHistoryResponse.ProductHistoryResult;
import query.ProductHistoryQuery;

public interface ProductHistoryApi {

    ProductHistoryResult getProductHistory(ProductHistoryQuery query) throws IndixApiException;

}
