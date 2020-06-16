package com.dao.ubicaciones;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bd.DBConector;
import com.clases.codigueras.CodDepartamento;
import com.clases.codigueras.CodLocalidad;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public class CodLocalidadesDaoImpl implements CodLocalidadesDao {

	/// Declaracion de SQL y DML 	
	/*private static String INS_LOC = "Insert into localidades Values(NULL,UPPER(?))"; 
	private static String DEL_LOC = "Delete from localidades where id_localidad= ?";
	private static String UPD_LOC = "Update localidades set localidad = UPPER(?) where id_localidad= ?";*/
	
	private static String TODOS = "Select * from localidades order by localidad";
	private static String TODOS_POR_DEPTO = "Select * from localidades WHERE id_depto =? order by localidad asc";
	private static String TODOS_POR_ID = "Select * from localidades Where id_localidad=?"; 
	//private static String TODOS_POR_LOCALIDAD = "Select * from localidades Where localidad= UPPER ( ? )";
	//private static String TODOS_POR_LOCALIDAD_LIKE = "Select * from localidades Where localidad LIKE UPPER( '%' || ? || '%')";
	// traigo la instancia ya hecha o va nueva 
	DBConector bd = DBConector.getinstance();
	ArrayList<CodLocalidad> lsloc;
	CodDepartamentosDaoImpl cod = new CodDepartamentosDaoImpl();
	
	
	@Override
	public List<CodLocalidad> obtenerLocalidadesPorDepto(long depto) {
		
		bd.setPrepStmt(TODOS_POR_DEPTO);
		lsloc = new ArrayList<>();
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setLong(1, depto);
			bd.execQry();
			
			while ( bd.getResultSet().next()) {
				CodLocalidad loc = new CodLocalidad();
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
	public List<CodLocalidad> obtenerCodLocalidad() {
		
		lsloc = new ArrayList<>();
		try {
			bd.execStmt(TODOS);
			
			while ( bd.getResultSet().next()) {
				CodLocalidad loc = new CodLocalidad();
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
