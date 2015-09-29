package com.indix.query;

import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.IOException;

public class BulkLookupQuery extends QueryBase {

    private File inputFile;

    /**
     * Accepts jsonlines file to send for bulk lookup
     * @param file - The file object being passed as a part of the request
     * @throws IOException
     */
    public BulkLookupQuery withInputFile(File file) throws IOException {
        this.inputFile = file;
        return this;
    }

    /**
     * Limits results to products of the geography with this code. Example : 'US', 'GB', etc
     * If the user doesn’t pass a value, the default option returns the US countryCode data
     * @param cc - The country code a String value
     */
    public BulkLookupQuery withCountryCode(String cc) {
        parameters.add(new BasicNameValuePair("countryCode", cc));
        return this;
    }

    /**
     * Gets the file associated with a lookup query
     * @return file set as inputFile in current instance of class
     */
    public File getInputFile() {
        return inputFile;
    }
}
