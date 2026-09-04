package com.fincons.pgd.controllers;

import com.fincons.pgd.tools.DateTimeTool;
import com.fincons.pgd.tools.GitTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final ChatClient chatClient;
    private final DateTimeTool dateTimeTool;
    private final GitTool gitTool;

    public AiController(ChatClient.Builder chatClientBuilder, DateTimeTool dateTimeTool, GitTool gitTool) {
        this.chatClient = chatClientBuilder.build();
        this.dateTimeTool = dateTimeTool;
        this.gitTool = gitTool;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam (name = "message", required = true) String message) {
        return chatClient
                .prompt()
                .user(message)
                .tools(dateTimeTool, gitTool)
                .call()
                .content();
    }
}