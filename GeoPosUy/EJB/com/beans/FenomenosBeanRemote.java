package com.beans;

import javax.ejb.Remote;

import com.clases.Fenomeno;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface FenomenosBeanRemote {

	public boolean existeFenomeno(String nombre) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean eliminarFenomeno(String codigo);
	public boolean crearFenomeno(String codigo, String nombre, String descripcion, String contacto);
    public Fenomeno buscarFenomenoPorCodigo(String codigo);
	public boolean modificarFenomeno(String codigo, String nombre, String desc, String contacto) throws NoSeRealizoOperacionException, ProblemasNivelSQLException;
	

}
