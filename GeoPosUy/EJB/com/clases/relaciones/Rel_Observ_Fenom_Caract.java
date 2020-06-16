package com.clases.relaciones;

import com.clases.Caracteristica;
import com.clases.Fenomeno;

public class Rel_Observ_Fenom_Caract {	

	long id_observacion;
	private Fenomeno fenomeno;
	private Caracteristica caracteristica;
	private String valor; //max 10 todas not null
	
	public long getId_observacion() {
		return id_observacion;
	}
	public void setId_observacion(long id_observacion) {
		this.id_observacion = id_observacion;
	}
	public Fenomeno getIf_denomeno() {
		return fenomeno;
	}
	public void setIf_denomeno(Fenomeno if_denomeno) {
		this.fenomeno = if_denomeno;
	}
	public Caracteristica getId_caracteristica() {
		return caracteristica;
	}
	public void setId_caracteristica(Caracteristica id_caracteristica) {
		this.caracteristica = id_caracteristica;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public Rel_Observ_Fenom_Caract(long id_observacion, Fenomeno fenomeno, Caracteristica caracteristica, String valor) {
		super();
		this.id_observacion = id_observacion;
		this.fenomeno = fenomeno;
		this.caracteristica = caracteristica;
		this.valor = valor;
	}

}
