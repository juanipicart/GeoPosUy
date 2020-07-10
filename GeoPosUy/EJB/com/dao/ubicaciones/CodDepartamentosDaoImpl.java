package com.dao.ubicaciones;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bd.DBConector;
import com.clases.codigueras.Departamento;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public class CodDepartamentosDaoImpl implements CodDepartamentosDao {
	
	private static String TODOS = "Select * from DEPARTAMENTOS order by DEPARTAMENTO"; 
		
	// traigo la instancia ya hecha o va nueva 
	DBConector bd = DBConector.getinstance();
	ArrayList<Departamento> lsdto;
		
	@Override
	public List<Departamento> obtenerCodDepartamento() {
		
		lsdto = new ArrayList<>();	
		try {
			bd.execStmt(TODOS);
			
			while ( bd.getResultSet().next()) {
				Departamento dto = new Departamento();
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
