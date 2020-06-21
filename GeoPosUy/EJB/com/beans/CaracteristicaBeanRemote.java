package com.beans;

import javax.ejb.Remote;

import com.exceptions.NoValidaParamException;

@Remote
public interface CaracteristicaBeanRemote {

	int registrarCaracteristica(String descripcion, String unidad_medida) throws NoValidaParamException;
}
