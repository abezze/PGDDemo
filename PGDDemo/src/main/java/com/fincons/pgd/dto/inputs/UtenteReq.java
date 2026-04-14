package com.fincons.pgd.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UtenteReq {
	
	private Long id;
    private String idPNR;
	private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String residenza;
    private String luogoDiNascita;
    private String codiceFiscale;
    private String indirizzoEmail;
    private Boolean flagEmailCertificata;
    private Boolean flagAccettazionePolicy;

}
