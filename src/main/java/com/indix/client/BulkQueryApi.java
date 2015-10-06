package com.indix.client;

import com.indix.exception.IndixApiException;
import com.indix.models.jobs.JobInfo;
import com.indix.query.BulkLookupQuery;
import com.indix.query.BulkProductsQuery;
import com.indix.query.JobQuery;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;

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
