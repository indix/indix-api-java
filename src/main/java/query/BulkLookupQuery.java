package query;

import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.IOException;

public class BulkLookupQuery extends QueryBase {

    private File inputFile;

    public BulkLookupQuery withInputFile(File file) throws IOException {
        this.inputFile = file;
        return this;
    }

    public BulkLookupQuery withCountryCode(String cc) {
        parameters.add(new BasicNameValuePair("countryCode", cc));
        return this;
    }

    public File getInputFile() {
        return inputFile;
    }
}
