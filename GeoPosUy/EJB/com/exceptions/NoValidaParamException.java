package com.exceptions;

public class NoValidaParamException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoValidaParamException() {
		super();
		
	}

	public NoValidaParamException(String message) {
		super("Fallo en Parametro " + message);
		
	}

}