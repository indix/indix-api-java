package models.productDetailsResponse;

import models.ResponseBase;
import models.productDetailsResponse.productDetailsResult.CatalogPremiumProductDetailsResult;

public class CatalogPremiumProductDetailsResponse extends ResponseBase {
    private CatalogPremiumProductDetailsResult result;

    public CatalogPremiumProductDetailsResult getProductDetailsResult() {
        return result;
    }
}
