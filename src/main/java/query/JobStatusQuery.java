package query;

public class JobStatusQuery extends QueryBase {

    private int jobId;

    public JobStatusQuery() {
        super();
    }

    /**
     * Job Id to search for
     * @param jobId - Id of job returned against bulk query
     */
    public JobStatusQuery withJobId(int jobId) {
        this.jobId = jobId;
        return this;
    }

    public int getJobId() {
        return jobId;
    }
}
