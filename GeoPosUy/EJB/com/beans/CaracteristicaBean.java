package com.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.clases.Caracteristica;
import com.dao.CaracteristicaDao;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.NoValidaParamException;
import com.exceptions.ProblemasNivelSQLException;

@Stateless
@LocalBean
public class CaracteristicaBean implements CaracteristicaBeanRemote {

	CaracteristicaDao dao;
	
    public CaracteristicaBean() {
    }

    @Override	
	public int registrarCaracteristica(String descripcion, String unidad_medida) throws NoValidaParamException {	
			
    	Caracteristica caract = new Caracteristica();	
			
		if(descripcion.isEmpty() || descripcion.length() > 30) {				
			throw new NoValidaParamException("Descripcion") ;
		}	
		if(unidad_medida.length() > 20) {				
			throw new NoValidaParamException("unidad medida") ;
		}	
		
		try {	
			dao.registrarCaracteristica(caract);	
		} catch (ProblemasNivelSQLException e) {	
			e.printStackTrace();	
		} catch (NoSeRealizoOperacionException e) {	
			e.printStackTrace();	
		}	
		return 0;	
	}
}
