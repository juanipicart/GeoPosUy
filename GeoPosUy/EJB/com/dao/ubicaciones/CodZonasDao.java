package com.dao.ubicaciones;

import java.util.List;

import com.clases.codigueras.CodZona;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

public interface CodZonasDao {

	public void actualizarCodZona (long idZonaOld, String descZonaNueva) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public void ingresarCodZona (String descZona) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;	
	public void eliminarCodZona (long idZona) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	
	public List<CodZona> obtenerCodZona(String descZona);
	public List<CodZona> obtenerCodZonaLike(String descZona);
	public List<CodZona> obtenerCodZona(long idZona);
	public List<CodZona> obtenerCodZona();
	
}
