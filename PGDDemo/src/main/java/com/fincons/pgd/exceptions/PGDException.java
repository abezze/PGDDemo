package com.fincons.pgd.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PGDException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PGDException() {
		super();
	}

	public PGDException(String message) {

		super(message);
        log.debug(message);
	}

}
