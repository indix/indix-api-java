package com.indix.tools;

import com.indix.client.IndixApiClient;
import com.indix.client.ProductsViewType;
import com.indix.client.impl.IndixApiClientFactory;
import com.indix.exception.IndixApiException;
import com.indix.models.jobs.JobInfo;
import com.indix.query.BulkLookupQuery;
import com.indix.query.QueryFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class SubmitBulkLookup {

    public static void main(String[] args) throws IndixApiException, URISyntaxException, IOException {

        // TODO: Validate args
        //
        if (3 != args.length) {
            throw new IllegalArgumentException("<fill it with proper usage>");
        }

        String appId = args[0];
        String appKey = args[1];
        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(appId, appKey);

        File file = new File(args[2]);
        BulkLookupQuery bulkLookupQuery = QueryFactory.newBulkLookupQuery()
                .withCountryCode("US")
                .withInputFile(file);

        JobInfo jobInfo = indixApiClient.postBulkJob(ProductsViewType.SUMMARY, bulkLookupQuery);
        System.out.println(jobInfo.getCount());
        System.out.println(jobInfo.getId());
        System.out.println(jobInfo.getStatus());
    }
}