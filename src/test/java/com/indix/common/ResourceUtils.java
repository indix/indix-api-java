package com.indix.common;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.zip.GZIPInputStream;

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

    public static File getTestFile(ClassLoader classLoader, String pathToResource) throws IOException{
        URL url= classLoader.getResource(pathToResource);
        String fileName = url.getFile();
        File file = new File(fileName) ;
        return file;
    }

    public static InputStream getTestStream(ClassLoader classLoader, String pathToResource) throws IOException {
        InputStream is = classLoader.getResourceAsStream(pathToResource);
        if (null == is) {
            throw new IOException("resource not found: " + pathToResource);
        }
        return is;
    }


}
