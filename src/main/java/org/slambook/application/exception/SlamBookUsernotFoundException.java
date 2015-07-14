package org.slambook.application.exception;

import org.springframework.security.core.AuthenticationException;

public class SlamBookUsernotFoundException extends AuthenticationException{

	public SlamBookUsernotFoundException(String msg) {
		super(msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
