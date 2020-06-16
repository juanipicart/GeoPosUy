package com.clases;

import java.util.Date;

import com.clases.relaciones.RelUbicacion;

public class Observacion {


	long id_observacion; 
	String descripcion; //max 100 not null
	String geolocalizacion; //max 150 not null
	Date fecha_hora; //not null
	private RelUbicacion ubicacion; //not null
	private Usuario usuario; //not null
	
	public long getId_observacion() {
		return id_observacion;
	}
	public void setId_observacion(long id_observacion) {
		this.id_observacion = id_observacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public RelUbicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(RelUbicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public Observacion(long id_observacion, String descripcion, String geolocalizacion, Date fecha_hora,
			RelUbicacion ubicacion, Usuario usuario) {
		super();
		this.id_observacion = id_observacion;
		this.descripcion = descripcion;
		this.geolocalizacion = geolocalizacion;
		this.fecha_hora = fecha_hora;
		this.ubicacion = ubicacion;
		this.usuario = usuario;
	}
}
