package com.fincons.pgd.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fincons.pgd.models.MessageID;
import com.fincons.pgd.models.Messaggi;
import com.fincons.pgd.repositories.IMessaggiRepository;
import com.fincons.pgd.services.interfaces.IMessaggioServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessaggioImpl implements IMessaggioServices{
	
	private final IMessaggiRepository msgR;

	@Value("${lang}")
	private String lang;
	
	@Override
	public String get(String code) {
		log.debug("get  {} ",code);
		String r = null;
		Optional<Messaggi> m = msgR.findById(new MessageID(lang, code));
		if (m.isEmpty())
			r = code;
		else
			r = m.get().getMessaggio();		
		
		return r;
	}

}
