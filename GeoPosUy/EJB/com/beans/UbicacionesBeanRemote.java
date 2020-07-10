package com.beans;

import java.util.List;

import javax.ejb.Remote;

import com.clases.codigueras.Departamento;
import com.clases.codigueras.Localidad;
import com.clases.codigueras.Zona;

@Remote
public interface UbicacionesBeanRemote {
	public List<Departamento> retornarDepartamentos();
	public List<Localidad> retornarLocalidades();
	public List<Zona> retornarZonas();
	public List<Localidad> retornarLocalidadesPorDepto(long depto);
}
