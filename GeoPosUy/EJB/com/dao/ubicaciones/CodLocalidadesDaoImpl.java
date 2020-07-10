package com.dao.ubicaciones;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bd.DBConector;
import com.clases.codigueras.Localidad;

public class CodLocalidadesDaoImpl implements CodLocalidadesDao {

	private static String TODOS = "Select * from localidades order by localidad";
	private static String TODOS_POR_DEPTO = "Select * from localidades WHERE id_depto =? order by localidad asc";
	
	
	DBConector bd = DBConector.getinstance();
	ArrayList<Localidad> lsloc;
	CodDepartamentosDaoImpl cod = new CodDepartamentosDaoImpl();
	
	
	@Override
	public List<Localidad> obtenerLocalidadesPorDepto(long depto) {
		
		bd.setPrepStmt(TODOS_POR_DEPTO);
		lsloc = new ArrayList<>();
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setLong(1, depto);
			bd.execQry();
			
			while ( bd.getResultSet().next()) {
				Localidad loc = new Localidad();
				loc.setIdCodLocalidad(bd.getResultSet().getLong(1));
				loc.setDescCodLocalidad(bd.getResultSet().getString(2));
				lsloc.add(loc);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Localidad por Descripcion -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lsloc;
	}

	public CodLocalidadesDaoImpl() {

	}

	@Override
	public List<Localidad> obtenerCodLocalidad() {
		
		lsloc = new ArrayList<>();
		try {
			bd.execStmt(TODOS);
			
			while ( bd.getResultSet().next()) {
				Localidad loc = new Localidad();
				loc.setIdCodLocalidad(bd.getResultSet().getLong(1));
				loc.setDescCodLocalidad(bd.getResultSet().getString(2));
				lsloc.add(loc);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Localidad por Descripcion -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lsloc;
	}



}
