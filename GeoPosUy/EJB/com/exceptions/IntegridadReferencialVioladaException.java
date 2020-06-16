package com.exceptions;

public class IntegridadReferencialVioladaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntegridadReferencialVioladaException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IntegridadReferencialVioladaException(String message) {
		super("Error - Existe una referencia en "+ message +" . No se puede completar la operacion");
		// TODO Auto-generated constructor stub
	}

}
