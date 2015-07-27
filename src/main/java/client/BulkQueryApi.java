package client;

import exception.IndixApiException;
import models.jobs.JobInfo;
import query.BulkLookupQuery;
import query.BulkProductsQuery;
import query.JobStatusQuery;

import java.io.InputStream;

public interface BulkQueryApi {

    JobInfo postBulkJob(BulkProductsQuery query) throws IndixApiException;

    JobInfo postBulkJob(BulkLookupQuery query) throws IndixApiException;

    JobInfo getBulkJobStatus(JobStatusQuery query) throws IndixApiException;

    InputStream getBulkJobOutput(JobStatusQuery query) throws IndixApiException;
}
