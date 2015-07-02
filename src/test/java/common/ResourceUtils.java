package common;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class ResourceUtils {

    /*
     * Retrieves a resource for this classLoader and returns a String representation
     * NOTE: Don't use this for resources that are huge
     */
    public static String getTestResource(ClassLoader classLoader, String pathToResource) throws IOException {
        InputStream is = classLoader.getResourceAsStream(pathToResource);
        if (null == is) {
            throw new IOException("resource not found: " + pathToResource);
        }

        StringWriter sw = new StringWriter();
        try {
            IOUtils.copy(is, sw);
        } finally {
            is.close();
        }
        return sw.toString();
    }
}
