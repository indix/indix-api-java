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

        if (3 != args.length) {
            throw new IllegalArgumentException("Usage:java -cp <client_jar_path> com.indix.tools.SubmitBulkLookup " +
                    "<endpoint> <country_code> <app_id> <app_key> <lookup_file>");
        }

        String appId = args[2];
        String appKey = args[3];
        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(appId, appKey);

        File file = new File(args[4]);
        if (!file.exists())
            throw new IllegalArgumentException("No file found at specified path");

        String countryCode = args[1];

        String endpoint = args[0];
        ProductsViewType view;
        switch(endpoint) {
            case "summary"             : view = ProductsViewType.SUMMARY;break;
            case "catalog_premium"     : view = ProductsViewType.CATALOG_PREMIUM; break;
            case "catalog_standard"    : view = ProductsViewType.CATALOG_STANDARD; break;
            case "offers_premium"      : view = ProductsViewType.OFFERS_PREMIUM; break;
            case "offers_standard"     : view = ProductsViewType.OFFERS_STANDARD; break;
            default                    : throw new IllegalArgumentException("Invalid endpoint specified! " +
                    " Allowed endpoints are :" + ProductsViewType.values());
        }

        BulkLookupQuery bulkLookupQuery = QueryFactory.newBulkLookupQuery()
                .withCountryCode(countryCode)
                .withInputFile(file);

        JobInfo jobInfo = indixApiClient.postBulkJob(view, bulkLookupQuery);
        System.out.println(jobInfo.getCount());
        System.out.println(jobInfo.getId());
        System.out.println(jobInfo.getStatus());
    }
}
