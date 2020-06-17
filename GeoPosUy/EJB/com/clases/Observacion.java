package com.clases;

import java.util.Date;

public class Observacion {


	long id_observacion; 
	long id_usuario; 
	String geolocalizacion; //max 150 not null
	Date fecha_hora; //not null
	String descripcion; //not null
	long id_fenomeno; //not null
	
	public long getId_observacion() {
		return id_observacion;
	}
	public void setId_observacion(long id_observacion) {
		this.id_observacion = id_observacion;
	}

	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getId_fenomeno() {
		return id_fenomeno;
	}
	public void setId_fenomeno(long id_fenomeno) {
		this.id_fenomeno = id_fenomeno;
	}
	public String getGeolocalizacion() {
		return geolocalizacion;
	}
	public void setGeolocalizacion(String geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}
	public Date getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	
	public Observacion(long id_observacion, Long id_usuario, String descripcion, String geolocalizacion,
			Date fechaHora, Long id_fenomeno) {
		super();
		this.id_observacion = id_observacion;
		this.id_usuario = id_usuario;
		this.geolocalizacion = geolocalizacion;
		this.descripcion = descripcion;
		this.fecha_hora = fechaHora;
		this.id_fenomeno = id_fenomeno;
	}
}
