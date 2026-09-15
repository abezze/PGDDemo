package com.fincons.pgd.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MavenTool {


    private final Path projectPath;

    @Value("${git.repository.path}")
    private String repositoryPath;

    public MavenTool(
            @Value("${git.repository.path}") String projectPath ) {
        projectPath = projectPath +  "/PGDDemo";
        this.projectPath = Paths.get(projectPath);
    }

    @Tool(description = """
            Esegue 'mvn test' sul repository Git configurato.
            Utilizzare questo tool quando è necessario verificare
            se i test automatici del progetto passano.
            Restituisce l'output completo di Maven e l'exit code.
            """)
    public String runTests() {

        String os = System.getProperty("os.name").toLowerCase();

        boolean windows = os.contains("win");

        String command;
        String shell;

        if (windows) {
            shell = "cmd";
            command = "mvnw.cmd";
        } else {
            shell = "sh";
            command = "./mvnw";
        }

        System.out.println(">>> Maven runTests  <<< ");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    shell,
                    windows ? "/c" : "-c",
                    command + " test"
            );

            processBuilder
                    .directory(new java.io.File(repositoryPath))
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

            System.out.println(">>> Maven result  <<< " + output.toString());

            return output.toString();

        } catch (IOException e) {
            System.out.println(">>> Maven exc  <<< " + e.getMessage());
            return "Errore durante l'esecuzione di Maven: "
                    + e.getMessage();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            System.out.println(">>> Maven exc interr  <<< " + e.getMessage());
            return "Esecuzione Maven interrotta.";
        }
    }

    @Tool(description = """
                 Esegue Maven clean verify e analizza il report JaCoCo.

                  Usa questo tool quando l'utente chiede:
                  - coverage dei test
                  - percentuale di coverage
                  - code coverage
                  - JaCoCo coverage
                  - risultato della coverage dopo mvn verify

                  Il risultato contiene le percentuali REALI estratte da jacoco.xml.

                  NON ricavare la coverage dal log Maven.
                  NON stimare la coverage.
                  NON inventare valori.

                  Quando il tool restituisce lineCoverage, branchCoverage,
                  methodCoverage e classCoverage, usa esattamente questi valori
                  nella risposta all'utente.
            """)
    public MavenCoverageResult  verifyCoverage() {

        String os = System.getProperty("os.name").toLowerCase();

        boolean windows = os.contains("win");

        String command;
        String shell;

        if (windows) {
            shell = "cmd";
            command = "mvnw.cmd";
        } else {
            shell = "sh";
            command = "./mvnw";
        }
        System.out.println(">>> Maven verify Coverage  <<< ");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    shell,
                    windows ? "/c" : "-c",
                    command + " verify" ,
                    "-Dspring.devtools.restart.enabled=false"
            );

            processBuilder
                    .directory(new java.io.File(repositoryPath))
                    .redirectErrorStream(true);

            Process process = processBuilder.start();

            String output;

            try (InputStream inputStream = process.getInputStream()) {
                output = new String(
                        inputStream.readAllBytes(),
                        StandardCharsets.UTF_8
                );
            }

            int exitCode = process.waitFor();

            System.out.println(">>> Maven  verify Coverage output  <<< " + output);

            Path jacocoFile = projectPath
                    .resolve("target")
                    .resolve("site")
                    .resolve("jacoco")
                    .resolve("jacoco.xml");

            CoverageResult coverage = null;

            System.out.println(">>> Maven  Coverage jacocoFile path <<< " + jacocoFile.toAbsolutePath().toString());

            if (Files.exists(jacocoFile)) {
                coverage = readCoverage(jacocoFile);
            }

            if (coverage!=null){
                System.out.println(">>> Maven  Coverage  <<< " + coverage.toString());
            } else
                System.out.println(">>> Maven  Coverage null  <<< ");


            return new MavenCoverageResult(
                    exitCode == 0,
                    exitCode,
                    coverage.lineCoverage(),
                    coverage.branchCoverage(),
                    coverage.methodCoverage(),
                    coverage.classCoverage()
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "Errore durante l'esecuzione di Maven",
                    e
            );
        }
    }

    private CoverageResult readCoverage(Path jacocoFile)
            throws Exception {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        // Non validare il documento tramite DTD
        factory.setValidating(false);

        // Ignora il caricamento dei DTD esterni
        factory.setFeature(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                false
        );

        // Disabilita external entities
        factory.setFeature(
                "http://xml.org/sax/features/external-general-entities",
                false
        );

        factory.setFeature(
                "http://xml.org/sax/features/external-parameter-entities",
                false
        );

        Document document = factory
                .newDocumentBuilder()
                .parse(jacocoFile.toFile());

        NodeList counters =
                document.getElementsByTagName("counter");

        double line = 0;
        double branch = 0;
        double method = 0;
        double clazz = 0;

        for (int i = 0; i < counters.getLength(); i++) {

            Element counter =
                    (Element) counters.item(i);

            String type = counter.getAttribute("type");

            int missed =
                    Integer.parseInt(counter.getAttribute("missed"));

            int covered =
                    Integer.parseInt(counter.getAttribute("covered"));

            double percentage =
                    calculateCoverage(missed, covered);

            switch (type) {
                case "LINE" -> line = percentage;
                case "BRANCH" -> branch = percentage;
                case "METHOD" -> method = percentage;
                case "CLASS" -> clazz = percentage;
                default -> {
                    // ignorato
                }
            }
        }

        return new CoverageResult(
                line,
                branch,
                method,
                clazz
        );
    }

    private double calculateCoverage(int missed, int covered) {

        int total = missed + covered;

        if (total == 0) {
            return 100.0;
        }

        return covered * 100.0 / total;
    }

    record MavenCoverageResult(
            boolean success,
            int exitCode,
            double lineCoverage,
            double branchCoverage,
            double methodCoverage,
            double classCoverage
    ) {

    }

    record CoverageResult(
            double lineCoverage,
            double branchCoverage,
            double methodCoverage,
            double classCoverage
    ) {
        @Override
        public String toString() {
            return "CoverageResult{" +
                    "lineCoverage=" + lineCoverage +
                    ", branchCoverage=" + branchCoverage +
                    ", methodCoverage=" + methodCoverage +
                    ", classCoverage=" + classCoverage +
                    '}';
        }
    }
}
