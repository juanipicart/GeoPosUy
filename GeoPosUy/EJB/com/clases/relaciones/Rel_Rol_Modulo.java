package com.clases.relaciones;

import com.clases.codigueras.CodModulo;
import com.clases.codigueras.Rol;

public class Rel_Rol_Modulo {

	private Rol rol; 
	private CodModulo modulo; //not null
	char permiso;


	public Rol getId_rol() {
		return rol;
	}
	public void setId_rol(Rol id_rol) {
		this.rol = id_rol;
	}
	public CodModulo getId_modulo() {
		return modulo;
	}
	public void setId_modulo(CodModulo id_modulo) {
		this.modulo = id_modulo;
	}
	public char getPermiso() {
		return permiso;
	}
	public void setPermiso(char permiso) {
		this.permiso = permiso;
	}
	
	

	public Rel_Rol_Modulo(Rol id_rol, CodModulo id_modulo, char permiso) {
		super();
		this.rol = id_rol;
		this.modulo = id_modulo;
		this.permiso = permiso;
	}
	
	public Rel_Rol_Modulo(Rol id_rol, CodModulo id_modulo) {
		super();
		this.rol = id_rol;
		this.modulo = id_modulo;
	}
	
}
