package models.metadataResponse;

import models.ResponseBase;
import models.metadataResponse.metadataResult.CategoriesResult;

public class CategoriesResponse extends ResponseBase {
    private CategoriesResult result;

    public CategoriesResult getResult() {
        return result;
    }
}
