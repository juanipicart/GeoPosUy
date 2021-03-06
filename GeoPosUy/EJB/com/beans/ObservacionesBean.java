package com.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
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
	public boolean ingresarObservacion(Observacion insObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException {	
		
		boolean registroExitoso = false;
				
		try {
			servicio.registrarObservacion(insObservacion);
			registroExitoso = true;
		} catch (ProblemasNivelSQLException | NoSeRealizoOperacionException e) {
			
			e.printStackTrace();
		}
	
	return (registroExitoso);
	}	
		

	@Override	
	public int modificarObservacion(Observacion modifObservacion) throws NoValidaParamException, ProblemasNivelSQLException, NoSeRealizoOperacionException {	

		// Validar param entrada	
	    validarParamObservacion(modifObservacion);	
	    validarLatLong(modifObservacion.getGeolocalizacion());	
		//	
		servicio.modificarObservacion(modifObservacion);	
			
		return 0;	
	}
	
	// devolver todos	
	@Override	
	public List<Observacion> buscarObservacion() throws ProblemasNivelSQLException {	
			
		List<Observacion> observacion = new ArrayList<Observacion>();	
		observacion = servicio.buscarTodas();	
		return observacion;	
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
    	if (obs.getId_departamento() ==0) 	
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
    	String[] palabrasDescripcion = texto.split(" ");
    	
    	
    		todasLasPalabras = servicio.obtenerPalabrasProhibidas();   	
    	for (int i=0; i<todasLasPalabras.size(); i++) {
    		String palabra = todasLasPalabras.get(i);
    		for (int j=0; j<palabrasDescripcion.length; j++)
    			if (palabrasDescripcion[j].toUpperCase().equals(palabra)) {
    				palabrasQueContiene.add(palabra);
    			}    		
    	}
    	
    	return palabrasQueContiene;
    }
    
    @Override
    public boolean existeObservacionPorFenomeno(String fenom) throws Exception {
    	return this.servicio.buscarObservacionesPorFenomeno(fenom);
    	
    
    }
 
	@Override	
	public List<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo, Date fechaDesde, Date fechaHasta)	
			throws ProblemasNivelSQLException, NoSeRealizoOperacionException, SQLException, Exception {	
		List<Observacion> observaciones = new ArrayList<>();	
			
		try {	
			observaciones = servicio.buscarObservacionesPorFenomenos(codigo, fechaDesde, fechaHasta);	
		} catch (ProblemasNivelSQLException | NoSeRealizoOperacionException e) {	
			e.printStackTrace();	
		}	
			
		return observaciones;	
			
	}
	
	@Override
	public long obtenerNextVal() throws SQLException {
		return servicio.obtenerNextVal();
	}
	
	@Override
	public int obtenerCriticidad(String nivel) {
		int nivelcriticidad = 0;
		if (nivel.equalsIgnoreCase("Alta")) {
			nivelcriticidad = 1;
		} else if (nivel.equalsIgnoreCase("Media")) {
			nivelcriticidad = 2;
		} else if (nivel.equalsIgnoreCase("Baja")) {
			nivelcriticidad = 3;
		} else if (nivel.equalsIgnoreCase("Informe")) {
			nivelcriticidad = 4;
		}
		
		return nivelcriticidad;
	}



}

