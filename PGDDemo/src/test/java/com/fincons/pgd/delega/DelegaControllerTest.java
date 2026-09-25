package com.fincons.pgd.delega;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fincons.pgd.controllers.DelegaController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DelegaControllerTest {
	
	@Autowired
	private DelegaController delegaC;
	
	@Test
	@Order(1)	
	public void createDelegaTest() {
		log.debug("create delega");
		
	}

}
