package dev.util;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class ContabilizaErros {

    public static void main(String[] args) throws IOException {
        String nomeDiretorio = "output";
        File diretorio = new File(nomeDiretorio);
        if (!diretorio.exists()) throw new IllegalArgumentException("Diretório " + nomeDiretorio + " não encontrado.");
        File[] listFiles = diretorio.listFiles();
        int totalFalhas = 0;
        for (int i = 0; i < listFiles.length; i++) {
            File arquivo = listFiles[i];
            if (arquivo.exists() && (!arquivo.getName().endsWith("200.json"))) {
                String request = Files.readFirstLine(arquivo, Charsets.UTF_8);
                System.out.println(arquivo.getName() + ";" + request);
                totalFalhas++;
            }
        }
        System.out.println("Total de " + totalFalhas + " falhas entre " + listFiles.length + " arquivos.");
    }
}
