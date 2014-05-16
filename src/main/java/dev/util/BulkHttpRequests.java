package dev.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class BulkHttpRequests {

    public static void main(String[] args) {
        if (args == null) throw new IllegalArgumentException("Input name of the file");

        File file = new File(args[0]);
        if (!file.exists()) throw new IllegalArgumentException("Invalid file");

        try {
            List<String> lines = Files.readLines(file, Charsets.UTF_8);
            for (String url : lines) {
                new HttpClientUtil().executeMethodGet(url);
            }

        } catch (IOException e) {
            System.out.println("Error when trying to readLines: " + e.getMessage());
        }
    }
}
