package com.beans;

import java.util.List;

import javax.ejb.Remote;

import com.clases.UbicacionesObj;
import com.clases.codigueras.CodDepartamento;
import com.clases.codigueras.CodLocalidad;
import com.clases.codigueras.CodZona;

@Remote
public interface UbicacionesBeanRemote {
	public List<CodDepartamento> retornarDepartamentos();
	public List<CodLocalidad> retornarLocalidades();
	public List<CodZona> retornarZonas();
	public List<CodDepartamento> retornarDepartamentosSimilar(CodDepartamento clDto);
	//public List<CodLocalidad> retornarLocalidadesSimilar(CodLocalidad clLoc);
	public List<CodZona> retornarZonasSimilar(CodZona clZna);

	// devuelve un objeto tipo ubicacion con el ID pasado
	/*public UbicacionesObj devolverObjUbicacion( long idUbicacion);*/
	boolean holaMundo();
	public List<CodLocalidad> retornarLocalidadesPorDepto(long depto);
}
