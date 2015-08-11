package client;

import exception.*;
import httpClient.HttpClient;
import org.apache.http.NameValuePair;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

public class MockExceptionHttpClient {

    public enum exception_name{
        UNAUTHORIZED,BAD_REQUEST,INDIX_API,INTERNAL_SERVER,PAYMENT_REQUIRED,TOO_MANY_REQUESTS
    }

    public HttpClient getMockClient(exception_name getException) throws IOException, IndixApiException {

        final exception_name getExc=getException;
        HttpClient mockhttpclient = new HttpClient() {

            public String GET(URI uri) throws IOException, IndixApiException {

                //throw corresponding exception as per the situation
                //
                if(getExc.equals(exception_name.BAD_REQUEST))
                    throw new BadRequestException("bad request exception");
                else if(getExc.equals(exception_name.UNAUTHORIZED))
                    throw new UnauthorizedException("unauthorized exception");
                else if(getExc.equals(exception_name.TOO_MANY_REQUESTS))
                    throw new TooManyRequestsException("too many requests exception");
                else if(getExc.equals(exception_name.PAYMENT_REQUIRED))
                    throw new PaymentRequiredException("payment required exception");
                else if(getExc.equals(exception_name.INDIX_API))
                    throw new IndixApiException(999, "some unknown error code");
                else if(getExc.equals(exception_name.INTERNAL_SERVER))
                    throw new InternalServerException("internal server exception");

                return null;
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
        return mockhttpclient;
    }
}
