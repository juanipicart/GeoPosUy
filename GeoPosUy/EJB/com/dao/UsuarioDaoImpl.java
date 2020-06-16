package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.bd.DBConector;
import com.clases.Usuario;
import com.clases.codigueras.Estado;
import com.clases.codigueras.Rol;
import com.clases.codigueras.TipoDocumento;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Stateless
@LocalBean
public class UsuarioDaoImpl implements UsuarioDao {
	
	DBConector bd = DBConector.getinstance();
	
	private static final String crearUsuario = "INSERT INTO USUARIOS (USUARIO,NOMBRE,APELLIDO,DIRECCION,ROL,LOCALIDAD,ZONA,ESTADO,DEPARTAMENTO,MAIL,PASSWORD,TIPO_DOC,DOCUMENTO) "
			+ "VALUES (UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),?,UPPER(?),UPPER(?))";
	
	
	
	
	@Override
	public void registrarUsuario(Usuario usuario) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		
		int i;
		
		// se prepara el insert
		bd.setPrepStmt(crearUsuario);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, usuario.getUsuario().toUpperCase());
			bd.getPrepStmt().setString(2, usuario.getNombre().toUpperCase());
			bd.getPrepStmt().setString(3, usuario.getApellido().toUpperCase());
			bd.getPrepStmt().setString(4, usuario.getDireccion().toUpperCase());
			bd.getPrepStmt().setLong(5, usuario.getRol());
			bd.getPrepStmt().setLong(6, usuario.getLocalidad());		
			bd.getPrepStmt().setLong(7, usuario.getZona());	
			bd.getPrepStmt().setLong(8, usuario.getEstado());	
			bd.getPrepStmt().setLong(9, usuario.getDepartamento());
			bd.getPrepStmt().setString(10, usuario.getCorreo());	
			bd.getPrepStmt().setString(11, usuario.getPassword());
			bd.getPrepStmt().setLong(12, usuario.getTipodoc());	
			bd.getPrepStmt().setString(13, usuario.getDocumento());	
	
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		
		i= bd.execDML();		
		
		// ejecutamos y controlamos retorno
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Insertar usuario");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Insertar usuario");
		} else
			System.out.println("Se ingresaron ["+i+"] registros");	
		
	}
	
	@Override
	public boolean modificarUsuario(Usuario usu) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		String UPD_USU = "Update USUARIOS set NOMBRE = UPPER(?), APELLIDO = UPPER(?), DIRECCION = UPPER(?), ROL = ?, LOCALIDAD = ?, ZONA = ?, "
				+ "ESTADO = ?, DEPARTAMENTO = ?, MAIL = UPPER(?), PASSWORD = ?, TIPO_DOC = ?, DOCUMENTO = UPPER(?) WHERE USUARIO = UPPER(?)";
		int i;
		boolean exito = false;
		// se prepara el insert
		bd.setPrepStmt(UPD_USU);
		
		//El asignar una variable puede lanzar una excepcion de SQL
		try {
			bd.getPrepStmt().setString(1, usu.getNombre());
			bd.getPrepStmt().setString(2, usu.getApellido().toUpperCase());
			bd.getPrepStmt().setString(3, usu.getDireccion().toUpperCase());
			bd.getPrepStmt().setLong(4, usu.getRol());
			bd.getPrepStmt().setLong(5, usu.getLocalidad());
			bd.getPrepStmt().setLong(6, usu.getZona());
			bd.getPrepStmt().setLong(7, usu.getEstado());
			bd.getPrepStmt().setLong(8, usu.getDepartamento());
			bd.getPrepStmt().setString(9, usu.getCorreo());
			bd.getPrepStmt().setString(10, usu.getPassword());
			bd.getPrepStmt().setLong(11, usu.getTipodoc());
			bd.getPrepStmt().setString(12, usu.getDocumento());
			bd.getPrepStmt().setString(13, usu.getUsuario());
			
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException(e.getMessage());
		}
		i= bd.execDML();
		
		if ( i == 0) {
			throw new NoSeRealizoOperacionException("Modificar usuario");
		} else if (i < 0) {
			throw new  ProblemasNivelSQLException("Modificar usuario");
		} else {
			System.out.println("Se modificaron ["+i+"] registros");	
		exito = true;
		}
		return exito;
	}
	
	@Override
	public boolean chequearSiExisteUsuario(String username) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		final String selectUsuarioPorUsername = "SELECT * FROM USUARIOS WHERE USUARIO = ?";
		boolean existe = false;
		bd.setPrepStmt(selectUsuarioPorUsername);
		
		try {
			bd.getPrepStmt().setString(1, username);
			bd.execQry();
			
			ResultSet resultado = bd.getResultSet();
			
			while (resultado.next()) {
				existe = true;
			}
			return existe;
			
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException("realizar búsqueda");
		}
	}
	
	@Override
	public Usuario buscarUsuarioPorUsername(String username) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		final String selectUsuarioPorUsername = "SELECT * FROM USUARIOS WHERE USUARIO = UPPER(?)";
		bd.setPrepStmt(selectUsuarioPorUsername);
		Usuario usuario = null;
		try {
			bd.getPrepStmt().setString(1, username);
			bd.execQry();
			
			ResultSet resultado = bd.getResultSet();
			while (resultado.next()) {				
				usuario = getUsuarioDesdeResultado(resultado);
			}
			return usuario;
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException("realizar búsqueda");
		}
	}
	
	@Override
	public Usuario buscarUsuarioPorDocumento(long codTipoDoc, String documento) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
		final String selectUsuarioPorDocumento = "SELECT * FROM USUARIOS WHERE TIPO_DOC=? AND DOCUMENTO=UPPER(?)";
		bd.setPrepStmt(selectUsuarioPorDocumento);
		Usuario usuario = null;
		try {
			bd.getPrepStmt().setLong(1, codTipoDoc);
			bd.getPrepStmt().setString(2, documento);
			bd.execQry();
			
			ResultSet resultado = bd.getResultSet();
			while (resultado.next()) {				
				usuario = getUsuarioDesdeResultado(resultado);
			}
			return usuario;
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException("realizar búsqueda");
		}
	}
	
	private static Usuario getUsuarioDesdeResultado(ResultSet resultado) throws ProblemasNivelSQLException {
		
		try {		
			long id_user = resultado.getLong("ID_USER");
			String usuario = resultado.getString("USUARIO");
			String nombre = resultado.getString("NOMBRE");
			String apellido = resultado.getString("APELLIDO");
			String direccion = resultado.getString("DIRECCION");
			String correo = resultado.getString("MAIL");
			String documento = resultado.getString("DOCUMENTO");
			String password = resultado.getString("PASSWORD");
			int tipodoc = resultado.getInt("TIPO_DOC");
			int localidad = resultado.getInt("LOCALIDAD");
			int departamento = resultado.getInt("DEPARTAMENTO");
			int zona = resultado.getInt("ZONA");
			int rol = resultado.getInt("ROL");
			int estado = resultado.getInt("ESTADO");
			
			Usuario user = new Usuario(id_user, usuario, nombre, apellido, direccion, correo, password, estado, tipodoc, documento, rol, departamento, localidad, zona);
			
			return user; 
		} catch (SQLException e) {
			throw new ProblemasNivelSQLException("realizar operación");
			
		}
	}

@Override
	public boolean eliminarUsuario(String username) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
	
	String DEL_USU = "UPDATE USUARIOS SET ESTADO=2 where USUARIO= ?";
	int i;
	boolean logrado = false;
	
	bd.setPrepStmt(DEL_USU);
	

	try {
		bd.getPrepStmt().setString(1, username);			
	} catch (SQLException e) {
		throw new ProblemasNivelSQLException(e.getMessage());
	}
	i= bd.execDML();
	
	// ejecutamos y controlamos retorno
	if ( i == 0) {
		throw new NoSeRealizoOperacionException("Eliminar usuario");
	} else if (i < 0) {
		throw new  ProblemasNivelSQLException("Eliminar usuario");
	} else {
		System.out.println("Se eliminaron ["+i+"] registros");
	logrado = true;
	}
	
	return logrado;	
}

	@Override
	public List<TipoDocumento> obtenerTiposDocumento() {
		
		String TIPOS_DOCUMENTO = "SELECT * FROM USUARIOS_TIPOS_DOC";
		
		ArrayList<TipoDocumento> list = new ArrayList<>();	
		
		try {
			bd.execStmt(TIPOS_DOCUMENTO);
			
			while ( bd.getResultSet().next()) {
				TipoDocumento dto = new TipoDocumento();
				dto.setID_TIPO_DOC(bd.getResultSet().getLong(1));
				dto.setDescripcion(bd.getResultSet().getString(2));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar tipos de documento -->" + e.getMessage());
			e.printStackTrace();
		}		
		return list;	
		}

	@Override
	public List<Rol> obtenerRoles() {
		
		String ROLES = "SELECT * FROM ROLES_USUARIOS";
		
		ArrayList<Rol> list = new ArrayList<>();	
		
		try {
			bd.execStmt(ROLES);
			
			while ( bd.getResultSet().next()) {
				Rol rol = new Rol();
				rol.setId_rol(bd.getResultSet().getLong(1));
				rol.setDescripcion_rol(bd.getResultSet().getString(2));
				list.add(rol);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar tipos de documento -->" + e.getMessage());
			e.printStackTrace();
		}		
		return list;
	}

	@Override
	public List<Estado> obtenerEstados() {

		String ESTADOS = "SELECT * FROM USUARIOS_ESTADOS";
		
		ArrayList<Estado> list = new ArrayList<>();	
		
		try {
			bd.execStmt(ESTADOS);
			
			while ( bd.getResultSet().next()) {
				Estado estado = new Estado();
				estado.setId_estado(bd.getResultSet().getLong(1));
				estado.setDesc_estado(bd.getResultSet().getString(2));
				list.add(estado);
			}
		} catch (SQLException e) {
			System.out.println("Excepcion al consultar tipos de documento -->" + e.getMessage());
			e.printStackTrace();
		}		
		return list;
	}

	public boolean validarCedula(String cedula) {
		boolean cedulaValida = false;
		int value = 0;
		int digitoVerificador = 0;
		
		//Si la cedula es de largo 7, le agrego un cero al principio
		if (cedula.length() == 7) {
			cedula = '0' + cedula;
		}
		
		//Separo la cedula por números y los agrego a un array
		String[] digitos = cedula.split("");
		String[] validador = "2987634".split("");
		
		for (int i = 0; i < cedula.length() - 1; i++) {
			value = value + Integer.parseInt(digitos[i])*Integer.parseInt(validador[i]); 
		}
		
		if (value % 10 == 0) {
			digitoVerificador = 0;
		} else {
			digitoVerificador = 10 - (value % 10);
		}
		
		if (Integer.parseInt(digitos[cedula.length() - 1]) == digitoVerificador) {
			cedulaValida = true;
		}
		
		return cedulaValida;
	}
}