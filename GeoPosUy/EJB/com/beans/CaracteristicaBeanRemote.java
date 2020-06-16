package com.beans;

import javax.ejb.Remote;

@Remote
public interface CaracteristicaBeanRemote {

	int registrarCaracteristica(String etiqueta, String nombre, String tipo, String descripcion);

}
