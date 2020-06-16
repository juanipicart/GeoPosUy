package com.dao.ubicaciones;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bd.DBConector;
import com.clases.codigueras.CodZona;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public class CodZonasDaoImpl implements CodZonasDao {

	/// Declaracion de SQL y DML 	
	private static String INS_ZONA = "Insert into ZONAS Values(NULL,UPPER(?))"; 
	private static String DEL_ZONA = "Delete from ZONAS where id_zona= ?";
	private static String UPD_ZONA = "Update ZONAS set descripcion = UPPER(?) where id_zona= ?";
	
	private static String TODOS = "Select * from ZONAS order by descripcion"; 
	private static String TODOS_POR_ID = "Select * from ZONAS Where id_zona=?"; 
	private static String TODOS_POR_DESCRIPCION = "Select * from ZONAS Where descripcion= UPPER ( ? )";
	private static String TODOS_POR_DESCRIPCION_LIKE = "Select * from ZONAS Where descripcion LIKE UPPER( '%' || ? || '%')";
	// Esta tambien funciona
	//private static String TODOS_POR_DESCRIPCION_LIKE = "Select * from ZONAS Where descripcion LIKE UPPER( CONCAT(CONCAT('%',?),'%'))";
	
	// traigo la instancia ya hecha o va nueva 
	DBConector bd = DBConector.getinstance();
	ArrayList<CodZona> lszona;
	
	@Override
	public void actualizarCodZona(long idZonaOld, String descZonaNueva) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		int i;
		bd.setPrepStmt(UPD_ZONA);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, descZonaNueva);
			bd.getPrepStmt().setLong(2, idZonaOld);
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("actualizar Zona");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("actualizar Zona");
		} else
			System.out.println("Se actualizaron ["+i+"] registros");
	}

	@Override
	public void ingresarCodZona(String descZona) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		int i;
		// se prepara el insert
		bd.setPrepStmt(INS_ZONA);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, descZona);
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		
		i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Insertar Zona");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Insertar Zona");
		} else
			System.out.println("Se ingresaron ["+i+"] registros");
		
	}

	@Override
	public void eliminarCodZona(long idZona) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {

		int i;
		// se prepara el delete
		bd.setPrepStmt(DEL_ZONA);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setLong(1, idZona);
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Borrar Zona");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Borrar Zona");
		} else
			System.out.println("Se borró ["+i+"] registros");
				
	}

	@Override
	public List<CodZona> obtenerCodZona(String descZona) {

		bd.setPrepStmt(TODOS_POR_DESCRIPCION);
		lszona = new ArrayList<>();
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, descZona);
			bd.execQry();
			
			while ( bd.getResultSet().next()) {
				CodZona glZona = new CodZona();
				glZona.setIdCodZona(bd.getResultSet().getLong(1));
				glZona.setDescCodZona(bd.getResultSet().getString(2));
				lszona.add(glZona);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Zona por Descripcion -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lszona;
	}

	@Override
	public List<CodZona> obtenerCodZona(long idZona) {

		bd.setPrepStmt(TODOS_POR_ID);
		lszona = new ArrayList<>();
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setLong(1, idZona);
			bd.execQry();
			
			while ( bd.getResultSet().next()) {
				CodZona glZona = new CodZona();
				glZona.setIdCodZona(bd.getResultSet().getLong(1));
				glZona.setDescCodZona(bd.getResultSet().getString(2));
				lszona.add(glZona);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Zona por ID -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lszona;
	}

	@Override
	public List<CodZona> obtenerCodZonaLike(String descZona) {
		System.out.println("llegue");
		bd.setPrepStmt(TODOS_POR_DESCRIPCION_LIKE);
		lszona = new ArrayList<CodZona>();
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, descZona);
			//System.out.println("SQL ->" +bd.getCnn().nativeSQL(TODOS_POR_DESCRIPCION_LIKE));
			bd.execQry();
			
			while ( bd.getResultSet().next()) {
				CodZona glZona = new CodZona();
				glZona.setIdCodZona(bd.getResultSet().getLong(1));
				glZona.setDescCodZona(bd.getResultSet().getString(2));
				lszona.add(glZona);
				
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Zona por Descripcion -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lszona;
	}

	@Override
	public List<CodZona> obtenerCodZona() {
		lszona = new ArrayList<>();
		try {
			bd.execStmt(TODOS);
			while ( bd.getResultSet().next()) {
				CodZona glZona = new CodZona();
				glZona.setIdCodZona(bd.getResultSet().getLong(1));
				glZona.setDescCodZona(bd.getResultSet().getString(2));
				lszona.add(glZona);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Zona por Descripcion -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lszona;
	}

}
