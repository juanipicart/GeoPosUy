package com.clases.relaciones;

import java.io.Serializable;

public class RelUbicacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idUbicacion;
	private long idLoc;
	private String idDepto;
	private long idZona;

	public RelUbicacion() {
		this.idUbicacion = 0;
		this.idLoc = 0;
		this.idDepto = null;
		this.idZona = 0;
	}
	
	public RelUbicacion(long idUbicacion, long idLoc, String idDepto, long idZona) {
		this.idUbicacion = idUbicacion;
		this.idLoc = idLoc;
		this.idDepto = idDepto;
		this.idZona = idZona;
	}

	public long getIdUbicacion() {
		return idUbicacion;
	}
	public void setIdUbicacion(long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	public long getIdLoc() {
		return idLoc;
	}
	public void setIdLoc(long idLoc) {
		this.idLoc = idLoc;
	}
	public String getIdDepto() {
		return idDepto;
	}
	public void setIdDepto(String idDepto) {
		this.idDepto = idDepto;
	}
	public long getIdZona() {
		return idZona;
	}
	public void setIdZona(long idZona) {
		this.idZona = idZona;
	}
	
	
	
}
