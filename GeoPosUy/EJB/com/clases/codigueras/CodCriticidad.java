package com.clases.codigueras;

public class CodCriticidad {


	long id_nivel;
	String descripcion_corta; //max 20 not null
	String descripcion; //max 50 not null
	
	
	public long getId_nivel() {
		return id_nivel;
	}
	public void setId_nivel(long id_nivel) {
		this.id_nivel = id_nivel;
	}
	public String getDescripcion_corta() {
		return descripcion_corta;
	}
	public void setDescripcion_corta(String descripcion_corta) {
		this.descripcion_corta = descripcion_corta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public CodCriticidad(String descripcion_corta, String descripcion) {
		super();
		this.descripcion_corta = descripcion_corta;
		this.descripcion = descripcion;
	}
	
	public CodCriticidad(long id_nivel, String descripcion_corta, String descripcion) {
		super();
		this.id_nivel = id_nivel;
		this.descripcion_corta = descripcion_corta;
		this.descripcion = descripcion;
	}
}
