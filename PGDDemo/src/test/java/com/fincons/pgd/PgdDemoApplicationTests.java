package com.fincons.pgd;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import com.fincons.pgd.delega.DelegaControllerTest;
import com.fincons.pgd.utente.UtenteControllerTest;

/*@Suite
@SuiteDisplayName("PGD ordered Test Suite")
@SelectClasses({
	UtenteControllerTest.class,
	DelegaControllerTest.class
})*/



@SpringBootTest
class PgdDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
