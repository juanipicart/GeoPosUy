	package com.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.swing.DefaultListModel;

import com.clases.Fenomeno;
import com.dao.FenomenoDao;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Stateful
public class FenomenosBean implements FenomenosBeanRemote {
	
	@EJB
	private FenomenoDao servicio;
	
	@Override
    public boolean crearFenomeno( String codigo, String nombre, String descripcion, String contacto) {
    	
    	boolean pudeCrear = false; 
    	Fenomeno fenomeno = new Fenomeno(codigo, nombre, descripcion, contacto);
    	fenomeno.setId_fenomeno(0);
    	
    	try {
    		servicio.registrarFenomeno(fenomeno);
    		pudeCrear = true;
    	} catch (ProblemasNivelSQLException | NoSeRealizoOperacionException e) {
    		pudeCrear = false;
    	}
    	
    	return pudeCrear;
    }
    
	@Override
    public boolean existeFenomeno(String nombre) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
    	
    	boolean existe;
    	Fenomeno fenomeno = servicio.selectFenomenoPorNombre(nombre);
    	
    	if (fenomeno == null) {
    		existe = false;
    	} else {
    		existe = true;
    	} return existe;
    }
	
	@Override
	public boolean modificarFenomeno(String codigo, String nombre, String descripcion, String contacto) throws NoSeRealizoOperacionException, ProblemasNivelSQLException {
		Fenomeno fenomeno = new Fenomeno(codigo, nombre, descripcion, contacto);
		
		boolean pudeActualizar = false;
		try {
			servicio.actualizarFenomeno(fenomeno);
			pudeActualizar = true;
		} catch (ProblemasNivelSQLException e) {
    		throw new ProblemasNivelSQLException("actualizar fenómeno");
		} return pudeActualizar;
	}
	
	@Override
	public Fenomeno buscarFenomenoPorCodigo(String codigo) {
		
		Fenomeno fenomeno = null;
		try {
			fenomeno = servicio.selectFenomenoPorCodigo(codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fenomeno;
	}

	@Override
	public boolean eliminarFenomeno(String codigo) {

		boolean logrado = false;
		
		try {
			
			return logrado = servicio.eliminarFenomeno(codigo);
			
		} catch (ProblemasNivelSQLException | NoSeRealizoOperacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logrado;
		
	}	
	@Override	
	public DefaultListModel<Fenomeno> consultarFenomenos() {	
			
    	DefaultListModel<Fenomeno> fenomenos = null;	
		try {	
			fenomenos = servicio.consultarFenomenos();	
		} catch (ProblemasNivelSQLException e) {	
			// TODO Auto-generated catch block	
			e.printStackTrace();	
		} catch (NoSeRealizoOperacionException e) {	
			// TODO Auto-generated catch block	
			e.printStackTrace();	
		} catch (Exception e) {	
			// TODO Auto-generated catch block	
			e.printStackTrace();	
		}	
    		
    	if (fenomenos != null) {	
    		return fenomenos;	
    	} else {	
    		return null;	
    	}  
	}

	@Override
	public List<Fenomeno> obtenerTodosLosFenomenos() {
		
		
    	List<Fenomeno> fenomenos = null;	
		try {	
			fenomenos = servicio.obtenerTodosLosFenomenos();	
		} catch (ProblemasNivelSQLException e) {	
			// TODO Auto-generated catch block	
			e.printStackTrace();	
		} catch (NoSeRealizoOperacionException e) {	
			// TODO Auto-generated catch block	
			e.printStackTrace();	
		} catch (Exception e) {	
			// TODO Auto-generated catch block	
			e.printStackTrace();	
		}	
    		
    	if (fenomenos != null) {	
    		return fenomenos;	
    	} else {	
    		return null;	
    	}  
    	
	}


}
