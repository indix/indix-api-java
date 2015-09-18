package client;

import exception.IndixApiException;
import models.jobs.JobInfo;
import query.BulkLookupQuery;
import query.BulkProductsQuery;
import query.JobQuery;

import java.io.InputStream;

//params
public interface BulkQueryApi {

    /**
     * Posts a bulk job for the appropriate resource type for search cases
     * @param productsViewType {@link ProductsViewType}
     * @param query Instance of {@link BulkProductsQuery} with appropriate parameters
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    JobInfo postBulkJob(ProductsViewType productsViewType, BulkProductsQuery query) throws IndixApiException;

    /**
     * Posts a bulk job for the appropriate resource type for lookup cases
     * @param productsViewType {@link ProductsViewType}
     * @param query Instance of {@link BulkLookupQuery} with appropriate parameters
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    JobInfo postBulkJob(ProductsViewType productsViewType, BulkLookupQuery query) throws IndixApiException;

    /**
     * get status of job returned against bulk query
     * @param query Instance of {@link JobQuery} with appropriate jobId
     * @return {@link JobInfo}
     * @throws {@link IndixApiException}
     */
    JobInfo getBulkJobStatus(JobQuery query) throws IndixApiException;

    /**
     * get output of job returned against bulk query
     * @param query Instance of {@link JobQuery} with appropriate jobId
     * @return stream of data obtained as response from the bulk job
     * @throws {@link IndixApiException}
     */
    InputStream getBulkJobOutput(JobQuery query) throws IndixApiException;
}
