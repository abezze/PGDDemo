package com.fincons.pgd.services.implementations;

import static com.fincons.pgd.utilities.UtenteMapper.buildUtenteDto;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.fincons.pgd.dto.inputs.UtenteReq;
import com.fincons.pgd.dto.outputs.UtenteDTO;
import com.fincons.pgd.exceptions.PGDException;
import com.fincons.pgd.models.Utente;
import com.fincons.pgd.repositories.IUtenteRepository;
import com.fincons.pgd.services.interfaces.IUtenteServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UtenteImpl implements IUtenteServices {
	
	private final IUtenteRepository utR;

	@Override
	public void create(UtenteReq req) throws IllegalArgumentException,	OptimisticLockingFailureException, PGDException{
		Utente utente = new Utente();
		
		utente.setCognome(req.getCognome());
		utente.setNome(req.getNome());
		utente.setCodiceFiscale(req.getCodiceFiscale());
		utente.setIndirizzoEmail(req.getIndirizzoEmail());
		utente.setDataNascita(req.getDataNascita());
		utente.setFlagAccettazionePolicy(req.getFlagAccettazionePolicy());
		utente.setFlagEmailCertificata(req.getFlagEmailCertificata());
		utente.setIdPNR(req.getIdPNR());
		utente.setLuogoDiNascita(req.getLuogoDiNascita());
		utente.setResidenza(req.getResidenza());
		
		utR.save(utente);
		
	}

	@Override
	public List<UtenteDTO> list() throws Exception {
		log.debug("list Utente");
		List<Utente> lA = utR.findAll();
		return buildUtenteDto(lA);
	}

	@Override
	public UtenteDTO findByIdPNR(String idPNR) throws Exception {

		Utente utente = utR.findByIdPNR(idPNR);
		
		return buildUtenteDto(utente);
	}
}
