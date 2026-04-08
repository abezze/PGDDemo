package com.fincons.pgd.services.implementations;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fincons.pgd.dto.inputs.DocumentiAllegatiReq;
import com.fincons.pgd.models.DocumentiAllegati;
import com.fincons.pgd.models.services.interfaces.IDocumentiAllegatiServices;
import com.fincons.pgd.repositories.IDocumentiAllegatiRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class DocumentiAllegatiImpl implements IDocumentiAllegatiServices {
	
	private final IDocumentiAllegatiRepository utR;
	//private final IDelegaRepository delR;

	public void create(DocumentiAllegatiReq req) throws Exception{
		DocumentiAllegati doc = new DocumentiAllegati();
		doc.setDataCaricamento(LocalDateTime.now());
		
		//Delega delega = 
		
		//doc.setDelega(req.getIdDelega());
		doc.setFormatoFile(req.getFormatoFile());
		doc.setPathBucket(null);
		
		
		utR.save(doc);
		
	}
}
