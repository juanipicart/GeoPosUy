package com.dao.ubicaciones;

import java.util.List;

import com.clases.codigueras.CodDepartamento;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public interface CodDepartamentosDao {

	public void actualizarCodDepartamento (String idDepartamentoOld, String descDepartamentoNueva) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void ingresarCodDepartamento (String idDepartamento, String descDepartamento) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void eliminarCodDepartamento (String idDepartamento) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public List<CodDepartamento> obtenerCodDepartamento(String descDepartamento);
	public List<CodDepartamento> obtenerCodDepartamentoId(String idDepartamento);
	public List<CodDepartamento> obtenerCodDepartamentoLike(String descDepartamento); 
	public List<CodDepartamento> obtenerCodDepartamento();
}

