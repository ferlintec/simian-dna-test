package br.com.ferlintec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDNAException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidDNAException(String exception) {
		super(exception);
	}
	
}
