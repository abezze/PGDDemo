package com.fincons.pgd.dto.outputs;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("all")
@Getter
@Setter
@ToString
public class DelegationDataDTO {
    private UtenteDTO utente;
    private List<DelegaDTO> deleghe;
    public DelegationDataDTO(UtenteDTO utenteDto, List<DelegaDTO> deleghe) {
		this.deleghe= deleghe;
		this.utente=utenteDto;
	}
	

}
