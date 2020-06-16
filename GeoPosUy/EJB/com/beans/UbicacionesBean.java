package com.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import com.clases.UbicacionesObj;
import com.clases.codigueras.CodDepartamento;
import com.clases.codigueras.CodLocalidad;
import com.clases.codigueras.CodZona;
import com.clases.relaciones.RelUbicacion;
import com.dao.ubicaciones.CodDepartamentosDao;
import com.dao.ubicaciones.CodDepartamentosDaoImpl;
import com.dao.ubicaciones.CodLocalidadesDao;
import com.dao.ubicaciones.CodLocalidadesDaoImpl;
import com.dao.ubicaciones.CodZonasDao;
import com.dao.ubicaciones.CodZonasDaoImpl;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

/**
 * Session Bean implementation class UbicacionesBean
 */
@Stateful
public class UbicacionesBean implements UbicacionesBeanRemote {


	private CodZonasDao daoZna;
	private CodDepartamentosDao daoDto;
	private CodLocalidadesDao daoLoc;
	
	@Override
	public List<CodDepartamento> retornarDepartamentos() {
		this.daoDto = new CodDepartamentosDaoImpl();
		return this.daoDto.obtenerCodDepartamento();
	}

	@Override
	public List<CodLocalidad> retornarLocalidades() {
		this.daoLoc = new CodLocalidadesDaoImpl();
		return this.daoLoc.obtenerCodLocalidad();
	}
	
	@Override
	public List<CodLocalidad> retornarLocalidadesPorDepto(long depto) {
		this.daoLoc = new CodLocalidadesDaoImpl();
		return this.daoLoc.obtenerLocalidadesPorDepto(depto);
	}

	@Override
	public List<CodZona> retornarZonas() {
		this.daoZna = new CodZonasDaoImpl();
		return this.daoZna.obtenerCodZona();
	}

	@Override
	public List<CodDepartamento> retornarDepartamentosSimilar(CodDepartamento clDto) {
		this.daoDto = new CodDepartamentosDaoImpl();
		return this.daoDto.obtenerCodDepartamentoLike(clDto.getDescCodDepartamento());
		
	}

	/*@Override
	public List<CodLocalidad> retornarLocalidadesSimilar(CodLocalidad clLoc) {
		this.daoLoc = new CodLocalidadesDaoImpl();
		return this.daoLoc.obtenerCodLocalidadLike(clLoc.getDescCodLocalidad());
	}*/

	@Override
	public List<CodZona> retornarZonasSimilar(CodZona clZna) {
		this.daoZna = new CodZonasDaoImpl();
		return this.daoZna.obtenerCodZonaLike(clZna.getDescCodZona());
	}

	

	/*@Override
	public UbicacionesObj devolverObjUbicacion(long idUbicacion) {

		RelUbicacionesDao daoubica = new RelUbicacionesDaoImpl();
		RelUbicacion cRel = new RelUbicacion(idUbicacion, 0, null, 0);
		UbicacionesObj ubicacionesobj = null;
		
		int cnt =0;
		
		cRel.setIdUbicacion(idUbicacion);
		// traigo la cantidad que sea.
		cnt = daoubica.obtenerUbicacion(cRel).size();
		System.out.println("cantidad = " +cnt); 
		//
		if (cnt >= 0 ){
			
			cRel = daoubica.obtenerUbicacion(cRel).get(0);
			// cargo las clases para insertarlas en el objeto ubicaciones
			CodZona cz = new CodZona();
			this.daoZna = new CodZonasDaoImpl();
			cz = this.daoZna.obtenerCodZona(cRel.getIdZona()).get(0);
			
			CodDepartamento cd = new CodDepartamento();
			this.daoDto = new CodDepartamentosDaoImpl();
			cd = this.daoDto.obtenerCodDepartamentoId(cRel.getIdDepto()).get(0);
			
			CodLocalidad cl = new CodLocalidad();
			this.daoLoc = new CodLocalidadesDaoImpl();
			cl = this.daoLoc.obtenerCodLocalidad(cRel.getIdLoc()).get(0);
			
			// crear objeto a devolver
			ubicacionesobj = new UbicacionesObj(cz, cd, cl);

		} else {
			return null;
		}
		return ubicacionesobj;
	}*/

	public boolean holaMundo() {
		return true;
	}
	public UbicacionesBean() {
        // TODO Auto-generated constructor stub
    }

}
