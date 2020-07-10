package com.clases.codigueras;

import java.io.Serializable;

public class Localidad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long idCodLocalidad;
	private String descCodLocalidad;

	public Localidad() {

		this.idCodLocalidad = 0;
		this.descCodLocalidad = null;
	}

	public Localidad(long idCodLocalidad, String descCodLocalidad) {

		this.idCodLocalidad = idCodLocalidad;
		this.descCodLocalidad = descCodLocalidad;
	}

	public long getIdCodLocalidad() {
		return idCodLocalidad;
	}

	public void setIdCodLocalidad(long idCodLocalidad) {
		this.idCodLocalidad = idCodLocalidad;
	}

	public String getDescCodLocalidad() {
		return descCodLocalidad;
	}

	public void setDescCodLocalidad(String descCodLocalidad) {
		this.descCodLocalidad = descCodLocalidad;
	}
	
	
	
}
