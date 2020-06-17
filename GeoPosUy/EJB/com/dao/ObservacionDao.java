package com.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.swing.DefaultListModel;

import com.clases.Observacion;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface ObservacionDao {

	//public void registrarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void eliminarObservacion(long id_observacion) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	//public void modificarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean buscarObservacionesPorFenomeno(String codigo) throws Exception;	
	public DefaultListModel<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo) throws Exception;
}
