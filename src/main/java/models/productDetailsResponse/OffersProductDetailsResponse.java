package models.productDetailsResponse;

import models.ResponseBase;
import models.productDetailsResponse.productDetailsResult.OffersProductDetailsResult;

public class OffersProductDetailsResponse extends ResponseBase {
    private OffersProductDetailsResult result;

    public OffersProductDetailsResponse() {
    }

    public OffersProductDetailsResult getResult() {
        return result;
    }
}
