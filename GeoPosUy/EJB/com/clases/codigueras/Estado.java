package com.clases.codigueras;

import java.io.Serializable;

public class Estado implements Serializable{


	private static final long serialVersionUID = 1L;
	
	long id_estado;
	String desc_estado; //max 30 not null
	
	public long getId_estado() {
		return id_estado;
	}
	public void setId_estado(long id_estado) {
		this.id_estado = id_estado;
	}
	public String getDesc_estado() {
		return desc_estado;
	}
	public void setDesc_estado(String desc_estado) {
		this.desc_estado = desc_estado;
	}
	
	public Estado(long id_estado, String desc_estado) {
		super();
		this.id_estado = id_estado;
		this.desc_estado = desc_estado;
	}
	
	public Estado(String desc_estado) {
		super();
		this.desc_estado = desc_estado;
	}
	public Estado() {
		// TODO Auto-generated constructor stub
	}
	
	
}
