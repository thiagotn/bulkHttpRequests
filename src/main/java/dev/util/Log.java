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
    public static Log log = null;
    private StringBuilder logger = new StringBuilder();
    private boolean sysout = false;

    private Log() {

    }

    private Log(boolean sysout) {
        this.sysout = sysout;
    }

    public static Log getInstance(boolean sysout) {
        if (log == null) {
            log = new Log(sysout);
        }
        return log;
    }

    public static Log getInstance() {
        if (log == null) {
            log = new Log();
        }
        return log;
    }

    public void generate(String url, InputStream content, Integer statusCode) {
        InputStream inputStream = null;
        OutputStream fileOutputStream = null;

        try {
            String json = IOUtils.toString(content);
            StringBuilder result = new StringBuilder();
            result.append(url);
            result.append("\n");
            result.append(json);
            inputStream = IOUtils.toInputStream(result.toString());

            String time = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss-SSS").format(new GregorianCalendar().getTime());

            String name = "request-" + time + "-status-" + statusCode + DEFAULT_EXTENSION;
            File file = new File("output\\" + name);

            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, fileOutputStream);
            log.info("Gerado arquivo de log: " + file.getAbsolutePath() + "");

        } catch (Exception e) {
            log.info("Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    public void info(String content) {
        StringBuilder line = new StringBuilder().append(logDate()).append(" ").append(content);
        if (sysout) {
            System.out.println(line);
        }
        logger.append(line).append("\n");
    }

    public void close(){
        generate(logger.toString(), logFileName());
    }

    private static String logFileName() {
        GregorianCalendar calendar = new GregorianCalendar();
        String name = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(calendar.getTime());
        return "log\\log-" + name + ".txt";
    }

    private static String logDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss,SSS").format(calendar.getTime()));
        builder.append("]");
        return builder.toString();
    }

    public void generate(String content, String fileName) {
        InputStream inputStream = null;
        OutputStream fileOutputStream = null;
        try {
            inputStream = IOUtils.toInputStream(content);
            File file = new File(fileName);
            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (Exception e) {
            log.info("Erro: " + e.getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fileOutputStream);
        }
    }
}
