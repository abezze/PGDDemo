package com.fincons.pgd.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.fincons.pgd.models.Utente;

@FeignClient(name = "utente-service", url = "http://localhost:9070")
public interface UtenteClient {
	
	@GetMapping("/deleghe")
    Utente getUtente(String idUtente);

}
