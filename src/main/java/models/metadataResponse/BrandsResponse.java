package models.metadataResponse;

import models.ResponseBase;
import models.metadataResponse.metadataResult.BrandsResult;

public class BrandsResponse extends ResponseBase {
    private BrandsResult result;

    public BrandsResult getResult() {
        return result;
    }
}
