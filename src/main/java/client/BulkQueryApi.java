package client;

import exception.IndixApiException;
import models.jobs.jobsResult.JobsResult;
import models.jobs.jobsResult.JobsStatusResult;
import query.BulkLookupQuery;
import query.BulkSearchQuery;
import query.JobStatusQuery;

import java.io.File;

public interface BulkQueryApi {

    JobsResult getBulkJob(BulkSearchQuery query) throws IndixApiException;

    JobsResult getBulkJob(BulkLookupQuery query) throws IndixApiException;

    JobsStatusResult getBulkJobStatus(JobStatusQuery query) throws IndixApiException;

    File getBulkJobOutput(JobStatusQuery query) throws IndixApiException;
}
