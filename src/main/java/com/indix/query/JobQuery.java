package com.indix.query;

public class JobQuery extends QueryBase {

    private int jobId;

    public JobQuery() {
        super();
    }

    /**
     * Job Id to search for
     * @param jobId - Id of job returned against bulk query
     */
    public JobQuery withJobId(int jobId) {
        this.jobId = jobId;
        return this;
    }

    public int getJobId() {
        return jobId;
    }
}
