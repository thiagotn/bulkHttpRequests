package dev.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;

import com.google.common.base.Charsets;

public class HttpClientUtil {

    public void executeMethodGet(String url) throws URIException, NullPointerException {
        URI uri = new URI(url, false, Charsets.UTF_8.toString());
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(uri.toString());

        InputStream responseBodyAsStream = null;
        int statusCode;

        try {
            statusCode = client.executeMethod(method);
            responseBodyAsStream = method.getResponseBodyAsStream();
            new Log().generate(url, responseBodyAsStream, statusCode);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
    }

    public Map<String, String> parseParams(String allParams) {
        Map<String, String> params = new HashMap<String, String>();
        String[] param = allParams.split("&");
        for (int i = 0; i < (param.length -1); i++) {
            String[] keyValue = param[i].split("=");
            params.put(keyValue[0], keyValue[1]);
        }
        return params;
    }
}
