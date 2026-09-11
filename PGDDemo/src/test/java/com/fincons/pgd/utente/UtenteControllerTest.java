package com.fincons.pgd.utente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fincons.pgd.utilities.GeneratoreCodiceFiscaleTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fincons.pgd.controllers.UtenteController;
import com.fincons.pgd.dto.inputs.UtenteReq;
import com.fincons.pgd.response.Resp;
import static com.fincons.pgd.utilities.Utils.stringToDate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteControllerTest {
	
	@Autowired
	private UtenteController uteC;
	
	@Test
	@Order(1)	
	public void createUtenteTest() {
		log.debug("create utente");
		
		UtenteReq ute = new UtenteReq();

        String cf = GeneratoreCodiceFiscaleTest.generaCodiceFiscaleCasuale();
		ute.setCodiceFiscale(cf);
		ute.setCognome("Bezze");
		ute.setNome("Giovanni");
		try {
			ute.setDataNascita(stringToDate("19/08/1938"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ute.setFlagAccettazionePolicy(true);
		ute.setFlagEmailCertificata(false);
		ute.setIdPNR("ABCDFGETHL");
		ute.setIndirizzoEmail("giovannibezze@fincons.it");
		ute.setLuogoDiNascita("Brugine");
		ute.setResidenza("Viganò");
		
		ResponseEntity<Resp> resp = uteC.create(ute);
		assertEquals(HttpStatus.CREATED, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
		
		
	}

}
