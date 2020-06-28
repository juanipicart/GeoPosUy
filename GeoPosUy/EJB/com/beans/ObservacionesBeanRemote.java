package com.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;
import javax.swing.DefaultListModel;

import com.clases.Observacion;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.NoValidaParamException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface ObservacionesBeanRemote {


	public int ingresarObservacion ( Observacion insObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException;	
	public int borrarObservacion (Observacion delObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException;	
	public int modificarObservacion(Observacion modifObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException;		
		
	public List<Observacion> buscarObservacionPorUsuario(long idUsuario) throws NoValidaParamException, ProblemasNivelSQLException;	
	public List<Observacion> buscarObservacion() throws ProblemasNivelSQLException;	
	public Observacion buscarObservacionPorID(long idObservacion) throws NoValidaParamException, ProblemasNivelSQLException;

	public boolean existeObservacionPorFenomeno(String fenom) throws ProblemasNivelSQLException, NoSeRealizoOperacionException, SQLException, Exception;
	
	public List<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo) throws ProblemasNivelSQLException, NoSeRealizoOperacionException, SQLException, Exception;

}
