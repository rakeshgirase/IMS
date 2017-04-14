package com.exuberant.ims.storekeeper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class URLService {

    private URLService() {
    }

    public static URL getFileAsResoure(String path) {
        try {
            return new File("src/main/java/view/" + path).toURI().toURL();
        } catch (MalformedURLException e) {
            String message = "Failed to load file: " + path;
            System.err.println(message);
            throw new RuntimeException(message);
        }
    }

    public static InputStream getFileAsStream(String path) {
        try {
            return getFileAsResoure(path).openStream();
        } catch (MalformedURLException e) {
            String message = "Failed to load file: " + path;
            System.err.println(message);
            throw new RuntimeException(message);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
