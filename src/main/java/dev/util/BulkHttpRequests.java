package dev.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class BulkHttpRequests {

    private static final File DEFAULT_FILE = new File("requests");
    private static Log log = Log.getInstance(true);

    public static void main(String[] args) throws IOException {
        log.info("Iniciando processo.");
        if (args.length != 1) {
            log.info("Utilizando arquivo default: " + DEFAULT_FILE.getAbsolutePath());
            execute(DEFAULT_FILE);
        } else {
            File file = new File(args[0]);
            log.info("Utilizando arquivo: " + file.getAbsolutePath());
            execute(file);
        }
        log.info("Finalizando processo.");
        log.close();
    }

    private static void execute(File file) throws IOException {
        try {
            List<String> lines = Files.readLines(file, Charsets.UTF_8);
            int l = 0;
            log.info("Total de " + lines.size() + " linha(s) para ser(em) processada(s).");
            for (String url : lines) {
                l++;
                if (StringUtils.isEmpty(url)) {
                    log.info("Linha: " + l + " ignorada.");
                } else {
                    new HttpClientUtil().executeMethodGet(url);
                }
            }
        } catch (IOException e) {
            log.info("Erro ao tentar ler as linhas: " + e.getMessage());
        }
    }
}
