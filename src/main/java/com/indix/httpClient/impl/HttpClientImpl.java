package com.indix.httpClient.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indix.exception.*;
import com.indix.httpClient.HttpClient;
import com.indix.tools.SSLTrustCA;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

/**
 * Wrapper over apache http client
 */

class HttpClientImpl implements HttpClient {
    CloseableHttpClient closeableHttpClient;
    ObjectMapper objectMapper;

    /**
     * Creates a {@link CloseableHttpClient} instance with default
     * configuration.
     */
    public HttpClientImpl() {
        this(HttpClients.custom().setSSLContext(SSLTrustCA.trustLetsEncryptRootCA()).build());
    }

    /**
     * Creates with a custom {@link CloseableHttpClient} instance.
     *
     * @param closableHttpClient
     */
    public HttpClientImpl(CloseableHttpClient closableHttpClient) {
        closeableHttpClient = closableHttpClient;
        objectMapper = new ObjectMapper();
    }

    /**
     * Executes a HTTP request and checks for any invalid responses that might have been returned
     * @param httpRequest - The http request object containing all the request details/entities
     * @return the response, if obtained from the request
     * @throws {@link IndixApiException}
     * @throws IOException
     */
    private CloseableHttpResponse getResponse(HttpRequestBase httpRequest) throws IndixApiException, IOException{

        CloseableHttpResponse response = closeableHttpClient.execute(httpRequest);
        int status = response.getStatusLine().getStatusCode();

        if (HttpStatus.SC_OK != status) {

            String content = EntityUtils.toString(response.getEntity());

            //deserialize exception if raised from query errors
            //
            ErrorResponse error = objectMapper.readValue(content, ErrorResponse.class);
            String message = error.getMessage();

            // we need to close the resources before we throw an exception
            //
            response.close();

            switch (status) {
                case HttpStatus.SC_BAD_REQUEST:
                    throw new BadRequestException(message);
                case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                    throw new InternalServerException(message);
                case HttpStatus.SC_UNAUTHORIZED:
                    throw new UnauthorizedException(message);
                case HttpStatus.SC_PAYMENT_REQUIRED:
                    throw new PaymentRequiredException(message);
                case 429: // too many requests - rate limited error
                    throw new TooManyRequestsException(message);
                default:
                    throw new IndixApiException(status, message);
            }
        }

        return response;
    }

    /**
     * Executes HTTP GET request for which a String value is returned as response.
     * @param uri - The URI against which the request is to be sent
     * @return the response to the request
     * @throws IOException
     * @throws {@link IndixApiException}
     */
    public String GET(URI uri) throws IOException, IndixApiException {

        HttpGet httpGet = new HttpGet(uri);

        try(CloseableHttpResponse response = getResponse(httpGet)) {
            return EntityUtils.toString(response.getEntity());
        }
    }

    /**
     * Executes HTTP GET request for which a large stream is received as response
     * @param uri - The URI against which the request is to be sent
     * @return the response to the request
     * @throws IOException
     * @throws {@link IndixApiException}
     */
    public InputStream GETStream(URI uri) throws IOException, IndixApiException {

        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = getResponse(httpGet);
        return response.getEntity().getContent();
        }

    /**
     * Executes HTTP POST request, accepting parameters in the form-urlencoded format
     * @param uri - The URI against which the request is to be sent
     * @param params - The list of parameters to be sent as a part of the request
     * @return the response to the request
     * @throws {@link IndixApiException}
     * @throws IOException
     */
    public String POST(URI uri, List<NameValuePair> params) throws IndixApiException, IOException {

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
        try(CloseableHttpResponse response = getResponse(httpPost)) {
            return EntityUtils.toString(response.getEntity());
        }
    }

    /**
     * Executes HTTP POST request, accepting file as multi part entity
     *
     * @param uri - The URI against which the request is to be sent
     * @param params - The list of parameters to be sent as a part of the request
     * @param file - The file to be sent as the HTTP multipart entity
     * @return the response to the request
     * @throws IOException
     * @throws {@link IndixApiException}
     */
    public String POST(URI uri, List<NameValuePair> params, File file) throws IOException, IndixApiException {

        // build multi-part entity
        //
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        FileBody fileBody = new FileBody(file);
        builder.addPart("file", fileBody);
        for (NameValuePair param : params) {
            builder.addTextBody(param.getName(), param.getValue());
        }

        // create http post request, set above multi-part entity
        //
        HttpEntity multiPartEntiity = builder.build();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(multiPartEntiity);

        // process request
        //
        try(CloseableHttpResponse response = getResponse(httpPost)) {
            return EntityUtils.toString(response.getEntity());
        }
    }

    /**
     * Close the particular http client instance
     */
    public void close() throws IOException {
        closeableHttpClient.close();
    }
}


