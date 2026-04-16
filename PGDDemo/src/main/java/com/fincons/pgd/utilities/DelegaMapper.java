package com.fincons.pgd.utilities;

import static com.fincons.pgd.utilities.UtenteMapper.buildUtenteDto;

import java.util.List;

import com.fincons.pgd.dto.outputs.DelegaDTO;
import com.fincons.pgd.models.Delega;

public class DelegaMapper {
	
	public static DelegaDTO buildDelegaDto(Delega u) {
		return DelegaDTO.builder() 
				.id(u.getId())
				.codiceUnivocoDelega(u.getCodiceUnivocoDelega())
				.dataCreazione(u.getDataCreazione())
				.dataFineValidita(u.getDataFineValidita())
				.dataInizioValidita(u.getDataInizioValidita())
				.dataUltimoAggiornamento(u.getDataUltimoAggiornamento())
				.dataVideoRegistrazione(u.getDataVideoRegistrazione())
				.dicEsercenteRespGenitorialeApprovatore(u.getDicEsercenteRespGenitorialeApprovatore())
				.dicEsercenteRespGenitorialeDelegato(u.getDicEsercenteRespGenitorialeDelegato())
				.documenti( null)
				.esitoVerificaResponsabilitaGenitoriale(u.getEsitoVerificaResponsabilitaGenitoriale())
				.flagResponsabilitaGenitoriale(u.getFlagResponsabilitaGenitoriale())
				.idVideoChiamata(u.getIdVideoChiamata())
				.utenteDelegante(u.getUtenteDelegante()!=null? buildUtenteDto(u.getUtenteDelegante()): null)
				.utenteDelegato(u.getUtenteDelegato()!=null? buildUtenteDto(u.getUtenteDelegato()): null)
				.build();
				
	}
	
	public static List<DelegaDTO> buildDelegaDto (List<Delega> lU){
		
		return lU.stream()
				.map(u -> buildDelegaDto(u))
				.toList();
	}

}
