package dev.util.csv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class CsvWriter {

    public StringBuilder content = new StringBuilder();
    public String name;

    public static CsvWriter csv() {
        return new CsvWriter();
    }

    public CsvWriter withCotent(StringBuilder content) {
        this.content = content;
        return this;
    }

    public CsvWriter withName(String name) {
        this.name = name;
        return this;
    }

    public CsvWriter write() {
        InputStream inputStream = null;
        OutputStream fileOutputStream = null;
        try {
            inputStream = IOUtils.toInputStream(content.toString());
            File file = new File(name);
            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, fileOutputStream);
            System.out.println("Arquivo gerado: " + name);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fileOutputStream);
        }
        return this;
    }
}
