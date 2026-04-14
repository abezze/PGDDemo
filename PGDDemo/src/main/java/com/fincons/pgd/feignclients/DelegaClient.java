package com.fincons.pgd.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.fincons.pgd.dto.outputs.DelegaDTO;
import com.fincons.pgd.models.Utente;

@FeignClient(name = "delega-service", url = "http://localhost:9070")
public interface DelegaClient {
	
	@GetMapping("/deleghe")
    List<DelegaDTO> getDelega(Utente utente);

}
