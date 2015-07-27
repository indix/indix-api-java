package client;

import client.impl.IndixApiClientFactory;
import exception.IndixApiException;
import httpClient.HttpClient;
import models.jobs.JobInfo;
import org.junit.Test;
import query.BulkLookupQuery;
import query.BulkProductsQuery;
import query.JobStatusQuery;
import query.QueryFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IndixApiClientBulkQueryTest {

    @Test
    public void getBulkJobId() throws IOException, IndixApiException{

        MockHttpCLient mockHttpClientInstance = new MockHttpCLient();
        HttpClient mockHttpClient = mockHttpClientInstance.mockPostFormEncodedClient("bulkQuery-json-responses0/bulkQueryResponse.json");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(68);
        try{
            BulkProductsQuery bulkQuery = QueryFactory.newBulkQuery()
                    .withAppId("cb7e91b3")
                    .withAppKey("7367fd8ed2856c6d44c4f30303963b9c")
                    .withCountryCode("US")
                    .withStoreId(intList);

            JobInfo jr = indixApiClient.postBulkJob(bulkQuery);
            assertEquals(1941, jr.getId());
            assertEquals("SUBMITTED", jr.getStatus());
        }
        finally{
            indixApiClient.close();
        }
    }

    @Test
    public void getBulkLookupJobId() throws IOException, IndixApiException{

        MockHttpCLient mockHttpClientInstance = new MockHttpCLient();
        HttpClient mockHttpClient = mockHttpClientInstance.mockPostMultipartClient("bulkQuery-json-responses0/bulkQueryResponse.json");

        IndixApiClient indixApiClient1 = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try{
            File file =new File("/home/indix/Desktop/repos/scarlet-client/src/test/resources/bulkQuery-json-responses0/bulkLookupInput.jsonl");
            BulkLookupQuery bulkLookupQuery = QueryFactory.newBulkLookupQuery()
                    .withAppId("cb7e91b3")
                    .withAppKey("7367fd8ed2856c6d44c4f30303963b9c")
                    .withCountryCode("US")
                    .withInputFile(file);
            JobInfo job1 = indixApiClient1.postBulkJob(bulkLookupQuery);

            assertEquals(true,bulkLookupQuery.getInputFile().exists());
            assertEquals("SUBMITTED", job1.getStatus());
            assertEquals(1941, job1.getId());


        }
        finally{
            indixApiClient1.close();
        }

    }

    @Test
    public void getBulkJobStatus() throws IOException, IndixApiException{

        MockHttpCLient mockHttpClientInstance = new MockHttpCLient();
        HttpClient mockHttpClient = mockHttpClientInstance.mockGetClient("bulkQuery-json-responses0/bulkQueryJobStatus.json");
        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);
        try{
            JobStatusQuery jobStatusQuery = QueryFactory.newJobStatusQuery()
                    .withJobId(1941);
            JobInfo job = indixApiClient.getBulkJobStatus(jobStatusQuery);

            assertEquals(jobStatusQuery.getJobId(), job.getId());
            assertEquals("SUBMITTED", job.getStatus());
            assertEquals(11, job.getCount());
        }
        finally{
            indixApiClient.close();
        }

    }

    @Test
    public void getBulkJobFile() throws IOException, IndixApiException{

        MockHttpCLient mockHttpClientInstance = new MockHttpCLient();
        HttpClient mockHttpClient = mockHttpClientInstance.mockGetStreamClient("bulkQuery-json-responses0/bulkQueryJobOutput.json");
        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);
        try{
            JobStatusQuery jobStatusQuery = QueryFactory.newJobStatusQuery()
                    .withJobId(123);
            InputStream stream = indixApiClient.getBulkJobOutput(jobStatusQuery);
            assertNotNull(stream.available());
        }
        finally{
            indixApiClient.close();
        }

    }

}


