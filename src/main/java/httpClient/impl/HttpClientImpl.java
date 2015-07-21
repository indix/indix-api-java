package httpClient.impl;

import exception.*;
import httpClient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
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
import java.net.URI;

/*
 * Wrapper over apache http client
 */
class HttpClientImpl implements HttpClient {
    CloseableHttpClient closeableHttpClient;

    public HttpClientImpl() {
        closeableHttpClient = HttpClients.createDefault();
    }

    public String getResponse(HttpRequestBase httpRequest) throws IndixApiException,IOException {
        CloseableHttpResponse response = closeableHttpClient.execute(httpRequest);

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

            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity);
        } finally {
            response.close();
        }
    }

    public String GET(URI uri) throws IOException, IndixApiException {

        HttpGet httpGet = new HttpGet(uri);
        return getResponse(httpGet);
    }

    public String POST(URI uri) throws IndixApiException, IOException{

        HttpPost httpPost = new HttpPost(uri);
        return getResponse(httpPost);
    }

    public String POST(URI uri, File file) throws IOException, IndixApiException{

        HttpPost httpPost = new HttpPost(uri);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        FileBody fileBody = new FileBody(file);
        builder.addPart("file", fileBody);
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        return getResponse(httpPost);
    }

    public void close() throws IOException {
        closeableHttpClient.close();
    }

}
