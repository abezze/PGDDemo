package com.fincons.pgd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PgdDemoApplication {

	public static void main(String[] args) {
        System.out.println(
                "AI_DASHSCOPE_API_KEY presente: " +
                        (System.getenv("AI_DASHSCOPE_API_KEY") != null)
        );
        System.out.println(
                "AI_DASHSCOPE_API_KEY presente: " +
                        (System.getenv("AI_DASHSCOPE_API_KEY") )
        );
        SpringApplication.run(PgdDemoApplication.class, args);
	}
 
}
