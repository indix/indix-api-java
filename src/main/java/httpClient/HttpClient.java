package httpClient;

import exception.IndixApiException;
import org.apache.http.NameValuePair;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

/**
 * Interface defining the expected contract for http clients
 */
public interface HttpClient extends Closeable {

    /**
     * Executes a GET request and retrieves the response body as String
     */
    String GET(URI uri) throws IOException, IndixApiException;

    /**
     * Executes a GET request and retrieves the response body as Stream
     */
    InputStream GETStream(URI uri) throws IOException, IndixApiException;

    /**
     * Executes a POST request and retrieves the response body as String
     */
    String POST(URI uri , List<NameValuePair> params) throws IOException, IndixApiException;

    /**
     * Executes a multipart entity POST request and retrieves the response body as String
     */
    String POST(URI uri, List<NameValuePair> params , File file) throws IOException, IndixApiException;

    void close() throws IOException;
}
