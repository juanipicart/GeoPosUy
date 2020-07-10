package com.dao.ubicaciones;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bd.DBConector;
import com.clases.codigueras.Zona;

public class CodZonasDaoImpl implements CodZonasDao {

	
	
	private static String TODOS = "Select * from ZONAS order by descripcion"; 

	
	// traigo la instancia ya hecha o va nueva 
	DBConector bd = DBConector.getinstance();
	ArrayList<Zona> lszona;
	
	@Override
	public List<Zona> obtenerCodZona() {
		lszona = new ArrayList<>();
		try {
			bd.execStmt(TODOS);
			while ( bd.getResultSet().next()) {
				Zona glZona = new Zona();
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
