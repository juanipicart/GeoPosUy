package com.clases.codigueras;

public class CodObservacion_Estado {
	

	long id_estado;
	String descripcion; //max 30 not null
	
	
	public long getId_estado() {
		return id_estado;
	}
	public void setId_estado(long id_estado) {
		this.id_estado = id_estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public CodObservacion_Estado(long id_estado, String descripcion) {
		super();
		this.id_estado = id_estado;
		this.descripcion = descripcion;
	}

}
