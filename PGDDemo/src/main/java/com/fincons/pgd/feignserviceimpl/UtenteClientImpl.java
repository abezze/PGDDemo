package com.fincons.pgd.feignserviceimpl;

import com.fincons.pgd.feignclients.UtenteClient;
import com.fincons.pgd.models.Utente;
import com.fincons.pgd.repositories.IUtenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UtenteClientImpl implements UtenteClient {

	private final IUtenteRepository uteR;
	@Override
	public Utente getUtente(String idUtente) {
		
		Utente utente = uteR.findByIdPNR(idUtente);
		
		return utente;
	}

}
