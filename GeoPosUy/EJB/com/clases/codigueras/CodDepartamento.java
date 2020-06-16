package com.clases.codigueras;

import java.io.Serializable;

public class CodDepartamento implements Serializable{
	
	// Codiguera de codigos de Departamentos
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idCodDepartamento;
	private String descCodDepartamento;

	public CodDepartamento() {
	}
	
	public CodDepartamento(int idCodDepartamento, String descCodDepartamento) {
		this.idCodDepartamento = idCodDepartamento;
		this.descCodDepartamento = descCodDepartamento;
	}

	public long getIdCodDepartamento() {
		return idCodDepartamento;
	}

	public void setIdCodDepartamento(int idCodDepartamento) {
		this.idCodDepartamento = idCodDepartamento;
	}

	public String getDescCodDepartamento() {
		return descCodDepartamento;
	}

	public void setDescCodDepartamento(String descCodDepartamento) {
		this.descCodDepartamento = descCodDepartamento;
	}
	
}
