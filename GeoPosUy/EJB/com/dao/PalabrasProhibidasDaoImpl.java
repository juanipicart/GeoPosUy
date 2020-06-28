package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.bd.DBConector;
import com.clases.Fenomeno;
import com.clases.codigueras.PalabraProhibida;
import com.exceptions.ProblemasNivelSQLException;

@Stateless
@LocalBean
public class PalabrasProhibidasDaoImpl implements PalabrasProhibidasDao {

	DBConector bd = DBConector.getinstance();
	
	private static final String buscarPalabra = "select count(*) from cod_palabras_prohibidas c where instr(replace( ? ,' ','') ,upper(c.palabra)) >0"; 
	private static final String obtenerTodas = "SELECT PALABRA FROM PALABRAS_PROHIBIDAS ORDER BY PALABRA ASC";
	
	@Override
	public int consPalabraProhibida(String Palabra) throws ProblemasNivelSQLException {
		String strcons = Palabra.toUpperCase();
		int retorno = 0;
		
		bd.setPrepStmt(buscarPalabra);
		
		try {
			bd.getPrepStmt().setString(1, strcons);
			bd.execQry();
			ResultSet res = bd.getResultSet();
			
			if (res.next()) {
				retorno = res.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}		
		
		return retorno;
	}
	
}