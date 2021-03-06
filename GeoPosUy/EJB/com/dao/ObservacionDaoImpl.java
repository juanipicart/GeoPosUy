package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.bd.DBConector;
import com.clases.Fenomeno;
import com.clases.Observacion;
import com.dao.ubicaciones.CodDepartamentosDao;
import com.dao.ubicaciones.CodLocalidadesDao;
import com.dao.ubicaciones.CodZonasDao;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@LocalBean
@Stateless
public class ObservacionDaoImpl implements ObservacionDao{

	
	DBConector bd = DBConector.getinstance();
	
	
	
	private static final String insertarObservacion="Insert Into OBSERVACIONES (ID_OBSERVACION,  DESCRIPCION,  GEOLOCALIZACION,  "	
			+ "FECHA_HORA,  ID_USUARIO,  NIVEL_CRITICIDAD,  ID_LOCALIDAD,  "	
			+ "ID_DEPARTAMENTO,  ID_ZONA,  REVISADO,  OBSERVACIONES_VALIDACION, ACTIVO, ID_FENOMENO) Values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?)" ; 
	private static final String modificaObservacion="update OBSERVACIONES set DESCRIPCION = ?,  GEOLOCALIZACION = ?,  "	
			+ "FECHA_HORA = ?,  ID_USUARIO = ?,  NIVEL_CRITICIDAD = ?,  ID_LOCALIDAD = ?,  "	
			+ "ID_DEPARTAMENTO = ?,  ID_ZONA = ?,  REVISADO = ?,  OBSERVACIONES_VALIDACION = ? Where ID_OBSERVACION = ?" ;	
	private static final String selectNextId = "SELECT MAX(ID_OBSERVACION) + 1 FROM OBSERVACIONES";
		
		
	private static final String buscarTodosActivos = "Select * from OBSERVACIONES WHERE ACTIVO = 1";	
	private static final String obtenerTodas = "SELECT PALABRA FROM PALABRAS_PROHIBIDAS ORDER BY PALABRA ASC";

	
	@EJB
	FenomenoDao fenomenoDao;
	UsuarioDao usrDao;	
	CodLocalidadesDao locDao; 	
	CodDepartamentosDao depDao;	
	CodZonasDao zonaDao;
	
	@Override	
	public void registrarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {	
			
		int i = 0; 	
		bd.setPrepStmt(insertarObservacion);	
			
		try {	
			java.sql.Date sqlDate = new java.sql.Date(obs.getFecha_hora().getTime());	
			bd.getPrepStmt().setString(1, obs.getDescripcion().toUpperCase());	
			bd.getPrepStmt().setString(2, obs.getGeolocalizacion().toUpperCase());	
			bd.getPrepStmt().setDate(3, sqlDate);	
			bd.getPrepStmt().setLong(4, obs.getId_usuario());	
			bd.getPrepStmt().setInt(5, obs.getNivel_criticidad());	
			bd.getPrepStmt().setLong(6, obs.getId_localidad());	
			bd.getPrepStmt().setLong(7, obs.getId_departamento()); 	
			bd.getPrepStmt().setLong(8, obs.getId_zona());	
			bd.getPrepStmt().setInt(9, obs.getRevisado());	
			bd.getPrepStmt().setString(10, obs.getObsValidador());
			bd.getPrepStmt().setLong(11, obs.getId_fenomeno());
		} catch (SQLException e) {	
			throw new ProblemasNivelSQLException(e.getMessage());	
		}	
		i = bd.execDML();	
		if ( i == 0) {	
			throw new NoSeRealizoOperacionException("Insertar observaci�n");	
		} else if (i < 0) {	
			throw new  ProblemasNivelSQLException("Insertar observaci�n");	
		} else	
			System.out.println("Se ingresaron ["+i+"] registros");		
			
	}	

	@Override	
	public void modificarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {	
			
		int i = 0;	
		bd.setPrepStmt(modificaObservacion);	
			
		try {	
			java.sql.Date sqlDate = new java.sql.Date(obs.getFecha_hora().getTime());	
				
			bd.getPrepStmt().setString(1, obs.getDescripcion().toUpperCase());	
			bd.getPrepStmt().setString(2, obs.getGeolocalizacion().toUpperCase());	
			bd.getPrepStmt().setDate(3, sqlDate);	
			bd.getPrepStmt().setLong(4, obs.getId_usuario());	
			bd.getPrepStmt().setInt(5, obs.getNivel_criticidad());	
			bd.getPrepStmt().setLong(6, obs.getId_localidad());	
			bd.getPrepStmt().setLong(7, obs.getId_departamento()); 	
			bd.getPrepStmt().setLong(8, obs.getId_zona());	
			bd.getPrepStmt().setInt(9, obs.getRevisado());	
			bd.getPrepStmt().setString(10, obs.getObsValidador());	
			bd.getPrepStmt().setLong(11, obs.getId_observacion());	
		} catch (SQLException e) {	
			throw new ProblemasNivelSQLException(e.getMessage());	
		}	
		i= bd.execDML();	
			
		if ( i == 0) {	
			throw new NoSeRealizoOperacionException("Modificar observacion");	
		} else if (i < 0) {	
			throw new  ProblemasNivelSQLException("Modificar observacion");	
		} else	
			System.out.println("Se modificaron ["+i+"] registros");			
			
	}
	
	@Override
	public boolean buscarObservacionesPorFenomeno(String codigo) throws Exception {
		
		String ObsPorFenomeno = "Select * from OBSERVACIONES where ID_FENOMENO = ?";
		
		Fenomeno fenomeno = fenomenoDao.selectFenomenoPorCodigo(codigo);
		Long id = fenomeno.getId_fenomeno();
		
		
		bd.setPrepStmt(ObsPorFenomeno);
		bd.getPrepStmt().setLong(1, id);			
		ResultSet resultado = bd.getPrepStmt().executeQuery();
			
		boolean existe = false;
		if (!resultado.isBeforeFirst() ) {    
			    System.out.println("No data");     
		} else {
			existe = true;
		}
		
		return existe;
		
		}

	@Override	
	public List<Observacion> buscarTodas() throws ProblemasNivelSQLException {	
		List<Observacion> lsObservacion = new ArrayList<Observacion>();	
		bd.setPrepStmt(buscarTodosActivos);	
			
		try {	
			bd.execQry();	
			while (bd.getResultSet().next()) {	
				Observacion obs = getObservacionDesdeResulset(bd.getResultSet());	
				lsObservacion.add(obs);	
			}	
		}catch (SQLException e) {	
			throw new ProblemasNivelSQLException("realizar búsqueda por Usuario");	
		}	
			
		return lsObservacion;	
	}
	@Override	
	public List<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo, Date fechaDesde, Date fechaHasta) throws Exception {	
			
		int contador = 0;	
			
		try {	
				
			List<Observacion> observaciones = new ArrayList<>();	
				
			String ObsPorFenomeno = "Select * from OBSERVACIONES where ID_FENOMENO IN (";
			String Fechas = " AND FECHA_HORA BETWEEN ? AND ?";
			String ids = "";	
			for (Iterator<Long> i = codigo.iterator(); i.hasNext();) {	
				ids += i.next().toString();	
				ids += ",";	
			}	
				
			ids = ids.substring(0,ids.length()-1);	
			ids = ids.concat(")");	
			ObsPorFenomeno = ObsPorFenomeno.concat(ids);
			String SelectFinal = ObsPorFenomeno + Fechas;
				
			bd.setPrepStmt(SelectFinal);
			java.sql.Date sqlDateDesde = new java.sql.Date(fechaDesde.getTime());
			java.sql.Date sqlDateHasta = new java.sql.Date(fechaHasta.getTime());
			bd.getPrepStmt().setDate(1, sqlDateDesde);
			bd.getPrepStmt().setDate(2, sqlDateHasta);
			ResultSet resultado = bd.getPrepStmt().executeQuery();	
				
				
			while (resultado.next()) {	
				Observacion observacion = getObservacionDesdeResulset(resultado);	
				observaciones.add(observacion);	
			} 	
				
			return observaciones;	
				
				
		} catch (SQLException e) {	
			throw new ProblemasNivelSQLException("realizar operaci�n");
	
	}
	}
		
		
		private Observacion getObservacionDesdeResulset(ResultSet res) throws SQLException {	
			
			Observacion obs = new Observacion( 	
					res.getLong("ID_OBSERVACION"), res.getString("DESCRIPCION"), res.getString("GEOLOCALIZACION"), 	
					res.getDate("FECHA_HORA"), res.getLong("ID_USUARIO"),  res.getInt("NIVEL_CRITICIDAD"),	
					res.getLong("ID_LOCALIDAD"), res.getLong("ID_DEPARTAMENTO"), res.getLong("ID_ZONA"), 	
					res.getInt("REVISADO"), res.getString("OBSERVACIONES_VALIDACION"), res.getInt("ACTIVO"), res.getLong("ID_FENOMENO") );	
			return obs;	
				
		}
		
		@Override
		public List<String> obtenerPalabrasProhibidas() throws SQLException, ProblemasNivelSQLException {
			List<String> palabras = new ArrayList<>();
			bd.setPrepStmt(obtenerTodas);
			
			ResultSet resultado = bd.getPrepStmt().executeQuery();	
				
			while (resultado.next()) {	
				String palabra = resultado.getString(1);
				palabras.add(palabra);		
			} 				
			return palabras;					
		}
		
		@Override
		public long obtenerNextVal() throws SQLException {
			long nextVal = 0;
			bd.setPrepStmt(selectNextId);
			
			ResultSet resultado = bd.getPrepStmt().executeQuery();
			
			while (resultado.next()) {
				nextVal = resultado.getLong(1);
			}
			
			return nextVal;
		}


	}
