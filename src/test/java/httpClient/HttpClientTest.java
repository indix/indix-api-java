package httpClient;

import exception.IndixApiException;
import httpClient.impl.HttpClientFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {

    @Test
    public void httpClientGetTest() throws URISyntaxException, IOException, IndixApiException {
        HttpClient httpClient = HttpClientFactory.newHttpClient();
        URI uri = new URIBuilder("http://www.google.com").build();
        String content = httpClient.GET(uri);
        System.out.println(content.length());
    }

    @Test(expected = FileNotFoundException.class)
    public void httpClientPostFileTest() throws URISyntaxException, IOException, IndixApiException {
        HttpClient httpClient = HttpClientFactory.newHttpClient();
        URI uri = new URIBuilder("http://www.example.com").build();
        List<NameValuePair> expectedValue = new ArrayList<NameValuePair>();
        expectedValue.add(new BasicNameValuePair("app_id", "123"));
        String content = httpClient.POST(uri,expectedValue,new File("lookup.jsonl"));
        System.out.println(content.length());
    }

}
