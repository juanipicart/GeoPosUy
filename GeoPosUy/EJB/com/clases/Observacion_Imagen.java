package com.clases;

import java.sql.Blob;

public class Observacion_Imagen {

	long id_observacion;
	String archivo; //max 250 not null
	Blob imagen; //not null

	
	
	public long getId_observacion() {
		return id_observacion;
	}
	public void setId_observacion(long id_observacion) {
		this.id_observacion = id_observacion;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public Blob getImagen() {
		return imagen;
	}
	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}
	
	
	public Observacion_Imagen(long id_observacion, String archivo, Blob imagen) {
		super();
		this.id_observacion = id_observacion;
		this.archivo = archivo;
		this.imagen = imagen;
	}
}
