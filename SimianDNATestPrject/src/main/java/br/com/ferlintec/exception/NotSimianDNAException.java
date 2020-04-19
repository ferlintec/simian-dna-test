package br.com.ferlintec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotSimianDNAException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NotSimianDNAException(String exception) {
		super(exception);
	}
	
}