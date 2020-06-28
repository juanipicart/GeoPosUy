package com.clases;

import java.io.Serializable;
import java.util.Date;

public class Observacion implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id_observacion; 	
	private String descripcion; //max 100 not null	
	private String geolocalizacion; //max 150 not null	
	private Date fecha_hora; //not null	
	private long id_usuario; //not null	
	private int nivel_criticidad;  // nivel de criticidad de la observacion	
	private long id_localidad; // Clase Localidad	
	private String id_departamento;  // clase Deparamentos	
	private long id_zona; // Clase Zona	
	private int revisado;     // indoca si esta o no revisado	
	private String obsValidador;  // Notas del validador	
	private int activo; // Registro activo o borrado 
	private long id_fenomeno;
	

	public Observacion(long id_observacion, String descripcion, String geolocalizacion, Date fecha_hora,	
			long id_usuario, int nivel_criticidad, long id_localidad, String id_departamento, long id_zona,	
			int revisado, String obsValidador, int activo, long id_fenomeno) {	
		super();	
		this.id_observacion = id_observacion;	
		this.descripcion = descripcion;	
		this.geolocalizacion = geolocalizacion;	
		this.fecha_hora = fecha_hora;	
		this.id_usuario = id_usuario;	
		this.nivel_criticidad = nivel_criticidad;	
		this.id_localidad = id_localidad;	
		this.id_departamento = id_departamento;	
		this.id_zona = id_zona;	
		this.revisado = revisado;	
		this.obsValidador = obsValidador;	
		this.activo = activo;	
		this.id_fenomeno = id_fenomeno;
	}	
	public Observacion() {	
		super();	
		this.id_observacion = 0;	
		this.descripcion = "";	
		this.geolocalizacion = "";	
		this.fecha_hora = null ;	
		this.id_usuario = 0;	
		this.nivel_criticidad = 0;	
		this.id_localidad = 0;	
		this.id_departamento = "";	
		this.id_zona = 0;	
		this.revisado = 0;	
		this.obsValidador = "";	
		this.activo = 1;
		this.id_fenomeno = 0;
	}	
		
		
	public int getActivo() {	
		return activo;	
	}	
	public void setActivo(int activo) {	
		this.activo = activo;	
	}
	
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
	public int getNivel_criticidad() {	
		return nivel_criticidad;	
	}	
	public void setNivel_criticidad(int nivel_criticidad) {	
		this.nivel_criticidad = nivel_criticidad;	
	}	
	public int getRevisado() {	
		return revisado;	
	}	
	public void setRevisado(int revisado) {	
		this.revisado = revisado;	
	}	
	public String getObsValidador() {	
		return obsValidador;	
	}	
	public void setObsValidador(String obsValidador) {	
		this.obsValidador = obsValidador;	
	}	
	public long getId_usuario() {	
		return id_usuario;
	}
	
	public void setId_usuario(long id_usuario) {	
		this.id_usuario = id_usuario;	
	}	
	public long getId_localidad() {	
		return id_localidad;	
	}	
	public void setId_localidad(long id_localidad) {	
		this.id_localidad = id_localidad;	
	}	
	public String getId_departamento() {	
		return id_departamento;	
	}	
	public void setId_departamento(String id_departamento) {	
		this.id_departamento = id_departamento;	
	}	
	public long getId_zona() {	
		return id_zona;	
	}	
	public void setId_zona(long id_zona) {	
		this.id_zona = id_zona;	
	}
	public long getId_fenomeno() {
		return id_fenomeno;
	}
	public void setId_fenomeno(long id_fenomeno) {
		this.id_fenomeno = id_fenomeno;
	}
	
}
