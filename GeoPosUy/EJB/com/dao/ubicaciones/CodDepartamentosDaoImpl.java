package com.dao.ubicaciones;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bd.DBConector;
import com.clases.codigueras.CodDepartamento;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public class CodDepartamentosDaoImpl implements CodDepartamentosDao {
	
	
	/// Declaracion de SQL y DML 	
	private static String INS_DTO = "Insert into DEPARTAMENTOS (ID_DEPARTAMENTO, DEPARTAMENTO) Values(UPPER(?),UPPER(?))"; 
	private static String DEL_DTO = "Delete from DEPARTAMENTOS where ID_DEPARTAMENTO= UPPER(?)";
	private static String UPD_DTO = "Update DEPARTAMENTOS set DEPARTAMENTO = UPPER(?) where ID_DEPARTAMENTO= ?";
	
	private static String TODOS = "Select * from DEPARTAMENTOS order by DEPARTAMENTO"; 
	private static String TODOS_POR_ID = "Select * from DEPARTAMENTOS Where ID_DEPARTAMENTO= UPPER (?)"; 
	private static String TODOS_POR_DEPARTAMENTO = "Select * from DEPARTAMENTOS Where DEPARTAMENTO= UPPER (?)";
	private static String TODOS_POR_DEPARTAMENTO_LIKE = "Select * from DEPARTAMENTOS Where DEPARTAMENTO LIKE UPPER( '%' || ? || '%')";
	// Esta tambien funciona
	//private static String TODOS_POR_DESCRIPCION_LIKE = "Select * from COD_ZONAS Where descripcion LIKE UPPER( CONCAT(CONCAT('%',?),'%'))";
	
	// traigo la instancia ya hecha o va nueva 
	DBConector bd = DBConector.getinstance();
	ArrayList<CodDepartamento> lsdto;
		

	@Override
	public void actualizarCodDepartamento(String idDepartamentoOld, String descDepartamentoNueva)
			throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		int i;
		bd.setPrepStmt(UPD_DTO);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, descDepartamentoNueva);
			bd.getPrepStmt().setString(2, idDepartamentoOld);
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("actualizar Departamento");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("actualizar Departamento");
		} else
			System.out.println("Se actualizaron ["+i+"] registros");
		
	}

	@Override
	public void ingresarCodDepartamento(String idDepartamento, String descDepartamento)
			throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		int i;
		// se prepara el insert
		bd.setPrepStmt(INS_DTO);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, idDepartamento);
			bd.getPrepStmt().setString(2, descDepartamento);
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Insertar Departamento");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Insertar Departamento");
		} else
			System.out.println("Se ingresaron ["+i+"] registros");
	}

	@Override
	public void eliminarCodDepartamento(String idDepartamento)
			throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		int i;
		// se prepara el delete
		bd.setPrepStmt(DEL_DTO);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, idDepartamento);
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Borrar Departamento");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Borrar Departamento");
		} else
			System.out.println("Se borró ["+i+"] registros");
		
	}

	@Override
	public List<CodDepartamento> obtenerCodDepartamento(String descDepartamento) {
		bd.setPrepStmt(TODOS_POR_DEPARTAMENTO);
		lsdto = new ArrayList<>();
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, descDepartamento);
			bd.execQry();
			
			while ( bd.getResultSet().next()) {
				CodDepartamento dto = new CodDepartamento();
				dto.setIdCodDepartamento( bd.getResultSet().getInt(1));
				dto.setDescCodDepartamento(bd.getResultSet().getString(2));
				lsdto.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Departamento  por Descripcion -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lsdto;
	}

	@Override
	public List<CodDepartamento> obtenerCodDepartamentoId(String idDepartamento) {
		bd.setPrepStmt(TODOS_POR_ID);
		lsdto = new ArrayList<>();
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, idDepartamento);
			bd.execQry();
			
			while ( bd.getResultSet().next()) {
				CodDepartamento dto = new CodDepartamento();
				dto.setIdCodDepartamento( bd.getResultSet().getInt(1));
				dto.setDescCodDepartamento(bd.getResultSet().getString(2));
				lsdto.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Departamento  por ID -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lsdto;

	}

	@Override
	public List<CodDepartamento> obtenerCodDepartamentoLike(String descDepartamento) {
		bd.setPrepStmt(TODOS_POR_DEPARTAMENTO_LIKE);
		lsdto = new ArrayList<>();
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, descDepartamento);
			bd.execQry();
			
			while ( bd.getResultSet().next()) {
				CodDepartamento dto = new CodDepartamento();
				dto.setIdCodDepartamento( bd.getResultSet().getInt(1));
				dto.setDescCodDepartamento(bd.getResultSet().getString(2));
				lsdto.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Departamento  por Descripcion -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lsdto;
	}

	@Override
	public List<CodDepartamento> obtenerCodDepartamento() {
		
		lsdto = new ArrayList<>();	
		try {
			bd.execStmt(TODOS);
			
			while ( bd.getResultSet().next()) {
				CodDepartamento dto = new CodDepartamento();
				dto.setIdCodDepartamento( bd.getResultSet().getInt(1));
				dto.setDescCodDepartamento(bd.getResultSet().getString(2));
				lsdto.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar Departamento  por Descripcion -->" + e.getMessage());
			e.printStackTrace();
		}		
		return lsdto;
	}



}
