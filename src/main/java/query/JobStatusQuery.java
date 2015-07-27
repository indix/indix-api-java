package query;

import org.apache.http.message.BasicNameValuePair;

public class JobStatusQuery extends QueryBase {

    private int jobId;

    public JobStatusQuery() {
        super();
    }

    public JobStatusQuery withAppId(String app_id) {
        parameters.add(new BasicNameValuePair("app_id", app_id));
        return this;
    }

    public JobStatusQuery withAppKey(String app_key) {
        parameters.add(new BasicNameValuePair("app_key", app_key));
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
