package com.clases.relaciones;

import com.clases.codigueras.CodTipo_Contacto;

public class Rel_Usuario_Contacto {

	private long id_usuario;
	private CodTipo_Contacto tipo; //not null
	private String contacto; //max 30 not null
	
	
	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public CodTipo_Contacto getId_tipo() {
		return tipo;
	}
	public void setId_tipo(CodTipo_Contacto id_tipo) {
		this.tipo = id_tipo;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	
	public Rel_Usuario_Contacto(long id_usuario, CodTipo_Contacto id_tipo, String contacto) {
		super();
		this.id_usuario = id_usuario;
		this.tipo = id_tipo;
		this.contacto = contacto;
	}	
}
