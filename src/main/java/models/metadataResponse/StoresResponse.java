package models.metadataResponse;

import models.ResponseBase;
import models.metadataResponse.metadataResult.StoresResult;

public class StoresResponse extends ResponseBase {
    private StoresResult result;

    public StoresResult getResult() {
        return result;
    }
}
