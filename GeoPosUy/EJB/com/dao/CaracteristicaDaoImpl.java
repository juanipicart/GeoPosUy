package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.bd.DBConector;
import com.clases.Caracteristica;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public class CaracteristicaDaoImpl implements CaracteristicaDao {
	
	DBConector bd = DBConector.getinstance();
	
	private static final String crearCaracteristica = "Insert into CARACTERISTICAS Values(NULL,UPPER('?'),UPPER('?'))";
	private static final String borrarCaracteristica = "Delete from CARACTERISTICAS where DESCRIPCION= UPPER('?')";
	private static final String modificaCaracteristica = "Update CARACTERISTICAS set DESCRIPCION = UPPER('?'), UNIDAD_MEDIDA = UPPER('?') where id_caracteristica=?";
	private static final String selectCaracteristicas = "SELECT * FROM caracteristicas";
	private static final String selectCaracteristicaPorID = "SELECT * FROM caracteristicas WHERE ID_CARACTERISTICA=?";

	@Override
	public void registrarCaracteristica(Caracteristica caract) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		int i;
		// se prepara el insert
		bd.setPrepStmt(crearCaracteristica);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, caract.getDescripcion());
			bd.getPrepStmt().setString(2, caract.getUnid_medida());
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
	public void eliminarCaracteristica(String descripcion) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		int i;
		bd.setPrepStmt(borrarCaracteristica);
		

		try {
			bd.getPrepStmt().setString(1, descripcion);			
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
		int i;
		// se prepara el insert
		bd.setPrepStmt(modificaCaracteristica);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, caract.getDescripcion());
			bd.getPrepStmt().setString(2, caract.getUnid_medida());
			bd.getPrepStmt().setLong(2, caract.getId_caracteristica());
		
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
		bd.setPrepStmt(selectCaracteristicas);
		
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
		bd.setPrepStmt(selectCaracteristicaPorID);
		
		try {
			bd.getPrepStmt().setLong(1, id);
			bd.execQry();
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
			String descripcion = resultado.getString("DESCRIPCION");
			String unidad_medida = resultado.getString("UNIDAD_MEDIDA");
			
			Caracteristica caracteristica = new Caracteristica(id_caract, descripcion, unidad_medida);
			
			return caracteristica;
			
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException("realizar operación");		}
	
	}
		
	
	
		
		
		
	
		
		
		
}
