package com.clases.codigueras;

import java.io.Serializable;

public class Zona implements Serializable{

	private static final long serialVersionUID = 1L;
	private long idCodZona;
	private String descCodZona;

	public Zona(long idCodZona, String descCodZona) {
		this.idCodZona = idCodZona;
		this.descCodZona = descCodZona;
	}

	public Zona() {
		this.idCodZona = 0;
		this.descCodZona = null;
	}

	public long getIdCodZona() {
		return idCodZona;
	}

	public void setIdCodZona(long idCodZona) {
		this.idCodZona = idCodZona;
	}

	public String getDescCodZona() {
		return descCodZona;
	}

	public void setDescCodZona(String descCodZona) {
		this.descCodZona = descCodZona;
	}
	
}
