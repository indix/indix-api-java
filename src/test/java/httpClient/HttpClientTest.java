package httpClient;

import exception.IndixApiException;
import httpClient.impl.HttpClientFactory;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClientTest {

    final static Logger logger = LoggerFactory.getLogger(HttpClientTest.class);

    @Test
    public void httpClientGetTest() throws URISyntaxException, IOException, IndixApiException {

        HttpClient httpClient = HttpClientFactory.newHttpClient();
        URI uri = new URIBuilder("http://www.google.com").build();

        String content = httpClient.GET(uri);
        logger.info("Content length from google.com = " + content.length());
    }
}
