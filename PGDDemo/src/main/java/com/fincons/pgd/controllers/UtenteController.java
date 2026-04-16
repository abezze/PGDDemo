package com.fincons.pgd.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fincons.pgd.dto.inputs.UtenteReq;
import com.fincons.pgd.response.Resp;
import com.fincons.pgd.services.interfaces.IMessaggioServices;
import com.fincons.pgd.services.interfaces.IUtenteServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("pgd/utente")
public class UtenteController {
	
	private final IUtenteServices utS;
	private final IMessaggioServices    msgS;
	
	@PostMapping("/create")
	public ResponseEntity<Resp> create(@RequestBody(required = true)  UtenteReq req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			utS.create(req);
			r.setMsg(msgS.get("rest_created"));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> list(){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r= utS.list();
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
		
	}
	
	@GetMapping("/findByIdPNR")
	public ResponseEntity<Object> findById (@RequestParam (required = true)  String userIdPNR){
		log.debug("getDelegationData userIdPNR= {}", userIdPNR);
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r= utS.findByIdPNR(userIdPNR);
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		log.debug("getDelegationData Resp = {}", r.toString());
		return ResponseEntity.status(status).body(r);
		
	}

}
