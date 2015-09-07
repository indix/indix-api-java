package client;

import exception.IndixApiException;
import models.jobs.JobInfo;
import query.BulkLookupQuery;
import query.BulkProductsQuery;
import query.JobStatusQuery;

import java.io.InputStream;

//params
public interface BulkQueryApi {

    /**
     * Posts a bulk job for the appropriate resource type for search cases
     * @param resourceType {@link ResourceType}
     * @param query Instance of {@link BulkProductsQuery} with appropriate parameters
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    JobInfo postBulkJob(ResourceType resourceType, BulkProductsQuery query) throws IndixApiException;

    /**
     * Posts a bulk job for the appropriate resource type for lookup cases
     * @param resourceType {@link ResourceType}
     * @param query Instance of {@link BulkLookupQuery} with appropriate parameters
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    JobInfo postBulkJob(ResourceType resourceType, BulkLookupQuery query) throws IndixApiException;

    /**
     * get status of job returned against bulk query
     * @param query Instance of {@link JobStatusQuery} with appropriate jobId
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    JobInfo getBulkJobStatus(JobStatusQuery query) throws IndixApiException;

    /**
     * get output of job returned against bulk query
     * @param query Instance of {@link JobStatusQuery} with appropriate jobId
     * @return stream of data obtained as response from the bulk job
     * @throws {@link IndixApiException}
     */
    InputStream getBulkJobOutput(JobStatusQuery query) throws IndixApiException;
}
