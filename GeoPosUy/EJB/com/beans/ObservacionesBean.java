package com.beans;

import java.sql.SQLException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import com.clases.Fenomeno;
import com.clases.Observacion;
import com.clases.Usuario;
import com.clases.relaciones.RelUbicacion;
import com.dao.ObservacionDao;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

/**
 * Session Bean implementation class ObservacionesBean
 */
@Stateful
public class ObservacionesBean implements ObservacionesBeanRemote {

	@EJB
	private ObservacionDao servicio;
    
    public ObservacionesBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public int registrarObservacion(long id_observacion, String descripcion, String geolocalizacion, Date fecha_hora, RelUbicacion ubicacion,
			Usuario usuario) {
		
		int numeroError = 0;
		
		if(descripcion == null || descripcion == "" || descripcion.length() > 100) {			
			//Captar del otro lado este numero y mostrar el mensaje error correspondiente, lo mismo con las otras verificaciones
			numeroError = 1;
			return numeroError;
		}
		if(geolocalizacion == null || geolocalizacion == "" || geolocalizacion.length() > 150) {			
			numeroError = 2;
			return numeroError;
		}
		if(fecha_hora == null) {			
			numeroError = 3;
			return numeroError;
		}
		if(ubicacion == null) {			
			numeroError = 4;
			return numeroError;
		}
		if(usuario == null) {			
			numeroError = 5;
			return numeroError;
		}
		
		
		Observacion obs = new Observacion(id_observacion, descripcion, geolocalizacion, fecha_hora, ubicacion, usuario);
		
		try {
			servicio.registrarObservacion(obs);
		} catch (ProblemasNivelSQLException | NoSeRealizoOperacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numeroError;
	}
    
    @Override
    public boolean existeObservacionPorFenomeno(String fenom) throws Exception {
    	return this.servicio.buscarObservacionesPorFenomeno(fenom);
    	
    
    }

}
