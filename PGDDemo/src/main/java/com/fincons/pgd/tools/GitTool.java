package com.fincons.pgd.tools;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class GitTool {

    @Tool(description = "Restituisce il branch Git corrente del repository")
    public String getCurrentBranch(String repositoryPath) {
        System.out.println(">>> GIT current branch chiamato <<<");
        try (Repository repository = openRepository(repositoryPath)) {
            return repository.getBranch();
        } catch (IOException e) {
            return "Errore nell'apertura del repository: " + e.getMessage();
        }
    }

    @Tool(description = "Restituisce gli ultimi commit del repository Git")
    public String getRecentCommits(String repositoryPath) {

        try (Repository repository = openRepository(repositoryPath);
             Git git = new Git(repository)) {

            StringBuilder result = new StringBuilder();

            for (RevCommit commit : git.log()
                    .setMaxCount(5)
                    .call()) {

                result.append(commit.getName(), 0, 8)
                        .append(" - ")
                        .append(commit.getShortMessage())
                        .append("\n");
            }

            return result.toString();

        } catch (IOException | GitAPIException e) {
            return "Errore nella lettura dei commit: " + e.getMessage();
        }
    }

    @Tool(description = "Restituisce lo stato corrente del repository Git, indicando file modificati, aggiunti, rimossi e non tracciati")
    public String getGitStatus(String repositoryPath) {

        try (Repository repository = openRepository(repositoryPath);
             Git git = new Git(repository)) {

            Status status = git.status().call();

            StringBuilder result = new StringBuilder();

            result.append("Branch corrente: ")
                    .append(repository.getBranch())
                    .append("\n\n");

            result.append("File modificati:\n");
            status.getModified().forEach(file ->
                    result.append("  MODIFIED: ").append(file).append("\n"));

            result.append("File aggiunti:\n");
            status.getAdded().forEach(file ->
                    result.append("  ADDED: ").append(file).append("\n"));

            result.append("File rimossi:\n");
            status.getRemoved().forEach(file ->
                    result.append("  REMOVED: ").append(file).append("\n"));

            result.append("File non tracciati:\n");
            status.getUntracked().forEach(file ->
                    result.append("  UNTRACKED: ").append(file).append("\n"));

            return result.toString();

        } catch (IOException | GitAPIException e) {
            return "Errore nella lettura dello stato Git: " + e.getMessage();
        }
    }

    private Repository openRepository(String repositoryPath) throws IOException {

        return new FileRepositoryBuilder()
                .setGitDir(Path.of(repositoryPath, ".git").toFile())
                .readEnvironment()
                .findGitDir()
                .build();
    }
}
