package models.productHistoryResponse;

import models.ResponseBase;

public class ProductHistoryResponse extends ResponseBase{
    private ProductHistoryResult result;

    public ProductHistoryResponse() {
    }

    public ProductHistoryResult getResult() {
        return result;
    }
}
