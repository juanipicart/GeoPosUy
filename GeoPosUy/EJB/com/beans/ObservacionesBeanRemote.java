package com.beans;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;
import com.clases.Observacion;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.NoValidaParamException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface ObservacionesBeanRemote {


	public boolean ingresarObservacion ( Observacion insObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException;	
	public int modificarObservacion(Observacion modifObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException;		
	public List<Observacion> buscarObservacion() throws ProblemasNivelSQLException;	
	public boolean existeObservacionPorFenomeno(String fenom) throws ProblemasNivelSQLException, NoSeRealizoOperacionException, SQLException, Exception;
	
	public List<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo, Date fechaDesde, java.util.Date fechaHasta) throws ProblemasNivelSQLException, NoSeRealizoOperacionException, SQLException, Exception;
	public boolean validarLatLong(String latlong) throws NoValidaParamException;
	public List<String> contienePalabrasProhibidas(String texto) throws SQLException, ProblemasNivelSQLException;
	long obtenerNextVal() throws SQLException;
	int obtenerCriticidad(String nivel);
}
