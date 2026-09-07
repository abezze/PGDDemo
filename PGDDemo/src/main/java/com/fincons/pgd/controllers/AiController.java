package com.fincons.pgd.controllers;

import com.fincons.pgd.tools.DateTimeTool;
import com.fincons.pgd.tools.GitTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
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
    private final ChatMemory chatMemory;

    public AiController(ChatClient.Builder chatClientBuilder, DateTimeTool dateTimeTool, GitTool gitTool, ChatMemory chatMemory) {
        this.chatClient = chatClientBuilder.build();
        this.dateTimeTool = dateTimeTool;
        this.gitTool = gitTool;
        this.chatMemory = chatMemory;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam (name = "message", required = true) String message,
                        @RequestParam(name = "conversationId", defaultValue = "default") String conversationId ) {
        return chatClient
                .prompt()
                .system("""
                Sei un AI DevOps Assistant.

                Il tuo compito è aiutare lo sviluppatore
                ad analizzare repository Git e codice Java.

                Puoi utilizzare i tool disponibili per:
                - conoscere il branch corrente
                - leggere gli ultimi commit
                - verificare lo stato del repository
                - analizzare le modifiche presenti nel repository

                Usa i tool quando hai bisogno di informazioni
                che non puoi conoscere direttamente.

                Non inventare mai informazioni sul repository.
                """)
                .user(message)
                .tools(dateTimeTool, gitTool)
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .advisors(a -> a.param(
                        ChatMemory.CONVERSATION_ID,
                        conversationId))
                .call()
                .content();
    }
}