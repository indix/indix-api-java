package models.metadataResponse;

import models.ResponseBase;
import models.metadataResponse.metadataResult.BrandsResult;

public class BrandsResponse extends ResponseBase {
    private BrandsResult result;

    public BrandsResponse() {
    }

    public BrandsResult getResult() {
        return result;
    }
}
