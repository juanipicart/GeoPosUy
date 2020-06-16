package com.exceptions;

public class NoSeRealizoOperacionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSeRealizoOperacionException() {
		// TODO Auto-generated constructor stub
	}

	public NoSeRealizoOperacionException(String message) {
		super("No se realizó la operacion de "+message);
		// TODO Auto-generated constructor stub
	}

}
