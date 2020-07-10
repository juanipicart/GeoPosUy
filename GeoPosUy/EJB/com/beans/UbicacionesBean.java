package com.beans;

import java.util.List;

import javax.ejb.Stateful;
import com.clases.codigueras.Departamento;
import com.clases.codigueras.Localidad;
import com.clases.codigueras.Zona;
import com.dao.ubicaciones.CodDepartamentosDao;
import com.dao.ubicaciones.CodDepartamentosDaoImpl;
import com.dao.ubicaciones.CodLocalidadesDao;
import com.dao.ubicaciones.CodLocalidadesDaoImpl;
import com.dao.ubicaciones.CodZonasDao;
import com.dao.ubicaciones.CodZonasDaoImpl;

/**
 * Session Bean implementation class UbicacionesBean
 */
@Stateful
public class UbicacionesBean implements UbicacionesBeanRemote {


	private CodZonasDao daoZna;
	private CodDepartamentosDao daoDto;
	private CodLocalidadesDao daoLoc;
	
	@Override
	public List<Departamento> retornarDepartamentos() {
		this.daoDto = new CodDepartamentosDaoImpl();
		return this.daoDto.obtenerCodDepartamento();
	}

	@Override
	public List<Localidad> retornarLocalidades() {
		this.daoLoc = new CodLocalidadesDaoImpl();
		return this.daoLoc.obtenerCodLocalidad();
	}
	
	@Override
	public List<Localidad> retornarLocalidadesPorDepto(long depto) {
		this.daoLoc = new CodLocalidadesDaoImpl();
		return this.daoLoc.obtenerLocalidadesPorDepto(depto);
	}

	@Override
	public List<Zona> retornarZonas() {
		this.daoZna = new CodZonasDaoImpl();
		return this.daoZna.obtenerCodZona();
	}

	public UbicacionesBean() {
        // TODO Auto-generated constructor stub
    }

}
