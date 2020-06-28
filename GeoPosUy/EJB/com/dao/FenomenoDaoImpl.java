package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.swing.DefaultListModel;

import com.bd.DBConector;
import com.clases.Fenomeno;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@LocalBean
@Stateless
public class FenomenoDaoImpl implements FenomenoDao {
	
	DBConector bd = DBConector.getinstance();
	
	private static final String crearFenomeno = "INSERT INTO FENOMENOS (ID_FENOMENO,NOMBRE,DESCRIPCION,CONTACTO_EMERGENCIA,CODIGO) VALUES (ID_FENOMENO_SEQ.NEXTVAL, UPPER(?),UPPER(?),UPPER(?),UPPER(?))";
	private static final String actualizarFenomeno = "UPDATE FENOMENOS SET NOMBRE=UPPER(?),DESCRIPCION=UPPER(?),CONTACTO_EMERGENCIA=UPPER(?) WHERE CODIGO=UPPER(?)";
	private static final String borrarFenomeno = "DELETE FROM FENOMENOS WHERE CODIGO = UPPER(?)";
	private static final String selectTodosFenomenos = "SELECT * FROM FENOMENOS";
	private static final String selectFenomenoPorCodigo = "SELECT * FROM FENOMENOS WHERE CODIGO = UPPER(?)";
	private static final String selectFenomenoPorNombre = "SELECT * FROM FENOMENOS WHERE NOMBRE = UPPER(?)";

		
	@Override
	public void registrarFenomeno(Fenomeno fenomeno) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		
		bd.setPrepStmt(crearFenomeno);
		
		try {
			
			bd.getPrepStmt().setString(1, fenomeno.getNombre());
			bd.getPrepStmt().setString(2, fenomeno.getDescripcion());
			bd.getPrepStmt().setString(3, fenomeno.getContacto_emergencia());
			bd.getPrepStmt().setString(4, fenomeno.getCodigo());
			
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		
		int i = bd.execDML();
		
		// Ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Insertar usuario");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Insertar usuario");
		} else
			System.out.println("Se completó el registro del fenomeno");		
			
	}
	
	@Override
	public void actualizarFenomeno(Fenomeno fenomeno) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		
		bd.setPrepStmt(actualizarFenomeno);
		
		try {
			bd.getPrepStmt().setString(1, fenomeno.getNombre());
			bd.getPrepStmt().setString(2, fenomeno.getDescripcion());
			bd.getPrepStmt().setString(3, fenomeno.getContacto_emergencia());
			bd.getPrepStmt().setString(4, fenomeno.getCodigo());
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		} 
		int i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Modificar fenómeno");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Modificar fenómeno");
		} else
			System.out.println("Se actualizó el fenómeno");	

	}

	@Override
	public boolean eliminarFenomeno(String codigo) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {

		bd.setPrepStmt(borrarFenomeno);
		boolean logrado = false;
		try {
			bd.getPrepStmt().setString(1, codigo);
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		
		int i= bd.execDML();
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Eliminar fenómeno");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Eliminar fenómeno");
		} else {
			System.out.println("Se eliminaron ["+i+"] registros");
		logrado = true;
		}
		
		return logrado;	
	}

	@Override	
	public DefaultListModel<Fenomeno> consultarFenomenos() throws Exception {	
		DefaultListModel<Fenomeno> fenomenos = new DefaultListModel<>();	
		int contador = 0;	
		bd.setPrepStmt(selectTodosFenomenos);	
			
		try {	
							
			ResultSet resultado = bd.getPrepStmt().executeQuery();	
				
			while (resultado.next()) {	
				Fenomeno fenomeno = getFenomenoDesdeResultado(resultado);	
				fenomenos.add(contador, fenomeno);		
				contador++;	
			} 	
			return fenomenos;	
		} catch (ProblemasNivelSQLException e) {	
			throw new ProblemasNivelSQLException("Realizar búsqueda");	
		}	
	}
	
	@Override	
	public List<Fenomeno> obtenerTodosLosFenomenos() throws Exception {	
		List<Fenomeno> fenomenos = new ArrayList<Fenomeno>();	
		bd.setPrepStmt(selectTodosFenomenos);	
			
		try {	
							
			ResultSet resultado = bd.getPrepStmt().executeQuery();	
				
			while (resultado.next()) {	
				Fenomeno fenomeno = getFenomenoDesdeResultado(resultado);	
				fenomenos.add(fenomeno);		
			} 	
			
			resultado.close();
			return fenomenos;
			
		} catch (ProblemasNivelSQLException e) {	
			throw new ProblemasNivelSQLException("Realizar búsqueda");	
		}	
	}

	@Override
	public Fenomeno selectFenomenoPorCodigo(String codigo) throws Exception
	{

		Fenomeno fenomeno = null;
		bd.setPrepStmt(selectFenomenoPorCodigo);
		
		try { 
			bd.getPrepStmt().setString(1, codigo);
			bd.execQry();
			
			ResultSet resultado = bd.getResultSet();
			
			while (resultado.next()) {
				fenomeno = getFenomenoDesdeResultado(resultado);
			} }
		 catch (ProblemasNivelSQLException e) {
			throw new ProblemasNivelSQLException("realizar búsqueda");
		} return fenomeno;
	}
	
	@Override
	public Fenomeno selectFenomenoPorNombre(String nombre) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		
		Fenomeno fenomeno = null;
		bd.setPrepStmt(selectFenomenoPorNombre);
		
		try {
			bd.getPrepStmt().setString(1, nombre);
			bd.execQry();
			
			ResultSet resultado = bd.getResultSet();
			
			while (resultado.next()) {
				fenomeno = getFenomenoDesdeResultado(resultado);
			} return fenomeno;
		} catch (ProblemasNivelSQLException | SQLException e) {
			throw new ProblemasNivelSQLException("realizar búsqueda");
		}
	}


	private static Fenomeno getFenomenoDesdeResultado(ResultSet resultado) throws ProblemasNivelSQLException {
		
	try {
		Long id_fenomeno = resultado.getLong("ID_FENOMENO");
		String codigo = resultado.getString("CODIGO");
		String nombre = resultado.getString("NOMBRE");
		String descripcion = resultado.getString("DESCRIPCION");
		String contacto = resultado.getString("CONTACTO_EMERGENCIA");
		
		Fenomeno fenomeno = new Fenomeno(id_fenomeno, nombre, descripcion, contacto);
		fenomeno.setCodigo(codigo);
		
		return fenomeno; 
	} catch (SQLException e) {
		throw new ProblemasNivelSQLException("realizar operación");
		
	}

	}
}


