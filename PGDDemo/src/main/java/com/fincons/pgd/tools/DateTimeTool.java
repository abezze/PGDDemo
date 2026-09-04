package com.fincons.pgd.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateTimeTool {

    @Tool(description = "Restituisce la data e ora corrente")
    public String getCurrentDateTime() {
        System.out.println(">>> DATE TIME TOOL CHIAMATO <<<");
        return LocalDateTime.now().toString();
    }
}
