package com.fincons.pgd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PgdDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgdDemoApplication.class, args);
	}
 
}
