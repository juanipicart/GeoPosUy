package com.dao;

import javax.ejb.Remote;
import javax.swing.DefaultListModel;

import com.clases.Fenomeno;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface FenomenoDao {
	
	public void registrarFenomeno(Fenomeno fenomeno) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void actualizarFenomeno(Fenomeno fenomeno) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean eliminarFenomeno(String codigo) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public Fenomeno selectFenomenoPorNombre(String nombre) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public DefaultListModel<Fenomeno> consultarFenomenos() throws ProblemasNivelSQLException, NoSeRealizoOperacionException, Exception;
	public Fenomeno selectFenomenoPorCodigo(String codigo) throws Exception;
}
