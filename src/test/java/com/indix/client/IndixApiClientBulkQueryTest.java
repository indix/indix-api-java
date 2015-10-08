
package com.indix.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.indix.client.impl.IndixApiClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indix.exception.IndixApiException;
import com.indix.httpClient.HttpClient;
import com.indix.models.bulkJobResponse.BulkJobOutput;
import com.indix.models.jobs.JobInfo;
import com.indix.models.product.UniversalProduct;
import org.junit.Test;
import com.indix.query.BulkLookupQuery;
import com.indix.query.BulkProductsQuery;
import com.indix.query.JobQuery;
import com.indix.query.QueryFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class IndixApiClientBulkQueryTest {

    @Test
    public void getBulkJobId()
            throws IndixApiException, IOException, URISyntaxException {

        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("bulkQuery-json-responses0/bulkQueryResponse.json");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123", mockHttpClient);

        List<Integer> storeIdList = new ArrayList<Integer>();
        storeIdList.add(68);

        try {
            for (ProductsViewType resource : ProductsViewType.values()) {

                BulkProductsQuery bulkQuery = QueryFactory.newBulkQuery()
                        .withCountryCode("US")
                        .withStoreId(storeIdList);

                JobInfo jr = indixApiClient.postBulkJob(resource, bulkQuery);
                assertEquals(1941, jr.getId());
                assertEquals("SUBMITTED", jr.getStatus());
            }
        } finally {
            indixApiClient.close();
        }

    }

    @Test
    public void getBulkLookupJobId()
            throws IndixApiException, IOException, URISyntaxException {

        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("bulkQuery-json-responses0/bulkQueryResponse.json");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123", mockHttpClient);

        try {
            for (ProductsViewType resource : ProductsViewType.values()) {
                File file = new File("src/test/resources/bulkQuery-json-responses0/bulkLookupInput.jsonl");
                BulkLookupQuery bulkLookupQuery = QueryFactory.newBulkLookupQuery()
                        .withCountryCode("US")
                        .withInputFile(file);
                JobInfo job1 = indixApiClient.postBulkJob(resource, bulkLookupQuery);

                assertEquals(true, bulkLookupQuery.getInputFile().exists());
                assertEquals("SUBMITTED", job1.getStatus());
                assertEquals(1941, job1.getId());


            }
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getBulkJobStatus()
            throws IndixApiException, IOException, URISyntaxException {

        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("bulkQuery-json-responses0/bulkQueryJobStatus.json");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123", mockHttpClient);

        try {
            JobQuery jobQuery = QueryFactory.newJobQuery()
                    .withJobId(1941);
            JobInfo job = indixApiClient.getBulkJobStatus(jobQuery);

            assertEquals(jobQuery.getJobId(), job.getId());
            assertEquals("SUBMITTED", job.getStatus());
            assertEquals(11, job.getCount());
        } finally {
            indixApiClient.close();
        }
    }



    @Test
    public void getBulkJobFile()
            throws IndixApiException, IOException, URISyntaxException {

        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient("bulkQuery-json-responses0/bulkQueryJobOutput.jsonl.tar.gz");

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123", mockHttpClient);

        try {
            JobQuery jobQuery = QueryFactory.newJobQuery()
                    .withJobId(123);
            InputStream stream = indixApiClient.getBulkJobOutput(jobQuery);

            //validate if stream is available
            //
            assertNotNull(stream.available());

            InputStreamReader reader = new InputStreamReader(new GZIPInputStream(stream));
            BufferedReader bufferedReader = new BufferedReader(reader);

            //read the first record of the bulk job output
            String recordOutput1 = bufferedReader.readLine();

            //serialise the stream of data being obtained as bulk job output
            //
            ObjectMapper jsonMapper = new ObjectMapper();
            BulkJobOutput<UniversalProduct> bulkJobResponse1 = jsonMapper.readValue(
                    "{\"input\""+recordOutput1.split("\"input\"")[1],
                    new TypeReference<BulkJobOutput<UniversalProduct>>() {
                    });

            //validate the data obtained for the first given json input
            //
            assertThat(bulkJobResponse1.getInput(), is("{\"upc\":\"00049022632813\"}"));
            assertThat(bulkJobResponse1.getStatus(), is(200));
            assertThat(bulkJobResponse1.getMessage(), is("ok"));

            //validate the products obtained as a result for each given input
            //
            assertThat(bulkJobResponse1.getResult().getCount(), is(1));
            assertThat(
                    bulkJobResponse1.getResult().getProducts().get(0).getStores().get("68").getOffers().get(0).getPid(),
                    is("f66eed1cbb073c8f635c42452b8d31e8"));

            //read the second record of the bulk job output
            String recordOutput2 = bufferedReader.readLine();

            //serialise the stream of data being obtained as bulk job output
            //
            BulkJobOutput<UniversalProduct> bulkJobResponse2 = jsonMapper.readValue(
                    recordOutput2,
                    new TypeReference<BulkJobOutput<UniversalProduct>>() {
                    });

            //validate the data obtained for the second given json input
            //
            assertThat(bulkJobResponse2.getInput(), is("{\"sku\":\"OMB0001-CHIBAR-S12PAC\",\"storeId\":323}"));
            assertThat(bulkJobResponse2.getStatus(), is(200));
            assertThat(bulkJobResponse2.getMessage(), is("ok"));

            //validate the products obtained as a result for each given input
            //
            assertThat(bulkJobResponse2.getResult().getCount(), is(1));
            assertThat(
                    bulkJobResponse2.getResult().getProducts().get(0).getStores().get("323").getOffers().get(0).getPid(),
                    is("acc5aa04a3aac5f30153371e833ab9ce"));

            //read the third record of the bulk job output
            String recordOutput3 = bufferedReader.readLine();

            //serialise the stream of data being obtained as bulk job output
            //
            BulkJobOutput<UniversalProduct> bulkJobResponse3 = jsonMapper.readValue(
                    recordOutput3,
                    new TypeReference<BulkJobOutput<UniversalProduct>>() {
                    });

            //validate the data obtained for the third given json input
            //
            assertThat(bulkJobResponse3.getInput(), is("{\"mpn\":\"1C60NV\",\"brandId\":37600}"));
            assertThat(bulkJobResponse3.getStatus(), is(200));
            assertThat(bulkJobResponse3.getMessage(), is("ok"));

            //validate the products obtained as a result for each given input
            //
            assertThat(
                    bulkJobResponse3.getResult().getCount(),
                    is(0));

        } finally {
            indixApiClient.close();
        }
    }
}

