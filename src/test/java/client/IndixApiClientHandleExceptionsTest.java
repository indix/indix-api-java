package client;

import client.impl.IndixApiClientFactory;
import exception.*;
import httpClient.HttpClient;
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
                throw new UnauthorizedException("unauthorized");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected= TooManyRequestsException.class)
    public void handleTooManyRequestsException() throws IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new TooManyRequestsException("too many requests");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected= PaymentRequiredException.class)
    public void handlePaymentRequiredException() throws IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new PaymentRequiredException("payment required");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery();
            indixApiClient.getProductsSummary(searchQuery);
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
        } finally {
            indixApiClient.close();
        }
    }

}
