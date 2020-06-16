package com.clases.relaciones;

import java.util.Date;

import com.clases.codigueras.Estado;

public class Rel_Usuario_Estado {	

	long id_usuario;
	private Estado estado; // not null
	Date fecha_desde; 
	
	

	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public Estado getId_estado() {
		return estado;
	}
	public void setId_estado(Estado id_estado) {
		this.estado = id_estado;
	}
	public Date getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(Date fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	
	public Rel_Usuario_Estado(Estado id_estado, Date fecha_desde, long id_usuario) {
		super();
		this.estado = id_estado;
		this.fecha_desde = fecha_desde;
		this.id_usuario = id_usuario;
	}
	
	public Rel_Usuario_Estado(Estado id_estado, long id_usuario) {
		super();
		this.estado = id_estado;
		this.id_usuario = id_usuario;
	}
}
