package client;

import exception.*;
import httpClient.HttpClient;
import org.apache.http.NameValuePair;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockExceptionHttpClient {

    public enum exception_name{
        UNAUTHORIZED,BAD_REQUEST,INDIX_API,INTERNAL_SERVER,PAYMENT_REQUIRED,TOO_MANY_REQUESTS
    }

    public HttpClient getMockClient(exception_name exceptionName) throws IOException, IndixApiException {

        final exception_name getException=exceptionName;

        final Map<exception_name, IndixApiException> mapExceptions = new HashMap<exception_name, IndixApiException>()
        {{
                put(exception_name.BAD_REQUEST, new BadRequestException("bad request exception"));
                put(exception_name.UNAUTHORIZED, new UnauthorizedException("unauthorized exception"));
                put(exception_name.TOO_MANY_REQUESTS, new TooManyRequestsException("too many requests exception"));
                put(exception_name.PAYMENT_REQUIRED, new PaymentRequiredException("payment required exception"));
                put(exception_name.INDIX_API, new IndixApiException(999, "some unknown error code"));
                put(exception_name.INTERNAL_SERVER, new InternalServerException("internal server exception"));
            }};

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
