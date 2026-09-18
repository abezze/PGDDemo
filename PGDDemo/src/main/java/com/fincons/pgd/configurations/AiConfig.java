package com.fincons.pgd.configurations;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.context.annotation.Primary;

@Configuration
public class AiConfig {


/* //rimosso per AKS che non usa il bean ChatClient
    @Bean
    public ChatClient chatClient(
            DashScopeChatModel chatModel,
            //ChatClient.Builder chatClientBuilder, // per locale e docker
            ChatMemory chatMemory) {

        return ChatClient.builder(chatModel)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .build();
    }*/
    @Bean
    @Primary
    public ChatModel primaryChatModel(DashScopeChatModel dashScopeChatModel) {
        return dashScopeChatModel;
    }
}
