package com.fincons.pgd.feignserviceimpl;

import java.util.List;

import com.fincons.pgd.dto.outputs.DelegaDTO;
import com.fincons.pgd.feignclients.DelegaClient;
import com.fincons.pgd.models.Delega;
import com.fincons.pgd.models.Utente;
import com.fincons.pgd.repositories.IDelegaRepository;
import static com.fincons.pgd.utilities.DelegaMapper.buildDelegaDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class DelegaClientImpl implements DelegaClient {

	private final IDelegaRepository deleR;
	@Override
	public List<DelegaDTO> getDelega(Utente utente) {
		
		List<Delega> byUtenteDelegatoOrUtenteDelegante = deleR.findByUtenteDelegatoOrUtenteDelegante(utente, utente);
		
		return buildDelegaDto(byUtenteDelegatoOrUtenteDelegante);
		
	}

}
