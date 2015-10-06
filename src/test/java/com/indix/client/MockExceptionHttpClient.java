package com.indix.client;

import com.indix.exception.*;
import com.indix.httpClient.HttpClient;
import org.apache.http.NameValuePair;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class MockExceptionHttpClient {

    enum ExceptionName {
        UNAUTHORIZED, BAD_REQUEST, INDIX_API, INTERNAL_SERVER, PAYMENT_REQUIRED, TOO_MANY_REQUESTS
    }

    Map<ExceptionName, IndixApiException> mapExceptions;

    public MockExceptionHttpClient() {
        mapExceptions = new HashMap<ExceptionName, IndixApiException>() {{
            put(ExceptionName.BAD_REQUEST, new BadRequestException("bad request exception"));
            put(ExceptionName.UNAUTHORIZED, new UnauthorizedException("unauthorized exception"));
            put(ExceptionName.TOO_MANY_REQUESTS, new TooManyRequestsException("too many requests exception"));
            put(ExceptionName.PAYMENT_REQUIRED, new PaymentRequiredException("payment required exception"));
            put(ExceptionName.INDIX_API, new IndixApiException(999, "some unknown error code"));
            put(ExceptionName.INTERNAL_SERVER, new InternalServerException("internal server exception"));
        }};

    }

    public HttpClient getMockClient(ExceptionName exceptionName) throws IOException, IndixApiException {

        final ExceptionName getException = exceptionName;

        return new HttpClient() {

            public String GET(URI uri) throws IOException, IndixApiException {

                //throw corresponding exception as per the situation
                //
                throw mapExceptions.get(getException);
            }

            public InputStream GETStream(URI uri) throws IOException, IndixApiException {
                return null;
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair) throws IOException, IndixApiException {
                return null;
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair, File file) throws IOException, IndixApiException {
                return null;
            }

            public void close() throws IOException {

            }
        };
    }
}
