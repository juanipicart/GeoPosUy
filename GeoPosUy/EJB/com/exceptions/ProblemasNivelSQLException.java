package com.exceptions;

public class ProblemasNivelSQLException  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProblemasNivelSQLException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProblemasNivelSQLException(String message) {
		super("Error a Nivel de SQL en "+message);
		// TODO Auto-generated constructor stub
	}

}
