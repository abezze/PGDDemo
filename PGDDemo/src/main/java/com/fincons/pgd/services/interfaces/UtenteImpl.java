package com.fincons.pgd.services.interfaces;

import org.springframework.stereotype.Service;

import com.fincons.pgd.dto.inputs.UtenteReq;
import com.fincons.pgd.models.Utente;
import com.fincons.pgd.models.services.implementations.IUtenteServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UtenteImpl implements IUtenteServices {

	public void create(UtenteReq req) {
		Utente utente = new Utente();
		
		utente.setCognome("");
		
	}
}
