package com.dao.ubicaciones;

import java.util.List;

import com.clases.codigueras.Localidad;

public interface CodLocalidadesDao {

	public List<Localidad> obtenerCodLocalidad();
	public List<Localidad> obtenerLocalidadesPorDepto(long depto);
	
}
