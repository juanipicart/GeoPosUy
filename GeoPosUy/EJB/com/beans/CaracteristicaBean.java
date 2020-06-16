package com.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.clases.Caracteristica;
import com.dao.CaracteristicaDao;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Stateless
@LocalBean
public class CaracteristicaBean implements CaracteristicaBeanRemote {

	CaracteristicaDao dao;
	
    public CaracteristicaBean() {
    }

    @Override
	public int registrarCaracteristica(String etiqueta, String nombre, String tipo, String descripcion) {
		
    	Caracteristica caract = new Caracteristica(etiqueta, nombre, tipo, descripcion);
		int numeroError = 0;
		
		if(etiqueta == null || etiqueta == "" || etiqueta.length() > 20) {			
			//Captar del otro lado este numero y mostrar el mensaje error correspondiente, lo mismo con las otras verificaciones
			numeroError = 1;
			return numeroError;
		}
		if(nombre == null || nombre == "" || nombre.length() > 20) {			
			numeroError = 2;
			return numeroError;
		}
		if(tipo == null || tipo == "" || tipo.length() > 30) {			
			numeroError = 3;
			return numeroError;
		}
		if(descripcion.length() > 50) {			
			numeroError = 4;
			return numeroError;
		}else {
			caract = new Caracteristica(etiqueta, nombre, tipo, descripcion);		
		}
		
		try {
			dao.registrarCaracteristica(caract);
		} catch (ProblemasNivelSQLException e) {
			e.printStackTrace();
		} catch (NoSeRealizoOperacionException e) {
			e.printStackTrace();
		}


		return numeroError;
	}
}
