package client;

import client.impl.IndixApiClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.IndixApiException;
import httpClient.HttpClient;
import models.bulkJobResponse.BulkJobOutput;
import models.jobs.JobInfo;
import org.junit.Test;
import query.BulkLookupQuery;
import query.BulkProductsQuery;
import query.JobStatusQuery;
import query.QueryFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static client.impl.IndixApiConstants.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class IndixApiClientBulkQueryTest {

    @Test
    public void getBulkJobId() throws IOException, IndixApiException {

        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("bulkQuery-json-responses0/bulkQueryResponse.json");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        String bulkResources[] = {BULK_CATALOG_PREMIUM_PRODUCTS_RESOURCE,BULK_CATALOG_STANDARD_PRODUCTS_RESOURCE,
                BULK_OFFERS_PREMIUM_PRODUCTS_RESOURCE,BULK_OFFERS_STANDARD_PRODUCTS_RESOURCE,BULK_PRODUCT_RESOURCE,BULK_UNIVERSAL_PRODUCTS_RESOURCE};

        List<Integer> storeIdList = new ArrayList<Integer>();
        storeIdList.add(68);
        for(String resource : bulkResources){
            try {
                BulkProductsQuery bulkQuery = QueryFactory.newBulkQuery()
                        .withAppId("123")
                        .withAppKey("123")
                        .withCountryCode("US")
                        .withStoreId(storeIdList);

                JobInfo jr = indixApiClient.postBulkJob(resource,bulkQuery);
                assertEquals(1941, jr.getId());
                assertEquals("SUBMITTED", jr.getStatus());
            } finally {
                indixApiClient.close();
            }
        }

    }

    @Test
    public void getBulkLookupJobId() throws IOException, IndixApiException {

        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("bulkQuery-json-responses0/bulkQueryResponse.json");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        String bulkResources[] = {BULK_LOOKUP_CATALOG_PREMIUM_RESOURCE,BULK_LOOKUP_CATALOG_STANDARD_RESOURCE,
                BULK_LOOKUP_OFFERS_PREMIUM_RESOURCE,BULK_LOOKUP_OFFERS_STANDARD_RESOURCE,BULK_LOOKUP_RESOURCE,BULK_LOOKUP_UNIVERSAL_RESOURCE};

        for(String resource : bulkResources) {
            try {
                File file = new File("src/test/resources/bulkQuery-json-responses0/bulkLookupInput.jsonl");
                BulkLookupQuery bulkLookupQuery = QueryFactory.newBulkLookupQuery()
                        .withAppId("123")
                        .withAppKey("123")
                        .withCountryCode("US")
                        .withInputFile(file);
                JobInfo job1 = indixApiClient.postBulkJob(resource, bulkLookupQuery);

                assertEquals(true, bulkLookupQuery.getInputFile().exists());
                assertEquals("SUBMITTED", job1.getStatus());
                assertEquals(1941, job1.getId());


            } finally {
                indixApiClient.close();
            }
        }
    }

    @Test
    public void getBulkJobStatus() throws IOException, IndixApiException {

        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("bulkQuery-json-responses0/bulkQueryJobStatus.json");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            JobStatusQuery jobStatusQuery = QueryFactory.newJobStatusQuery()
                    .withJobId(1941);
            JobInfo job = indixApiClient.getBulkJobStatus(jobStatusQuery);

            assertEquals(jobStatusQuery.getJobId(), job.getId());
            assertEquals("SUBMITTED", job.getStatus());
            assertEquals(11, job.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getBulkJobFile() throws IOException, IndixApiException {

        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("bulkQuery-json-responses0/bulkQueryJobOutput.jsonl");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            JobStatusQuery jobStatusQuery = QueryFactory.newJobStatusQuery()
                    .withJobId(123);
            InputStream stream = indixApiClient.getBulkJobOutput(jobStatusQuery);

            //serialise the stream of data being obtained as bulk job output
            //
            ObjectMapper jsonMapper = new ObjectMapper();
            BulkJobOutput bulkJobResponse = jsonMapper.readValue(stream, BulkJobOutput.class);

            //validate if stream is available
            //
            assertNotNull(stream.available());

            //validate the data obtained for each given json input
            //
            assertThat(bulkJobResponse.getInput(), is("{\"upc\":\"00049022632813\"}"));
            assertThat(bulkJobResponse.getStatus(), is(200));
            assertThat(bulkJobResponse.getMessage(), is("ok"));

            //validate the products obtained as a result for each given input
            //
            assertThat(bulkJobResponse.getResult().getCount(), is(1));
            assertThat(bulkJobResponse.getResult().getProducts().get(0).getMpid(), is("f66eed1cbb073c8f635c42452b8d31e8"));


        } finally {
            indixApiClient.close();
        }
    }
}


