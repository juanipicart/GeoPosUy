package com.dao;

import java.util.List;

import javax.ejb.Remote;

import com.clases.Usuario;
import com.clases.codigueras.Estado;
import com.clases.codigueras.Rol;
import com.clases.codigueras.TipoDocumento;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface UsuarioDao {
	
	public void registrarUsuario(Usuario usuario) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean eliminarUsuario(String username) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean modificarUsuario(Usuario usu) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public List<TipoDocumento> obtenerTiposDocumento();
	public List<Rol> obtenerRoles();
	public List<Estado> obtenerEstados();
	public Usuario buscarUsuarioPorUsername(String username) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public Usuario buscarUsuarioPorDocumento(long codTipoDoc, String documento) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;
	public boolean validarCedula(String cedula);
	public Usuario buscarUsuarioPorId(long IdUser) throws ProblemasNivelSQLException;
}
