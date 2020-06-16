package com.clases.codigueras;

import java.io.Serializable;

public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id_rol;
	private String descripcion_rol; //max 20 not null

	

	public long getId_rol() {
		return id_rol;
	}

	public void setId_rol(long id_rol) {
		this.id_rol = id_rol;
	}

	public String getDescripcion_rol() {
		return descripcion_rol;
	}

	public void setDescripcion_rol(String descripcion_rol) {
		this.descripcion_rol = descripcion_rol;
	}

	
	
	
	public Rol(long id_rol, String descripcion_rol) {
		super();
		this.id_rol = id_rol;
		this.descripcion_rol = descripcion_rol;
		
	}
	
	public Rol(String descripcion_rol) {
		super();
		this.descripcion_rol = descripcion_rol;
		
	}

	public Rol() {
		// TODO Auto-generated constructor stub
	}

	
}
