package com.dao;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;
import javax.swing.DefaultListModel;

import com.clases.Observacion;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface ObservacionDao {

	public void registrarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void eliminarObservacion(long id_observacion) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void modificarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean buscarObservacionesPorFenomeno(String codigo) throws Exception;	
	public List<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo) throws Exception;
	public List<Observacion> buscarPorUsuario(long idUsuario) throws ProblemasNivelSQLException;	
	public Observacion buscarPorId(long idObservacion) throws ProblemasNivelSQLException;	
	//public List<Observacion> buscarPorFenomeno(long idFenomoeno) throws ProblemasNivelSQLException; // TODO	
	//public List<Observacion> buscarPorFecha(Date fecha); //TODO 	
	public List<Observacion> buscarTodas() throws ProblemasNivelSQLException;
}
