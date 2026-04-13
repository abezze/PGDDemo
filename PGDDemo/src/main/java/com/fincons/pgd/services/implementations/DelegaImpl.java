package com.fincons.pgd.services.implementations;

import com.fincons.pgd.dto.inputs.DelegaReq;
import com.fincons.pgd.dto.outputs.DelegaDTO;
import com.fincons.pgd.exceptions.PGDException;
import com.fincons.pgd.models.Delega;
import com.fincons.pgd.repositories.IDelegaRepository;
import com.fincons.pgd.services.interfaces.IDelegaServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.fincons.pgd.utilities.DelegaMapper.buildDelegaDto;

@RequiredArgsConstructor
@Slf4j
@Service
public class DelegaImpl implements IDelegaServices {
	
	private final IDelegaRepository utR;

	public void create(DelegaReq req) throws IllegalArgumentException,	OptimisticLockingFailureException, PGDException{
		Delega delega = new Delega();
		

		
		utR.save(delega);
		
	}

	@Override
	public List<DelegaDTO> list() throws Exception {
		log.debug("list Delega");
		List<Delega> lA = utR.findAll();
		return buildDelegaDto(lA);
	}


	@Override
	public DelegaDTO findById(Long id) throws Exception {
		Delega delega = utR.findById(id)
				.orElseThrow(() -> new PGDException(" Delega non trovata"));

		return buildDelegaDto(delega);
	}


}
