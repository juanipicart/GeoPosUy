package com.clases.relaciones;

import com.clases.Caracteristica;

public class Rel_Fenom_Caract {

	private long id_fenomeno;
	private Caracteristica caracteristica; //not null ambos
	

	public long getId_fenomeno() {
		return id_fenomeno;
	}
	public void setId_fenomeno(long id_fenomeno) {
		this.id_fenomeno = id_fenomeno;
	}
	public Caracteristica getId_caracteristica() {
		return caracteristica;
	}
	public void setId_caracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	
	public Rel_Fenom_Caract(long id_fenomeno, Caracteristica caracteristica) {
		super();
		this.id_fenomeno = id_fenomeno;
		this.caracteristica = caracteristica;
	}
	
}
