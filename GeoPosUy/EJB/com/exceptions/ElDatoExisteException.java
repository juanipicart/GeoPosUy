package com.exceptions;

public class ElDatoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElDatoExisteException() {
		super();
	}

	public ElDatoExisteException(String message) {
		super("Error - El dato de "+ message +" ya se encuentra registrado en el sistema");
		// TODO Auto-generated constructor stub
	}

}
