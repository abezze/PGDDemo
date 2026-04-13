package com.fincons.pgd.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class UtenteDTO {
	private Long id;

	private String nome;
    private String cognome;
    private String codiceFiscale;
    private String indirizzoEmail;
    private Boolean flagEmailCertificata;
    private Boolean flagAccettazioneProxy;
    //private Set<AssociativaUtenteProfiloDTO> profili;
    private List<DelegaDTO> delegheConcesse;

}
