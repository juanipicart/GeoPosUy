package com.clases.codigueras;

import java.io.Serializable;

public class TipoDocumento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	long id_tipo_doc;
	String Descripcion;

	public TipoDocumento(long id_tipo_doc, String Descripcion) {
		super();
		this.id_tipo_doc = id_tipo_doc;
		this.Descripcion = Descripcion;
	}
	
	public TipoDocumento() {
		// TODO Auto-generated constructor stub
	}

	public long getID_TIPO_DOC() {
		return id_tipo_doc;
	}
	public void setID_TIPO_DOC(long id_tipo_doc) {
		this.id_tipo_doc = id_tipo_doc;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}
	
}
