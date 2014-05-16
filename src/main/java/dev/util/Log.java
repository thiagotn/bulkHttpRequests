package dev.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.IOUtils;

public class Log {

    public static final String DEFAULT_EXTENSION = ".json";

    public void generate(String url, InputStream content, Integer statusCode) {
        InputStream responseBodyAsStream = null;
        OutputStream fileOutputStream = null;

        try {
            String json = IOUtils.toString(content);
            StringBuilder result = new StringBuilder();
            result.append(url);
            result.append("\n");
            result.append(json);
            InputStream response = IOUtils.toInputStream(result.toString());

            responseBodyAsStream = content;

            String time = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss-SSS").format(new GregorianCalendar().getTime());

            String name = "request-" + time + "-status-" + statusCode + DEFAULT_EXTENSION;
            File file = new File("output\\" + name);
            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(response, fileOutputStream);
            System.out.println("> " + name + "");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            IOUtils.closeQuietly(responseBodyAsStream);
            IOUtils.closeQuietly(fileOutputStream);
        }
    }
}
