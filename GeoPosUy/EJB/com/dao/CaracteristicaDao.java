package com.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import com.clases.Caracteristica;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public interface CaracteristicaDao {
	
	public void registrarCaracteristica(Caracteristica caract) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void eliminarCaracteristica(String nombre) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void modificarCaracteristica(Caracteristica caract) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public LinkedList<Caracteristica> selectAll() throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public Caracteristica selectCaractByID(long id) throws ProblemasNivelSQLException, NoSeRealizoOperacionException, SQLException;
}
