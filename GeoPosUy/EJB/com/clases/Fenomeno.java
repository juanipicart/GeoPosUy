package com.clases;

import java.io.Serializable;

public class Fenomeno implements Serializable  {


	private static final long serialVersionUID = 1L;
	
	long id_fenomeno;
	String nombre; //max 20 not null
	String descripcion; //max 50 not null
	String contacto_emergencia; //max 20
	String codigo; //max 5
	

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public long getId_fenomeno() {
		return id_fenomeno;
	}
	public void setId_fenomeno(long id_fenomeno) {
		this.id_fenomeno = id_fenomeno;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getContacto_emergencia() {
		return contacto_emergencia;
	}
	public void setContacto_emergencia(String contacto_emergencia) {
		this.contacto_emergencia = contacto_emergencia;
	}
	
	
	public Fenomeno(long id_fenomeno, String nombre, String descripcion, String contacto_emergencia) {
		super();
		this.id_fenomeno = id_fenomeno;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.contacto_emergencia = contacto_emergencia;
	}
	
	public Fenomeno(String codigo, String nombre, String descripcion, String contacto_emergencia) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.contacto_emergencia = contacto_emergencia;
		this.codigo = codigo;
	}
	public Fenomeno(long id_fenomeno, String nombre, String descripcion) {
		super();
		this.id_fenomeno = id_fenomeno;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Fenomeno() {
		// TODO Auto-generated constructor stub
	}
	public String toString() { return this.descripcion + " - " + Long.toString(this.id_fenomeno);}
}
