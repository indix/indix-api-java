package models.productDetailsResponse;

import models.ResponseBase;
import models.productDetailsResponse.productDetailsResult.SummaryProductDetailsResult;

public class SummaryProductDetailsResponse extends ResponseBase {
    private SummaryProductDetailsResult result;

    public SummaryProductDetailsResult getResult() {
        return result;
    }
}
