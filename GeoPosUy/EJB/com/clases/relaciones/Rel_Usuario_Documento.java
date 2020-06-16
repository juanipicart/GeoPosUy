package com.clases.relaciones;

import java.util.Date;

public class Rel_Usuario_Documento {

	long id_usuario;
	long id_tipo_doc; //not null
	String documento; //max 15 not null
	Date fecha_desde; //not null
	Date fecha_hasta; 
	
	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public long getId_tipo_doc() {
		return id_tipo_doc;
	}
	public void setId_tipo_doc(long id_tipo_doc) {
		this.id_tipo_doc = id_tipo_doc;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Date getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(Date fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public Date getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(Date fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
	
	public Rel_Usuario_Documento(long id_usuario, long id_tipo_doc, String documento, Date fecha_desde, Date fecha_hasta) {
		super();
		this.id_usuario = id_usuario;
		this.id_tipo_doc = id_tipo_doc;
		this.documento = documento;
		this.fecha_desde = fecha_desde;
		this.fecha_hasta = fecha_hasta;
	}
	
	public Rel_Usuario_Documento(long id_usuario, long id_tipo_doc, String documento, Date fecha_desde) {
		super();
		this.id_usuario = id_usuario;
		this.id_tipo_doc = id_tipo_doc;
		this.documento = documento;
		this.fecha_desde = fecha_desde;
	}
	
}
