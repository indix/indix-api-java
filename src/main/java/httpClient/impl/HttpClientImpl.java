package httpClient.impl;

import exception.*;
import httpClient.HttpClient;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
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

/*
 * Wrapper over apache http client
 */
class HttpClientImpl implements HttpClient {
    CloseableHttpClient closeableHttpClient;

    public HttpClientImpl() {
        closeableHttpClient = HttpClients.createDefault();
    }

    public HttpEntity getResponse(HttpRequestBase httpRequest) throws IndixApiException, IOException {

        CloseableHttpResponse response = closeableHttpClient.execute(httpRequest);
        System.out.print(response);
        try {
            String message = response.getStatusLine().getReasonPhrase();
            int status = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != status) {
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

            return response.getEntity();

        } finally {
            response.close();
        }
    }

    public String GET(URI uri) throws IOException, IndixApiException {

        HttpGet httpGet = new HttpGet(uri);
        return EntityUtils.toString(getResponse(httpGet));
    }

    public InputStream GETStream(URI uri) throws IOException, IndixApiException {

        HttpGet httpGet = new HttpGet(uri);
        HttpEntity httpEntity = getResponse(httpGet);
        return httpEntity.getContent();
    }

    public String POST(URI uri, List<NameValuePair> params) throws IndixApiException, IOException {

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
        return EntityUtils.toString(getResponse(httpPost));
    }

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
        return EntityUtils.toString(getResponse(httpPost));
    }

    public void close() throws IOException {
        closeableHttpClient.close();
    }
}


