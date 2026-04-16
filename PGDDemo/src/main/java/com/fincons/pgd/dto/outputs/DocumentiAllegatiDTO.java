package com.fincons.pgd.dto.outputs;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class DocumentiAllegatiDTO {
	private Long id;

    private DelegaDTO delega;

    //private TipologiaDocumento tipologiaDocumento;

    private String nomeFile;

    private String pathBucket; 

    private LocalDateTime dataCaricamento;

    private String formatoFile; 

}
