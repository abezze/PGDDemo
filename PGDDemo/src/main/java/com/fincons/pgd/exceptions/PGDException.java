package com.fincons.pgd.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PGDException  extends RuntimeException{

	public PGDException() {
		super();
	}

	public PGDException(String message) {
		log.debug(message);
		super(message);
	}

}
