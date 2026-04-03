package com.fincons.pgd.dto.outputs;

import java.util.List;
import java.util.Set;

import com.fincons.pgd.models.AssociativaUtenteProfilo;
import com.fincons.pgd.models.Delega;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private Set<AssociativaUtenteProfilo> profili;
    private List<Delega> delegheConcesse;

}
