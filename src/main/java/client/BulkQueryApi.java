package client;

import exception.IndixApiException;
import models.jobs.JobInfo;
import query.BulkLookupQuery;
import query.BulkProductsQuery;
import query.JobStatusQuery;

import java.io.InputStream;

public interface BulkQueryApi {

    /*
     * Retrieve response for a bulk search query
     */
    JobInfo postBulkJob(String resource, BulkProductsQuery query) throws IndixApiException;

    /*
     * Retrieve response for a bulk lookup query
     */
    JobInfo postBulkJob(String resource, BulkLookupQuery query) throws IndixApiException;

    /*
     * Retrieve job status for any bulk query
     */
    JobInfo getBulkJobStatus(JobStatusQuery query) throws IndixApiException;

    /*
     * Retrieve output file for a bulk job
     */
    InputStream getBulkJobOutput(JobStatusQuery query) throws IndixApiException;
}
