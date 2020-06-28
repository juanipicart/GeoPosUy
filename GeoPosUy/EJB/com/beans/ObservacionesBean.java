package com.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.swing.DefaultListModel;

import com.clases.Observacion;
import com.dao.ObservacionDao;
import com.dao.PalabrasProhibidasDao;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.NoValidaParamException;
import com.exceptions.ProblemasNivelSQLException;

/**
 * Session Bean implementation class ObservacionesBean
 */
@Stateful
public class ObservacionesBean implements ObservacionesBeanRemote {

	@EJB
	private ObservacionDao servicio;
	private PalabrasProhibidasDao consPalabraProhibida;
    
    public ObservacionesBean() {
        // TODO Auto-generated constructor stub
    }
    
    
	@Override	
	public int ingresarObservacion(Observacion insObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException {	
		// Validar param entrada	
		validarParamObservacion(insObservacion);	
		validarLatLong(insObservacion.getGeolocalizacion());	
		//	
		servicio.registrarObservacion(insObservacion);	
		return 0;	
	}	
		
	@Override	
	public int borrarObservacion(Observacion delObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException {	
			
		// validar id no vacio	
		if ( delObservacion.getId_observacion() == 0) throw new NoValidaParamException("ID Observacion");	
			
		servicio.eliminarObservacion( delObservacion.getId_observacion() );	
		return 0;	
	}	 
	
	@Override	
	public int modificarObservacion(Observacion modifObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException {	
		// 	
		// Validar param entrada	
	    validarParamObservacion(modifObservacion);	
	    validarLatLong(modifObservacion.getGeolocalizacion());	
		//	
		servicio.modificarObservacion(modifObservacion);	
			
		return 0;	
	}
	public List<Observacion> buscarObservacionPorUsuario(long idUsuario) throws NoValidaParamException, ProblemasNivelSQLException {	
		if ( idUsuario == 0) throw new NoValidaParamException("ID Usuario");	
			
		List<Observacion> observacionXUsuario = new ArrayList<Observacion>();	
		observacionXUsuario = servicio.buscarPorUsuario(idUsuario);	
		return observacionXUsuario;	
	}	
	// devolver todos	
	@Override	
	public List<Observacion> buscarObservacion() throws ProblemasNivelSQLException {	
			
		List<Observacion> observacion = new ArrayList<Observacion>();	
		observacion = servicio.buscarTodas();	
		return observacion;	
	}	
	// Devolver por id	
	@Override	
	public Observacion buscarObservacionPorID(long idObservacion) throws NoValidaParamException, ProblemasNivelSQLException {	
		if ( idObservacion == 0) throw new NoValidaParamException("ID Observacion");	
		Observacion observacionXId = null;	
		observacionXId = servicio.buscarPorId(idObservacion);	
		return observacionXId;	
	}	
	// Validar parametros correctos	
    private void validarParamObservacion(Observacion obs) throws NoValidaParamException, ProblemasNivelSQLException  {	
			
    	if (obs.getDescripcion().isEmpty() || obs.getDescripcion().length()>100) 	
    		throw new NoValidaParamException("Descripcion") ;	
    	if (obs.getGeolocalizacion().isEmpty() || obs.getGeolocalizacion().length()>150) 	
    		throw new NoValidaParamException("Geolocalizacion");	
    	if (obs.getFecha_hora() == null)	
    		throw new NoValidaParamException("Fecha/Hora");	
    	if (obs.getId_usuario() == 0) 	
    		throw new NoValidaParamException("Usuario");	
    	if (obs.getNivel_criticidad() < 1 && obs.getNivel_criticidad() > 4 ) 	
    		throw new NoValidaParamException("Nivel Criticidad");	
    	if (obs.getId_localidad() == 0) 	
    		throw new NoValidaParamException("Localidad");	
    	if (obs.getId_departamento().isEmpty()) 	
    		throw new NoValidaParamException("Deparatamento");	
    	if (obs.getId_zona() == 0) 	
    		throw new NoValidaParamException("Zona");	
    	if (obs.getObsValidador().length() >100 ) 	
    		throw new NoValidaParamException("Observaciones de Validador");	
    	// Validacion palabras prohibidas	
    	if (validarPalabrasProhibidas(obs.getDescripcion()) > 0) 	
    		throw new NoValidaParamException("Contiene palabras prohibidas en Descripcion");	
    		
    }	
   // Validar latitud y longitud
    @Override
	public boolean validarLatLong(String latlong)  throws NoValidaParamException {	
    	// Latitud, Longitud Maxima en Decimal	
    	//	
    	final double longMaxW = -58.433611;     		
    	final double longMaxE = -53.182778;	
    	final double latMaxS  = -34.974167;	
    	final double latMaxN  = -30.085556;	
    	//	
    		
    	boolean correcto = true;
    	String[] strLatLong;	
    		
    	double latitud=0;	
    	double longitud=0;	
    		
    	latlong.trim();	
    	strLatLong = latlong.split(",");	
    	latitud = Double.valueOf(strLatLong[0]);	
    	longitud = Double.valueOf(strLatLong[1]);	
    	
    
    	if (latitud > latMaxN || latitud < latMaxS) {
    			correcto = false;
    	} else if (longitud > longMaxE || longitud < longMaxW) {
    			correcto = false;
    	}
    	    	
    	return correcto;
    	
    }	
    
    // Validar palabras prohibidas 	
    private int validarPalabrasProhibidas(String palabra)  throws NoValidaParamException, ProblemasNivelSQLException {	
    		
    	return consPalabraProhibida.consPalabraProhibida(palabra);	
    		
    }
   
    @Override
    public List<String> contienePalabrasProhibidas(String texto) throws SQLException, ProblemasNivelSQLException {
    	List<String> todasLasPalabras = new ArrayList<String>();
    	List<String> palabrasQueContiene = new ArrayList<String>();
    	
    	if (!(servicio.obtenerPalabrasProhibidas().isEmpty())) {
    		todasLasPalabras = servicio.obtenerPalabrasProhibidas();   	
    	for (int i=0; i<todasLasPalabras.size(); i++) {
    		String palabra = todasLasPalabras.get(i);
    			if (texto.contains(palabra)) {
    				palabrasQueContiene.add(palabra);
    			}    		
    	}
    	}
    	return palabrasQueContiene;
    }
    
    @Override
    public boolean existeObservacionPorFenomeno(String fenom) throws Exception {
    	return this.servicio.buscarObservacionesPorFenomeno(fenom);
    	
    
    }
 
	@Override	
	public List<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo)	
			throws ProblemasNivelSQLException, NoSeRealizoOperacionException, SQLException, Exception {	
		List<Observacion> observaciones = new ArrayList<>();	
			
		try {	
			observaciones = servicio.buscarObservacionesPorFenomenos(codigo);	
		} catch (ProblemasNivelSQLException | NoSeRealizoOperacionException e) {	
			// TODO Auto-generated catch block	
			e.printStackTrace();	
		}	
			
		return observaciones;	
			
	}



}
