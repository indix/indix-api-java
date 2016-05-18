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

        if (5 != args.length) {
            throw new IllegalArgumentException(
                "Usage: java -cp <jar_path> <class_name> <app_id> <app_key> <view_type> <country_code> <lookup_file_path>"
            );
        }

        // Create Api client
        //
        String appId = args[0];
        String appKey = args[1];
        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(appId, appKey);

        // Validate input file's presence
        //
        File file = new File(args[4]);
        if (!file.exists()) throw new IllegalArgumentException("Invalid file: " + args[4]);

        String countryCode = args[3];
        String viewType = args[2];
        ProductsViewType view;
        switch(viewType) {
            case "summary"          : view = ProductsViewType.SUMMARY; break;
            case "offersStandard"   : view = ProductsViewType.OFFERS_STANDARD; break;
            case "offersPremium"    : view = ProductsViewType.OFFERS_PREMIUM; break;
            case "catalogStandard"  : view = ProductsViewType.CATALOG_STANDARD; break;
            case "catalogPremium"   : view = ProductsViewType.CATALOG_PREMIUM; break;
            case "universal"        : view = ProductsViewType.UNIVERSAL; break;
            default                 : throw new IllegalArgumentException("Invalid view_type: " + viewType);
        }

        // Create query
        //
        BulkLookupQuery bulkLookupQuery = QueryFactory.newBulkLookupQuery()
                .withCountryCode(countryCode)
                .withInputFile(file);

        // Submit and print job details
        //
        JobInfo jobInfo = indixApiClient.postBulkJob(view, bulkLookupQuery);
        System.out.println("jobId: " + jobInfo.getId());
        System.out.println("status: " + jobInfo.getStatus());
        System.out.println("Count: " + jobInfo.getCount());
    }
}
