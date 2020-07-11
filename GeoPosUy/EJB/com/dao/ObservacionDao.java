package com.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;
import com.clases.Observacion;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface ObservacionDao {

	public void registrarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void modificarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean buscarObservacionesPorFenomeno(String codigo) throws Exception;	
	public List<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo, Date fechaDesde, java.util.Date fechaHasta) throws Exception;
	public List<Observacion> buscarTodas() throws ProblemasNivelSQLException;
	public List<String> obtenerPalabrasProhibidas() throws SQLException, ProblemasNivelSQLException;
	long obtenerNextVal() throws SQLException; 
}
