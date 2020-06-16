package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.bd.DBConector;
import com.clases.Caracteristica;
import com.clases.Fenomeno;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public class CaracteristicaDaoImpl implements CaracteristicaDao {
	
	DBConector bd = DBConector.getinstance();
	
	private static final String selectFenomenos = "SELECT * FROM FENOMENOS";
	private static final String selectFenomenoPorID = "SELECT * FROM FENOMENOS WHERE ID_FENOMENO=?";

	@Override
	public void registrarCaracteristica(Caracteristica caract) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		String INS_CARACT = "Insert into CARACTERISTICAS Values(NULL,UPPER('?'),UPPER('?'),UPPER('?'),UPPER('?'))"; 
		int i;
		// se prepara el insert
		bd.setPrepStmt(INS_CARACT);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, caract.getNombre());
			bd.getPrepStmt().setString(1, caract.getEtiqueta());
			bd.getPrepStmt().setString(1, caract.getTipo());
			if(caract.getDescripcion() != "") {
				bd.getPrepStmt().setString(1, caract.getDescripcion());
			}

		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		
		i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Insertar caracteristica");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Insertar caracteristica");
		} else
			System.out.println("Se ingresaron ["+i+"] registros");		
	}
	
	
	@Override
	public void eliminarCaracteristica(String nombre) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		String DEL_CARACT = "Delete from CARACTERISTICAS where nombre= ?";
		int i;
		
		bd.setPrepStmt(DEL_CARACT);
		

		try {
			bd.getPrepStmt().setString(1, nombre);			
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Eliminar caracteristica");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Eliminar caracteristica");
		} else
			System.out.println("Se eliminaron ["+i+"] registros");
		
	}
	
	
	@Override
	public void modificarCaracteristica(Caracteristica caract) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		String UPD_CARACT = "Update CARACTERISTICAS set nombre = UPPER('?'), etiqueta = UPPER('?'), tipo = UPPER('?'), descripcion = UPPER('?') where id_usuario=" + caract.getId_caracteristica() + "";
		int i;
		// se prepara el insert
		bd.setPrepStmt(UPD_CARACT);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, caract.getNombre());
			bd.getPrepStmt().setString(2, caract.getEtiqueta());
			bd.getPrepStmt().setString(3, caract.getTipo());
			if(caract.getDescripcion() != "") {
				bd.getPrepStmt().setString(4, caract.getDescripcion());
			}

		
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Modificar caracteristica");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Modificar caracteristica");
		} else
			System.out.println("Se modificaron ["+i+"] registros");		
	}


	@Override
	public LinkedList<Caracteristica> selectAll() throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		LinkedList<Caracteristica> caracteristicas = new LinkedList<>();
		bd.setPrepStmt(selectFenomenos);
		
		try {
			
			ResultSet resultado = bd.getPrepStmt().executeQuery();
			
			while (resultado.next()) {
				Caracteristica caracteristica = getCaracteristicaDesdeResultado(resultado);
				caracteristicas.add(caracteristica);
			} return caracteristicas;
		} catch (SQLException e) {
    		throw new ProblemasNivelSQLException("realizar búsqueda");
    	} 
	}

	@Override
	public Caracteristica selectCaractByID(long id) throws ProblemasNivelSQLException, NoSeRealizoOperacionException, SQLException {
		Caracteristica caracteristica = null;
		bd.setPrepStmt(selectFenomenoPorID);
		try {
			bd.getPrepStmt();
			ResultSet resultado = bd.getResultSet();
			while (resultado.next()) {
				caracteristica = getCaracteristicaDesdeResultado(resultado);
				} return caracteristica;
			} catch (SQLException e) {
				throw new ProblemasNivelSQLException("realizar busqueda");			
		}
	}
		
	
	private Caracteristica getCaracteristicaDesdeResultado(ResultSet resultado) throws ProblemasNivelSQLException {
		
		try {
			Long id_caract = resultado.getLong("ID_CARACTERISICA");
			String etiqueta = resultado.getString("ETIQUETA");
			String nombre = resultado.getString("NOMBRE");
			String tipo = resultado.getString("TIPO");
			String descripcion = resultado.getString("DESCRIPCION");
			
			Caracteristica caracteristica = new Caracteristica(id_caract, etiqueta, nombre, tipo, descripcion);
			
			return caracteristica;
			
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException("realizar operación");		}
	
		
	}
		
	
	
		
		
		
	
		
		
		
}
