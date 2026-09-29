package com.fincons.pgd.services.implementations;

import static com.fincons.pgd.utilities.DelegaMapper.buildDelegaDto;

import java.time.LocalDateTime;
import java.util.List;

import com.fincons.pgd.models.Scenario;
import com.fincons.pgd.models.StatoDelega;
import com.fincons.pgd.models.TipoDelegato;
import com.fincons.pgd.models.enums.StatiDelega;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.fincons.pgd.dto.inputs.DelegaReq;
import com.fincons.pgd.dto.outputs.DelegaDTO;
import com.fincons.pgd.exceptions.PGDException;
import com.fincons.pgd.models.Delega;
import com.fincons.pgd.repositories.IDelegaRepository;
import com.fincons.pgd.services.interfaces.IDelegaServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class DelegaImpl implements IDelegaServices {
	
	private final IDelegaRepository utR;

	public String create(DelegaReq req) throws IllegalArgumentException,	OptimisticLockingFailureException, PGDException{
		Delega delega = new Delega();
        delega.setDataCreazione(LocalDateTime.now());
        delega.setDataUltimoAggiornamento(LocalDateTime.now());

        Scenario scenario = null;

        StatoDelega stato = null;

        TipoDelegato tipoDelegato = null;

        delega.setScenario(scenario);
        delega.setStatoCorrente(null);
        delega.setTipoDelegato(tipoDelegato);

        Delega save = utR.save(delega);

        return save.getCodiceUnivocoDelega();
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
