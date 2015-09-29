package com.indix.models.bulkJobResponse;

public class BulkJobOutput {

    private BulkJobResult result;
    private String message;
    private int status;
    private String input;

    public int getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }

    /**
     * @return The output of the current bulk job requested as {@link BulkJobResult}
     */
    public BulkJobResult getResult(){
        return result;
    }

    public String getInput(){
        return input;
    }
}
