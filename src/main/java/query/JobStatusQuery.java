package query;

public class JobStatusQuery extends QueryBase {

    private int jobId;

    public JobStatusQuery() {
        super();
    }

    public JobStatusQuery withJobId(int jobId) {
        this.jobId = jobId;
        return this;
    }

    public int getJobId() {
        return jobId;
    }
}
