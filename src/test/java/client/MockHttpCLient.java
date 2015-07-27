package client;

import common.ResourceUtils;
import exception.BadRequestException;
import exception.IndixApiException;
import exception.UnauthorizedException;
import httpClient.HttpClient;
import org.apache.http.NameValuePair;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MockHttpCLient {

    public HttpClient mockGetClient(String resource) throws IOException, IndixApiException {
        final String name = resource;
        HttpClient mockhttpclient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), name);
            }

            public InputStream GETStream(URI uri) throws IOException, IndixApiException {
                throw new UnauthorizedException("bad request exception");
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair, File file) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }

            public void close() throws IOException {

            }
        };
        return mockhttpclient;
    }

    public HttpClient mockGetStreamClient(String resource) throws IOException, IndixApiException {
        final String name = resource;
        HttpClient mockhttpclient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new UnauthorizedException("bad request exception");
            }

            public InputStream GETStream(URI uri) throws IOException, IndixApiException {
                String content = ResourceUtils.getTestResource(getClass().getClassLoader(), name);
                InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
                return stream;
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair, File file) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }

            public void close() throws IOException {

            }
        };
        return mockhttpclient;
    }

    public HttpClient mockPostFormEncodedClient(String resource) throws IOException, IndixApiException {
        final String name = resource;
        HttpClient mockhttpclient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }

            public InputStream GETStream(URI uri) throws IOException, IndixApiException {
                throw new UnauthorizedException("bad request exception");
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), name);
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair, File file) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }

            public void close() throws IOException {

            }
        };
        return mockhttpclient;
    }

    public HttpClient mockPostMultipartClient(String resource) throws IOException, IndixApiException {
        final String name = resource;
        HttpClient mockhttpclient = new HttpClient() {
            public String GET(URI uri) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }

            public InputStream GETStream(URI uri) throws IOException, IndixApiException {
                throw new UnauthorizedException("bad request exception");
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair) throws IOException, IndixApiException {
                throw new BadRequestException("bad request exception");
            }

            public String POST(URI uri, List<NameValuePair> nameValuePair, File file) throws IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), name);
            }

            public void close() throws IOException {

            }
        };
        return mockhttpclient;
    }

}
