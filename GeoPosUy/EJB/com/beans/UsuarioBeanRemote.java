package com.beans;

import java.util.List;

import javax.ejb.Remote;

import com.clases.Usuario;
import com.clases.codigueras.Estado;
import com.clases.codigueras.Rol;
import com.clases.codigueras.TipoDocumento;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface UsuarioBeanRemote {

	boolean ExisteUsuario(String username) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;

	Usuario buscarUsuarioPorUsername(String username) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	Usuario buscarUsuarioPorDocumento(long codTipoDoc, String documento) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean modificarUsuario(String username, String nombre, String apellido, String direccion, String password, String documento , long estado, long rol, long tipoDoc, String correo, long zona, long departamento, long localidad);

	boolean eliminarUsuario(String username);

	boolean registrarUsuario(String usuario, String nombre, String apellido, String direccion, long rol, long localidad,
			long zona, long estado, long departamento, String correo, String password, long tipodoc, String documento);

	public List<TipoDocumento> retornarTiposDocumento();
	public List<Rol> retornarRoles();
	public List<Estado> retornarEstado();
	public boolean validarCedula(String cedula);

}
