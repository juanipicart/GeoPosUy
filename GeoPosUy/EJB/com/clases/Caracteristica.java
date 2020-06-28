package com.clases;

public class Caracteristica {
	
	private long id_caracteristica;
	private String descripcion; //max 20 not null
	private String unid_medida; //max 20 not null
	
	public Caracteristica(long id_caracteristica, String descripcion, String unid_medida) {
		super();
		this.id_caracteristica = id_caracteristica;
		this.descripcion = descripcion;
		this.unid_medida = unid_medida;
	}

	public Caracteristica() {
		super();
		this.id_caracteristica = 0;
		this.descripcion = "";
		this.unid_medida = "";
	}

	public long getId_caracteristica() {
		return id_caracteristica;
	}

	public void setId_caracteristica(long id_caracteristica) {
		this.id_caracteristica = id_caracteristica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUnid_medida() {
		return unid_medida;
	}

	public void setUnid_medida(String unid_medida) {
		this.unid_medida = unid_medida;
	}
	

	
	
}