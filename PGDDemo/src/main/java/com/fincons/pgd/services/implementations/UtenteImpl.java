package com.fincons.pgd.services.implementations;

import org.springframework.stereotype.Service;

import com.fincons.pgd.dto.inputs.UtenteReq;
import com.fincons.pgd.models.Utente;
import com.fincons.pgd.models.services.interfaces.IUtenteServices;
import com.fincons.pgd.repositories.IUtenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UtenteImpl implements IUtenteServices {
	
	private final IUtenteRepository utR;

	public void create(UtenteReq req) throws Exception{
		Utente utente = new Utente();
		
		utente.setCognome(req.getCognome());
		utente.setNome(req.getNome());
		utente.setCodiceFiscale(req.getCodiceFiscale());
		utente.setIndirizzoEmail(req.getIndirizzoEmail());
		
		utR.save(utente);
		
	}
}
