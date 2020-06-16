package com.clases.codigueras;

public class CodTipo_Contacto {

	long id_tipo;
	String descripcion; //max 20 not null
	
	
	public long getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(long id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public CodTipo_Contacto(long id_tipo, String descripcion) {
		super();
		this.id_tipo = id_tipo;
		this.descripcion = descripcion;
	}
}
