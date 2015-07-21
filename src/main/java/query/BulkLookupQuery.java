package query;

import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.IOException;

public class BulkLookupQuery extends QueryBase{

    private File jsonfile;

    public BulkLookupQuery withAppId(String app_id) {
        parameters.add(new BasicNameValuePair("app_id", app_id));
        return this;
    }

    public BulkLookupQuery withAppKey(String app_key) {
        parameters.add(new BasicNameValuePair("app_key", app_key));
        return this;
    }

    public BulkLookupQuery withJsonFile(File file) throws IOException {
        this.jsonfile = file;
        return this;
    }

    public BulkLookupQuery withCountryCode(String cc) {
        parameters.add(new BasicNameValuePair("countryCode", cc));
        return this;
    }

    public File getJsonfile(){ return jsonfile; }
}
