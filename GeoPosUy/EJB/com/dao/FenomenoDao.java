package com.dao;

import java.util.LinkedList;

import javax.ejb.Remote;

import com.clases.Fenomeno;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface FenomenoDao {
	
	public void registrarFenomeno(Fenomeno fenomeno) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void actualizarFenomeno(Fenomeno fenomeno) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean eliminarFenomeno(String codigo) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public LinkedList<Fenomeno> selectTodosFenomenos() throws ProblemasNivelSQLException, NoSeRealizoOperacionException, Exception;	
	public Fenomeno selectFenomenoPorNombre(String nombre) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public Fenomeno selectFenomenoPorCodigo(String codigo) throws Exception;
}
