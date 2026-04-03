package com.fincons.pgd.utilities;

import com.fincons.pgd.dto.outputs.UtenteDTO;
import com.fincons.pgd.models.Utente;

public class UtenteMapper {
	
	public static UtenteDTO buildUtenteDto(Utente u) {
		return UtenteDTO.builder() 
				.id(u.getId())
				.nome(u.getNome())
				.cognome(u.getCognome())
				.codiceFiscale(u.getCodiceFiscale())
				.indirizzoEmail(u.getIndirizzoEmail())
				.flagEmailCertificata(u.getFlagEmailCertificata())
				.flagAccettazioneProxy(u.getFlagAccettazioneProxy())
				.build();
				
	}

}
