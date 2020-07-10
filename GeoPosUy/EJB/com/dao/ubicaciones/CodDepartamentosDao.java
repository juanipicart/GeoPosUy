package com.dao.ubicaciones;

import java.util.List;

import com.clases.codigueras.Departamento;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public interface CodDepartamentosDao {

	public List<Departamento> obtenerCodDepartamento();
}

