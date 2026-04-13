package com.fincons.pgd.services.interfaces;

import com.fincons.pgd.dto.inputs.DelegaReq;
import com.fincons.pgd.dto.outputs.DelegaDTO;
import com.fincons.pgd.exceptions.PGDException;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.List;

public interface IDelegaServices {

    void create(DelegaReq req)throws IllegalArgumentException, OptimisticLockingFailureException, PGDException;
    List<DelegaDTO> list() throws Exception;
    DelegaDTO findById(Long id) throws Exception;
}
