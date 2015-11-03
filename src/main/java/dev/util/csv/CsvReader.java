package dev.util.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class CsvReader {

    private File file;
    private List<String> lines = new ArrayList<String>();

    public static CsvReader read() {
        return new CsvReader();
    }

    public CsvReader withName(String fileName) throws IOException {
        file = new File(fileName);
        lines = Files.readLines(file, Charsets.UTF_8);
        return this;
    }

    public List<String> getLines() {
        return lines;
    }
}
