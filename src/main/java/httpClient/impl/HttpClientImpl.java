package httpClient.impl;

import exception.*;
import httpClient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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

    public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {

        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);

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

    public void close() throws IOException {
        closeableHttpClient.close();
    }

}
