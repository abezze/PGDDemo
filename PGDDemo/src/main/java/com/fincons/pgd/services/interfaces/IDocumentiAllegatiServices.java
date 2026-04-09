package com.fincons.pgd.services.interfaces;

import org.springframework.dao.OptimisticLockingFailureException;

import com.fincons.pgd.dto.inputs.DocumentiAllegatiReq;
import com.fincons.pgd.exceptions.PGDException;

public interface IDocumentiAllegatiServices {
	
	void create(DocumentiAllegatiReq req)throws IllegalArgumentException,	OptimisticLockingFailureException, PGDException;
	
	
}
