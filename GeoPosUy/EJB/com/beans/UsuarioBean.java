package com.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import com.clases.Usuario;
import com.clases.codigueras.CodDepartamento;
import com.clases.codigueras.Estado;
import com.clases.codigueras.Rol;
import com.clases.codigueras.TipoDocumento;
import com.clases.relaciones.RelUbicacion;
import com.dao.UsuarioDao;
import com.dao.UsuarioDaoImpl;
import com.dao.ubicaciones.CodDepartamentosDaoImpl;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
public class UsuarioBean implements UsuarioBeanRemote {

	@EJB
	private UsuarioDao servicio;	
	
    public UsuarioBean() {    	
    
    }
    
	@Override
	public boolean registrarUsuario(String usuario, String nombre, String apellido, String direccion, long rol, long localidad,
			long zona, long estado, long departamento, String correo, String password, long tipodoc, String documento) {
		
		boolean registroExitoso = false;
		
		Usuario user = new Usuario(usuario, nombre, apellido, direccion, correo, password, estado, tipodoc, documento, rol, departamento, localidad, zona);	
		
		try {
				servicio.registrarUsuario(user);
				registroExitoso = true;
		} catch (ProblemasNivelSQLException | NoSeRealizoOperacionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		return (registroExitoso);
	}
	
	
	
	@Override
	public boolean modificarUsuario(String username, String nombre, String apellido, String direccion, String password, String documento , long estado, long rol, long tipoDoc, String correo, long zona, long departamento, long localidad) {
		boolean modifico = false;
		
		Usuario usuario = new Usuario(username, nombre, apellido, direccion, correo, password, estado, tipoDoc, documento, rol, departamento, localidad, zona);
		
		try {
			
			modifico = servicio.modificarUsuario(usuario);
			
				
		} catch (ProblemasNivelSQLException | NoSeRealizoOperacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modifico;
	}
	
	
	@Override
    public boolean ExisteUsuario(String username) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		
    	boolean existe = servicio.chequearSiExisteUsuario(username);
    	
    	return existe;
    }
	
	@Override
    public Usuario buscarUsuarioPorUsername(String username) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		
		Usuario usu = new Usuario();
		
    	usu = servicio.buscarUsuarioPorUsername(username);
    	   	
    	return usu;
    }
	
	 public Usuario buscarUsuarioPorDocumento(long codTipoDoc, String documento) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
			
			Usuario usu = new Usuario();
			
	    	usu = servicio.buscarUsuarioPorDocumento(codTipoDoc, documento);
	    	   	
	    	return usu;
	    }

	@Override
	public boolean eliminarUsuario(String username) {

		boolean logrado = false;
		
		try {
			
			return logrado = servicio.eliminarUsuario(username);
			
		} catch (ProblemasNivelSQLException | NoSeRealizoOperacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logrado;
	}
	
	@Override
	public List<TipoDocumento> retornarTiposDocumento() {
		return servicio.obtenerTiposDocumento();
	}

	@Override
	public List<Rol> retornarRoles() {
		return servicio.obtenerRoles();
	}

	@Override
	public List<Estado> retornarEstado() {
		return servicio.obtenerEstados();
	}

	@Override
	public boolean validarCedula(String cedula) {
		return servicio.validarCedula(cedula);
	}
	
}
