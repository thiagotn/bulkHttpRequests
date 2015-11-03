package dev.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class Analisador {

    public static void main(String[] args) throws IOException {
       new Analisador().analisar();
    }

    private void analisar() throws IOException {
        Set<Long> inscriptionsPlataforma = fileToSet(new File("analise-inscriptions-office365\\inscriptions-plataforma.txt"));
        Set<Long> inscriptionsOffice365 = fileToSet(new File("analise-inscriptions-office365\\inscriptions-base-office365.txt"));
        Set<Long> inscriptionsForaDaBaseOffice365 = new HashSet<Long>();
        for (Long inscription : inscriptionsPlataforma) {
           if (!inscriptionsOffice365.contains(inscription)) {
               inscriptionsForaDaBaseOffice365.add(inscription);
           }
        }
        Log.getInstance().generate(buildContent(inscriptionsForaDaBaseOffice365), "analise-inscriptions-office365\\inscriptions-fora-base-office365.txt");
    }

    private Set<Long> fileToSet(File file) throws IOException {
        Set<Long> inscriptions = new HashSet<Long>();
        List<String> inscriptionsPlataforma = Files.readLines(file, Charsets.UTF_8);
        for (String inscriptionPlataforma : inscriptionsPlataforma) {
            inscriptions.add(Long.parseLong(inscriptionPlataforma));
        }
        System.out.println("Arquivo " + file.getName() + " contém " + inscriptionsPlataforma.size() + " linhas.");
        return inscriptions;
    }

    private String buildContent(Set<Long> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("IDT_INSCRIPTION;\n");
        for (Long inscription : list) {
            builder.append(inscription);
            builder.append(";\n");
        }
        return builder.toString();
    }
}
