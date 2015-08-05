package query;

import org.apache.http.message.BasicNameValuePair;

public class JobStatusQuery extends QueryBase {

    private int jobId;

    public JobStatusQuery() {
        super();
    }

    public JobStatusQuery withAppId(String appId) {
        parameters.add(new BasicNameValuePair("app_id", appId));
        return this;
    }

    public JobStatusQuery withAppKey(String appKey) {
        parameters.add(new BasicNameValuePair("app_key", appKey));
        return this;
    }

    public JobStatusQuery withJobId(int jobId) {
        this.jobId = jobId;
        return this;
    }

    public int getJobId() {
        return jobId;
    }
}
