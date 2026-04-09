package com.fincons.pgd.utilities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fincons.pgd.dto.outputs.DocumentiAllegatiDTO;
import com.fincons.pgd.models.DocumentiAllegati;
import com.fincons.pgd.services.interfaces.IUploadServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DocumentiAllegatiMapper {
	
	private final IUploadServices uplS; 
	
	public DocumentiAllegatiDTO buildDocumentiAllegatiDto(DocumentiAllegati d) {
		
		String doc = null;
			try {
				doc = uplS.buildUrl(d.getNomeFile());
			} catch(Exception ignore) {}
		
		return DocumentiAllegatiDTO.builder() 
				.id(d.getId())
				.nomeFile(doc)
				.dataCaricamento(LocalDateTime.now())
				.formatoFile(d.getFormatoFile())
				.build();
				
	}
	
	public List<DocumentiAllegatiDTO> buildDocumentiAllegatiDto (List <DocumentiAllegati> lD){
		
		return lD.stream()
		.map(d -> buildDocumentiAllegatiDto(d))
		.toList();
	}

}
