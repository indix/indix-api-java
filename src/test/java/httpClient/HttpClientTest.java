package httpClient;

import exception.IndixApiException;
import httpClient.impl.HttpClientFactory;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClientTest {

    @Test
    public void httpClientGetTest() throws URISyntaxException, IOException, IndixApiException {
        HttpClient httpClient = HttpClientFactory.newHttpClient();
        URI uri = new URIBuilder("http://www.google.com").build();
        String content = httpClient.GET(uri);
        System.out.println(content.length());
    }

}
