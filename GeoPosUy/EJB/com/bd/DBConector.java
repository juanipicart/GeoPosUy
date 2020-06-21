package com.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConector {

	
	private final String dataSrc = "java:/OracleDS";
	//private final String usr = "proyecto";
	//private final String pwd = "proyecto";
	//private final String srv = "127.0.0.1";
	//private final String instancia = "xe";
	
	private Connection sqlCnn = null; 
	private static DBConector conector;
	private PreparedStatement prepstmt;
	private Statement stmt;
	private ResultSet resultset;
	
	// creo la conexion si no existe
	public static DBConector getinstance() {
		if ( DBConector.conector == null) {
			System.out.println("Nueva instancia DBConexion..");
			conector = new DBConector();
		}
		System.out.println("Ya existe Instancia DBConexion..");
		return conector;
	}

	// Preparar una SQL prepared statement para no tener que hacer una asignacion afuera 
	public void setPrepStmt(String Sttm) {
		try {
			prepstmt = this.sqlCnn.prepareStatement(Sttm);
			System.out.println("Ejecutó seteo consulta pre parseada..=" + Sttm);
		} catch (SQLException e) {
			System.out.println("Error en Prepared Statement-->");
			e.printStackTrace();
		}
	}
	
	// devolver el objeto prepared para setearle los parametros afuera
	public PreparedStatement getPrepStmt() {
		return this.prepstmt;
	}
	
	public ResultSet getResultSet() {
		return this.resultset;
	}
	
	public int execDML() {
		int i = -1;
		try {
			i = this.prepstmt.executeUpdate();
			System.out.println("Ejecutó DML..");
		} catch (SQLException e) {
			System.out.println("Error al ejecutar DDL-->");
			e.printStackTrace();
		}
		return i;
	}
	
	// ejecutar consulta SQL Prepared
	public void execQry() {
		
		try {
			this.resultset = this.prepstmt.executeQuery();
			System.out.println("Ejecutó consulta pre parseada..");
		} catch (SQLException e) {
			System.out.println("Error al ejecutar Consulta-->");
			e.printStackTrace();
		}
	}
	

	// Ejecutar una SQL simple  
	public void execStmt(String Sttm) {
		try {
			stmt = this.sqlCnn.createStatement();
			this.resultset = stmt.executeQuery(Sttm);
			System.out.println("Ejecutó consulta simple..=" +Sttm);
		} catch (SQLException e) {
			System.out.println("Error en Statement-->");
			e.printStackTrace();
		}
	}
	
	// Contructor que crea la conexion a la bd. es un singleton para mantener una sola instancia de dichas conexiones 
	protected DBConector() {
		
		Locale.setDefault(Locale.ENGLISH);
		Context ctx; // contexto jndi
		DataSource ds; // datasource
				
		// Relizar la conexion via datasource
		try {
			ctx = new InitialContext();
			System.out.println("Contexto cargado...");
			
			ds = (DataSource) ctx.lookup(dataSrc);
			System.out.println("Encontré el Datasource...");
			
			this.sqlCnn = ds.getConnection();
			System.out.println("Capturo la conexion..");
			
		}catch (Exception e) {
			System.out.println("Error al buscar Driver Oracle-->" +e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Conectado..");
	}

}
