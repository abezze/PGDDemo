package com.fincons.pgd.dto.inputs;

import java.time.LocalDateTime;

import com.fincons.pgd.models.Delega;
import com.fincons.pgd.models.TipologiaDocumento;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DocumentiAllegatiReq {
	
	private Long id;

    private Long idDelega;

    private Long idTipologiaDocumento;

    private String nomeFile;

    private String pathBucket; 

    private LocalDateTime dataCaricamento;

    private String formatoFile; 

}
