package com.dao.ubicaciones;

import java.util.List;

import com.clases.codigueras.CodLocalidad;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public interface CodLocalidadesDao {

	//public void actualizarCodLocalidad (long idLocalidadOld, String descLocalidadNueva) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	//public void ingresarCodLocalidad (String descLocalidad) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	//public void eliminarCodLocalidad (long idLocalidad) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	//public List<CodLocalidad> obtenerCodLocalidad(String descLocalidad);
	//public List<CodLocalidad> obtenerCodLocalidadLike(String descLocalidad);
	//public List<CodLocalidad> obtenerCodLocalidad(long idLocalidad);
	public List<CodLocalidad> obtenerCodLocalidad();
	public List<CodLocalidad> obtenerLocalidadesPorDepto(long depto);
	
}
