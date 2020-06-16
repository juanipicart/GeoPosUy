package com.clases.codigueras;

public class CodModulo {


	long id_modulo;
	String descripcion; //max 30 not null
	
	
	public long getId_modulo() {
		return id_modulo;
	}
	public void setId_modulo(long id_modulo) {
		this.id_modulo = id_modulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public CodModulo(long id_modulo, String descripcion) {
		super();
		this.id_modulo = id_modulo;
		this.descripcion = descripcion;
	}
}
