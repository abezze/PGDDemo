package com.fincons.pgd.services.interfaces;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;

import com.fincons.pgd.dto.inputs.UtenteReq;
import com.fincons.pgd.dto.outputs.UtenteDTO;
import com.fincons.pgd.exceptions.PGDException;

public interface IUtenteServices {
	
	void create(UtenteReq req)throws IllegalArgumentException,	OptimisticLockingFailureException, PGDException;
	List<UtenteDTO> list() throws Exception;
	UtenteDTO findByIdPNR(String idPNR) throws Exception;

}
