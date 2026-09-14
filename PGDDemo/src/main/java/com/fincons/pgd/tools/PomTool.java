package com.fincons.pgd.tools;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PomTool {

    @Value("${git.repository.path}")
    private String repositoryPath;

    @Tool(description = """
            Legge il file pom.xml del progetto Maven corrente.
            Usa questo tool quando l'utente chiede informazioni
            sulle dipendenze, versioni, plugin, Java, Spring Boot,
            Spring AI o sulla configurazione Maven del progetto.
            Non inventare informazioni: usa esclusivamente il contenuto
            restituito dal pom.xml.
            """)
    public String readPom() {


        repositoryPath = repositoryPath +  "/PGDDemo";
        Path pomPath = Path.of(repositoryPath, "pom.xml");
        System.out.println(">>> Reading Pom  repositoryPath <<< " + repositoryPath);
        try {
            if (!Files.exists(pomPath)) {
                return "Errore: pom.xml non trovato in " + pomPath;
            }

            return Files.readString(pomPath);

        } catch (IOException e) {
            return "Errore nella lettura del pom.xml: " + e.getMessage();
        }
    }
}
