package com.fincons.pgd.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincons.pgd.dto.inputs.DocumentiAllegatiReq;
import com.fincons.pgd.response.Resp;
import com.fincons.pgd.services.interfaces.IDocumentiAllegatiServices;
import com.fincons.pgd.services.interfaces.IMessaggioServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rest/doc")
public class DocumentiAllegatiController {
	
	private final IDocumentiAllegatiServices utS;
	private final IMessaggioServices    msgS;
	
	@PostMapping("/create")
	public ResponseEntity<Resp> create(@RequestBody(required = true)  DocumentiAllegatiReq req){
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

}
