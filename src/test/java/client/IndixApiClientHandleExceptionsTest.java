package client;

import client.impl.IndixApiClientFactory;
import exception.*;
import httpClient.HttpClient;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import query.QueryFactory;
import query.SearchQuery;

import java.io.IOException;
import java.net.URI;

public class IndixApiClientHandleExceptionsTest {

    @Test(expected= UnauthorizedException.class)
    public void handleUnauthorizedException() throws IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new UnauthorizedException("unauthorized exception");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (UnauthorizedException ue) {
            Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, ue.getErrorCode());
            Assert.assertEquals("unauthorized exception", ue.getMessage());
            throw ue;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected= TooManyRequestsException.class)
    public void handleTooManyRequestsException() throws IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new TooManyRequestsException("too many requests exception");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (TooManyRequestsException tmr) {
            Assert.assertEquals(429, tmr.getErrorCode());
            Assert.assertEquals("too many requests exception", tmr.getMessage());
            throw tmr;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected= PaymentRequiredException.class)
    public void handlePaymentRequiredException() throws IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new PaymentRequiredException("payment required exception");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (PaymentRequiredException pre) {
            Assert.assertEquals(HttpStatus.SC_PAYMENT_REQUIRED, pre.getErrorCode());
            Assert.assertEquals("payment required exception", pre.getMessage());
            throw pre;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected= IndixApiException.class)
    public void handleIndixApiException() throws IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new IndixApiException(999, "some unknown error code");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (IndixApiException iae) {
            Assert.assertEquals(999, iae.getErrorCode());
            Assert.assertEquals("some unknown error code", iae.getMessage());
            throw iae;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected= InternalServerException.class)
    public void handleInternalServerException() throws IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new InternalServerException("internal server exception");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (InternalServerException ise) {
            Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, ise.getErrorCode());
            Assert.assertEquals("internal server exception", ise.getMessage());
            throw ise;
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected= BadRequestException.class)
    public void handleBadRequestException() throws IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } catch (BadRequestException bre) {
            Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, bre.getErrorCode());
            Assert.assertEquals("bad request exception", bre.getMessage());
            throw bre;
        } finally {
            indixApiClient.close();
        }
    }

}
