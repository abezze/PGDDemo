package com.fincons.pgd.tools;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@Component
public class GitTool {

    @Value("${git.repository.path}")
    private String repositoryPath;

    @Tool(description = "Restituisce il branch Git corrente del repository")
    public String getCurrentBranch() {
        System.out.println(">>> GIT current branch chiamato repositoryPath<<< " + repositoryPath);
        try (Repository repository = openRepository(repositoryPath)) {
            return repository.getBranch();
        } catch (IOException e) {
            return "Errore nell'apertura del repository: " + e.getMessage();
        }
    }

    @Tool(description = "Restituisce gli ultimi commit del repository Git")
    public String getRecentCommits() {
        System.out.println(">>> GIT getRecentCommits chiamato repositoryPath<<<" + repositoryPath);
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
    public String getGitStatus() {
        System.out.println(">>> GIT getGitStatus chiamato repositoryPath <<< " +repositoryPath);
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

    @Tool(description = "Restituisce il contenuto completo delle modifiche non committate nel repository Git")
    public String getDiff() {
        System.out.println(">>> GIT getDiff chiamato su repositoryPath : <<< " +repositoryPath);
        try (Repository repository = openRepository(repositoryPath);
             RevWalk revWalk = new RevWalk(repository)) {

            RevCommit headCommit = revWalk.parseCommit(
                    repository.resolve(Constants.HEAD));

            AbstractTreeIterator oldTree = prepareTreeParser(
                    repository,
                    headCommit.getTree().getId());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            try (DiffFormatter formatter = new DiffFormatter(outputStream)) {

                formatter.setRepository(repository);

                formatter.format(
                        oldTree,
                        new org.eclipse.jgit.treewalk.FileTreeIterator(repository));

            }

            String diff = outputStream.toString(StandardCharsets.UTF_8);

            if (diff.isBlank()) {
                return "Non ci sono modifiche nel working tree.";
            }

            return diff;

        } catch (IOException e) {
            return "Errore nel recupero del diff: " + e.getMessage();
        }
    }

    private Repository openRepository(String repositoryPath) throws IOException {
        Repository repository =  new FileRepositoryBuilder()
                .setWorkTree(Path.of(repositoryPath).toFile())
                .readEnvironment()
                .findGitDir()
                .build();
        System.out.println(">>> repositoryPath = " + repositoryPath);
        System.out.println(">>> gitDir         = " + repository.getDirectory());
        System.out.println(">>> workTree       = " + repository.getWorkTree());
        System.out.println(">>> isBare         = " + repository.isBare());
        System.out.println(">>> branch         = " + repository.getBranch());

        return repository;
    }

    private AbstractTreeIterator prepareTreeParser(
            Repository repository,
            org.eclipse.jgit.lib.AnyObjectId objectId) throws IOException {

        try (RevWalk walk = new RevWalk(repository)) {

            RevCommit commit = walk.parseCommit(objectId);
            CanonicalTreeParser treeParser = new CanonicalTreeParser();

            try (ObjectReader reader = repository.newObjectReader()) {
                treeParser.reset(reader, commit.getTree());
            }

            return treeParser;
        }
    }
}
