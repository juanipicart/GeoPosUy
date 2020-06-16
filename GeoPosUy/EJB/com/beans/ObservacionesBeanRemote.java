package com.beans;

import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Remote;

import com.clases.Usuario;
import com.clases.relaciones.RelUbicacion;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface ObservacionesBeanRemote {


	public int registrarObservacion(long id_observacion, String descripcion, String geolocalizacion, Date fecha_hora,
			RelUbicacion ubicacion, Usuario usuario);

	public boolean existeObservacionPorFenomeno(String fenom) throws ProblemasNivelSQLException, NoSeRealizoOperacionException, SQLException, Exception;

}
