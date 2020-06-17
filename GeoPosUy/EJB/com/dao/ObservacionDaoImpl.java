package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.swing.DefaultListModel;

import com.bd.DBConector;
import com.clases.Fenomeno;
import com.clases.Observacion;
import com.clases.relaciones.Rel_Observ_Fenom_Caract;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@LocalBean
@Stateless
public class ObservacionDaoImpl implements ObservacionDao{

	
	DBConector bd = DBConector.getinstance();
	
	@EJB
	FenomenoDao fenomenoDao;
	
	/*@Override
	public void registrarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		
		String INS_OBS = "Insert into OBSERVACIONES Values(NULL,?,?,?,?,?)"; 
		int i;

		bd.setPrepStmt(INS_OBS);
		

		try {
			java.sql.Date sqlDate = new java.sql.Date(obs.getFecha_hora().getTime());
			bd.getPrepStmt().setString(1, obs.getDescripcion().toUpperCase());
			bd.getPrepStmt().setString(2, obs.getGeolocalizacion().toUpperCase());
			bd.getPrepStmt().setDate(3, sqlDate);
			bd.getPrepStmt().setLong(4, obs.getUbicacion().getIdUbicacion());
			bd.getPrepStmt().setLong(5, obs.getUsuario().getId_user());
			
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		

		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Insertar observación");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Insertar observación");
		} else
			System.out.println("Se ingresaron ["+i+"] registros");	
		
	}*/

	@Override
	public void eliminarObservacion(long id_observacion) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		String DEL_OBS = "Delete from OBSERVACIONES where id_observacion= ?";
		int i;
		
		bd.setPrepStmt(DEL_OBS);
		

		try {
			bd.getPrepStmt().setLong(1, id_observacion);			
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		

		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Eliminar observacion");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Eliminar observacion");
		} else
			System.out.println("Se eliminaron ["+i+"] registros");
		
	}

	/*@Override
	public void modificarObservacion(Observacion obs) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		
		String UPD_OBS = "Update OBSERVACION set descripcion = ?, geolocalizacion = ?, fecha_hora = ?, id_ubicacion = ?, id_usuario = ? where id_observacion=" + obs.getId_observacion() + "";
		int i;

		bd.setPrepStmt(UPD_OBS);
		

		try {
			java.sql.Date sqlDate = new java.sql.Date(obs.getFecha_hora().getTime());
			bd.getPrepStmt().setString(1, obs.getDescripcion().toUpperCase());
			bd.getPrepStmt().setString(2, obs.getGeolocalizacion().toUpperCase());
			bd.getPrepStmt().setDate(3, sqlDate);
			bd.getPrepStmt().setLong(4, obs.getUbicacion().getIdUbicacion());
			bd.getPrepStmt().setLong(5, obs.getUsuario().getId_user());	
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
		
	}*/
	
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
	public DefaultListModel<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo) throws Exception {	
			
		int contador = 0;	
			
		try {	
				
			DefaultListModel<Observacion> observaciones = new DefaultListModel<>();	
				
			String ObsPorFenomeno = "Select * from OBSERVACIONES where ID_FENOMENO IN (";	
			String ids = "";	
			for (Iterator<Long> i = codigo.iterator(); i.hasNext();) {	
				ids += i.next().toString();	
				ids += ",";	
			}	
				
			ids = ids.substring(0,ids.length()-1);	
			ids = ids.concat(")");	
			ObsPorFenomeno = ObsPorFenomeno.concat(ids);	
				
			bd.setPrepStmt(ObsPorFenomeno);		
			ResultSet resultado = bd.getPrepStmt().executeQuery();	
				
				
			while (resultado.next()) {	
				Observacion observacion = getObservacionDesdeResultado(resultado);	
				observaciones.add(contador,observacion);	
				contador++;	
			} 	
				
			return observaciones;	
				
				
		} catch (SQLException e) {	
			throw new ProblemasNivelSQLException("realizar operación");
	
	}
	}
		
		private static Observacion getObservacionDesdeResultado(ResultSet resultado) throws ProblemasNivelSQLException {	
			
			try {	
				Long id_observacion = resultado.getLong("ID_OBSERVACION");	
				Long id_usuario = resultado.getLong("ID_USUARIO");	
				String descripcion = resultado.getString("DESCRIPCION");	
				String geolocalizacion = resultado.getString("GEOLOCALIZACION");	
				Date fechaHora = resultado.getDate("FECHA_HORA");	
				Long id_fenomeno = resultado.getLong("ID_FENOMENO");	
					
					
				Observacion obs = new Observacion(id_observacion,id_usuario, descripcion, geolocalizacion,fechaHora,id_fenomeno);	
					
				return obs; 	
			} catch (SQLException e) {	
				throw new ProblemasNivelSQLException("realizar operación");	
					
			}	
			}
	}
		


