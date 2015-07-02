package models.productDetailsResponse;

import models.ResponseBase;
import models.productDetailsResponse.productDetailsResult.CatalogStandardProductDetailsResult;

public class CatalogStandardProductDetailsResponse extends ResponseBase {
    private CatalogStandardProductDetailsResult result;

    public CatalogStandardProductDetailsResponse() {
    }

    public CatalogStandardProductDetailsResult getResult() {
        return result;
    }
}
