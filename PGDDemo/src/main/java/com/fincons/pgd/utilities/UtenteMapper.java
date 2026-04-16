package com.fincons.pgd.utilities;

import java.util.List;

import com.fincons.pgd.dto.outputs.UtenteDTO;
import com.fincons.pgd.models.Utente;

public class UtenteMapper {
	
	public static UtenteDTO buildUtenteDto(Utente u) {
		return UtenteDTO.builder() 
				.id(u.getId())
				.idPNR(u.getIdPNR())
				.nome(u.getNome())
				.cognome(u.getCognome())
				.codiceFiscale(u.getCodiceFiscale())
				.indirizzoEmail(u.getIndirizzoEmail())
				.flagEmailCertificata(u.getFlagEmailCertificata())
				.flagAccettazionePolicy(u.getFlagAccettazionePolicy())
				.dataNascita(u.getDataNascita())
				.delegheConcesse(null)
				.luogoDiNascita(u.getLuogoDiNascita())
				.residenza(u.getResidenza())
				.build();
				
	}
	
	public static List<UtenteDTO> buildUtenteDto (List<Utente> lU){
		
		return lU.stream()
				.map(u -> buildUtenteDto(u))
				.toList();
	}

}
