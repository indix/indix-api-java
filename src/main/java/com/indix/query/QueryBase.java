package com.indix.query;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

class QueryBase implements Query {
    List<NameValuePair> parameters;

    public QueryBase() {
        parameters = new ArrayList<NameValuePair>();
    }

    public List<NameValuePair> getParameters() {
        return parameters;
    }
}
