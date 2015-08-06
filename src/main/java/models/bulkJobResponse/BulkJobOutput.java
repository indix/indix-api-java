package models.bulkJobResponse;

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
    public BulkJobResult getResult(){
        return result;
    }
    public String getInput(){
        return input;
    }
}
