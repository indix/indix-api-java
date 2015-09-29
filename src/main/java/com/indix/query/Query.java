package com.indix.query;

import org.apache.http.NameValuePair;
import java.util.List;

/**
 * Base query interface
 */
public interface Query {
    List<NameValuePair> getParameters();
}
