package client;

import client.impl.IndixApiClientFactory;
import common.ResourceUtils;
import exception.BadRequestException;
import exception.IndixApiException;
import httpClient.HttpClient;
import models.jobs.jobs.Job;
import models.jobs.jobs.JobStatus;
import org.junit.Assert;
import org.junit.Test;
import query.BulkLookupQuery;
import query.BulkSearchQuery;
import query.JobStatusQuery;
import query.QueryFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertEquals;

public class IndixApiClientBulkQueryTest {

    @Test
    public void getBulkJobId() throws IOException, IndixApiException{
        HttpClient mockhttpclient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }
            public String POST(URI uri) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(),"bulkQuery-json-responses0/bulkQueryResponse.json");
            }
            public String POST(URI uri, File file) throws IOException, IndixApiException {
                return null;
            }
            @Override
            public void close() throws IOException {

            }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockhttpclient);

        try{
            BulkSearchQuery bulkQuery = QueryFactory.newBulkQuery()
                    .withAppId("123")
                    .withAppKey("123")
                    .withCountryCode("US");
            Job sr = indixApiClient.getBulkJob(bulkQuery).getJob();

            assertEquals(112, sr.getId());
            assertEquals("SUBMITTED", sr.getStatus());
        }
        finally{
            indixApiClient.close();
        }
    }

    @Test
    public void getBulkLookupJobId() throws IOException, IndixApiException{
        HttpClient mockhttpclient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                return null;
            }
            public String POST(URI uri) throws IOException, IndixApiException {
                return null;
            }
            public String POST(URI uri, File file) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "bulkQuery-json-responses0/bulkQueryResponse.json");
            }
            public void close() throws IOException {

            }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockhttpclient);

        try{
            File file =new File("/home/indix/Desktop/repos/scarlet-client/src/test/resources/bulkQuery-json-responses0/bulkLookupInput.jsonl");
            BulkLookupQuery bulkLookupQuery = QueryFactory.newBulkLookupQuery()
                    .withAppId("123")
                    .withAppKey("123")
                    .withCountryCode("US")
                    .withJsonFile(file);
            Job sr = indixApiClient.getBulkJob(bulkLookupQuery).getJob();

            assertEquals(true,bulkLookupQuery.getJsonfile().exists());
            assertEquals("SUBMITTED", sr.getStatus());
            assertEquals(112, sr.getId());
        }
        finally{
            indixApiClient.close();
        }

    }

    @Test
    public void getBulkJobStatus() throws IOException, IndixApiException{
        HttpClient mockhttpclient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }
            @Override
            public String POST(URI uri) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(),"bulkQuery-json-responses0/bulkQueryJobStatus.json");
            }
            @Override
            public String POST(URI uri, File file) throws IOException, IndixApiException {
                return null;
            }
            public void close() throws IOException {

            }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockhttpclient);

        try{
            JobStatusQuery jobStatusQuery = QueryFactory.newJobStatusQuery()
                    .withJobId(1234);
            JobStatus sr = indixApiClient.getBulkJobStatus(jobStatusQuery).getJob();

            assertEquals(jobStatusQuery.getJobId(), sr.getId());
            assertEquals("SUBMITTED", sr.getStatus());
            assertEquals(11,sr.getCount());
        }
        finally{
            indixApiClient.close();
        }
    }

    @Test
    public void getBulkJobFile() throws IOException, IndixApiException{
        HttpClient mockhttpclient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "bulkQuery-json-responses0/bulkQueryJobOutput.json");
            }
            public String POST(URI uri) throws IOException, IndixApiException {
                return null;
            }
            public String POST(URI uri, File file) throws IOException, IndixApiException {
                return null;
            }
            public void close() throws IOException {

            }
        };
        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockhttpclient);
        try{
            JobStatusQuery jobStatusQuery = QueryFactory.newJobStatusQuery()
                    .withJobId(123);
            File file = indixApiClient.getBulkJobOutput(jobStatusQuery);
            Assert.assertTrue(file.exists());
            assertEquals(file.getName(),"output123.jsonl");
        }
        finally{
            indixApiClient.close();
        }

    }




}
