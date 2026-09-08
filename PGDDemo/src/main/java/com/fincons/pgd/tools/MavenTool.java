package com.fincons.pgd.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class MavenTool {

    @Tool(description = """
        Esegue 'mvn test' sul repository Git configurato.
        Utilizzare questo tool quando è necessario verificare
        se i test automatici del progetto passano.
        Restituisce l'output completo di Maven e l'exit code.
        """)
    public String runTests() {

        System.out.println(">>> Maven runTests  <<< " );
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd",
                    "/c",
                    "mvnw.cmd",
                    "test"
            );

            processBuilder
                    .directory(new java.io.File("C:/Users/Betacom/IdeaProjects/PGDDemo"))
                    .redirectErrorStream(true);

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();

            output.append("\nExit code: ")
                    .append(exitCode);

            return output.toString();

        } catch (IOException e) {

            return "Errore durante l'esecuzione di Maven: "
                    + e.getMessage();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            return "Esecuzione Maven interrotta.";
        }
    }
}