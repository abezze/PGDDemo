package com.fincons.pgd.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtenteReq {
	
	private Long id;

	private String nome;
    private String cognome;
    private String codiceFiscale;
    private String indirizzoEmail;
    private Boolean flagEmailCertificata;
    private Boolean flagAccettazioneProxy;

}
