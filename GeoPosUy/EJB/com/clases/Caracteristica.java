package com.clases;

public class Caracteristica {

	long id_caracteristica;
	String etiqueta; //max 20 not null
	String nombre; //max 20 not null
	String tipo; //max 20 not null
	String descripcion; //max 50
	
	
	public long getId_caracteristica() {
		return id_caracteristica;
	}
	public void setId_caracteristica(long id_caracteristica) {
		this.id_caracteristica = id_caracteristica;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public Caracteristica(long id_caracteristica, String etiqueta, String nombre, String tipo, String descripcion) {
		super();
		this.id_caracteristica = id_caracteristica;
		this.etiqueta = etiqueta;
		this.nombre = nombre;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	
	public Caracteristica(long id_caracteristica, String etiqueta, String nombre, String tipo) {
		super();
		this.id_caracteristica = id_caracteristica;
		this.etiqueta = etiqueta;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public Caracteristica(String etiqueta, String nombre, String tipo, String descripcion) {
		super();
		this.etiqueta = etiqueta;
		this.nombre = nombre;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	
	public Caracteristica(String etiqueta, String nombre, String tipo) {
		super();
		this.etiqueta = etiqueta;
		this.nombre = nombre;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
}
