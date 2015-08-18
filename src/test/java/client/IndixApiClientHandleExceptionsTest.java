package client;

import client.impl.IndixApiClientFactory;
import exception.*;
import httpClient.HttpClient;
import org.apache.http.HttpStatus;
import org.junit.Test;
import query.QueryFactory;
import query.SearchQuery;

import java.io.IOException;


import static org.junit.Assert.assertEquals;

public class IndixApiClientHandleExceptionsTest {

    @Test(expected = UnauthorizedException.class)
    public void handleUnauthorizedException() throws IOException, IndixApiException {
        MockExceptionHttpClient mockClientInstance = new MockExceptionHttpClient();
        HttpClient mockHttpClient = mockClientInstance.getMockClient(MockExceptionHttpClient.ExceptionName.UNAUTHORIZED);

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (UnauthorizedException ue) {
            assertEquals(HttpStatus.SC_UNAUTHORIZED, ue.getErrorCode());
            assertEquals("unauthorized exception", ue.getMessage());
            throw ue;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = TooManyRequestsException.class)
    public void handleTooManyRequestsException() throws IOException, IndixApiException {
        MockExceptionHttpClient mockClientInstance = new MockExceptionHttpClient();
        HttpClient mockHttpClient = mockClientInstance.getMockClient(MockExceptionHttpClient.ExceptionName.TOO_MANY_REQUESTS);

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (TooManyRequestsException tmr) {
            assertEquals(429, tmr.getErrorCode());
            assertEquals("too many requests exception", tmr.getMessage());
            throw tmr;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = PaymentRequiredException.class)
    public void handlePaymentRequiredException() throws IOException, IndixApiException {
        MockExceptionHttpClient mockClientInstance = new MockExceptionHttpClient();
        HttpClient mockHttpClient = mockClientInstance.getMockClient(MockExceptionHttpClient.ExceptionName.PAYMENT_REQUIRED);

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (PaymentRequiredException pre) {
            assertEquals(HttpStatus.SC_PAYMENT_REQUIRED, pre.getErrorCode());
            assertEquals("payment required exception", pre.getMessage());
            throw pre;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = IndixApiException.class)
    public void handleIndixApiException() throws IOException, IndixApiException {
        MockExceptionHttpClient mockClientInstance = new MockExceptionHttpClient();
        HttpClient mockHttpClient = mockClientInstance.getMockClient(MockExceptionHttpClient.ExceptionName.INDIX_API);

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (IndixApiException iae) {
            assertEquals(999, iae.getErrorCode());
            assertEquals("some unknown error code", iae.getMessage());
            throw iae;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = InternalServerException.class)
    public void handleInternalServerException() throws IOException, IndixApiException {
        MockExceptionHttpClient mockClientInstance = new MockExceptionHttpClient();
        HttpClient mockHttpClient = mockClientInstance.getMockClient(MockExceptionHttpClient.ExceptionName.INTERNAL_SERVER);

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (InternalServerException ise) {
            assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, ise.getErrorCode());
            assertEquals("internal server exception", ise.getMessage());
            throw ise;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected = BadRequestException.class)
    public void handleBadRequestException() throws IOException, IndixApiException {
        MockExceptionHttpClient mockClientInstance = new MockExceptionHttpClient();
        HttpClient mockHttpClient = mockClientInstance.getMockClient(MockExceptionHttpClient.ExceptionName.BAD_REQUEST);

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (BadRequestException bre) {
            assertEquals(HttpStatus.SC_BAD_REQUEST, bre.getErrorCode());
            assertEquals("bad request exception", bre.getMessage());
            throw bre;
        } finally {
            indixApiClient.close();
        }
    }

}
