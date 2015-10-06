package com.indix.models.bulkJobResponse;

public class BulkJobOutput<T> {

    private BulkJobResult<T> result;
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
    public BulkJobResult<T> getResult(){
        return result;
    }

    public String getInput(){
        return input;
    }
}
