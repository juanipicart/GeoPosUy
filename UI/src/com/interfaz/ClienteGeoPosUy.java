package com.interfaz;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.DefaultListModel;

import com.beans.FenomenosBeanRemote;
import com.beans.LoginBeanRemote;
import com.beans.ObservacionesBeanRemote;
import com.beans.UbicacionesBeanRemote;
import com.beans.UsuarioBeanRemote;
import com.clases.Fenomeno;
import com.clases.Observacion;
import com.clases.Usuario;
import com.clases.codigueras.Departamento;
import com.clases.codigueras.Localidad;
import com.clases.codigueras.Rol;
import com.clases.codigueras.Zona;
import com.clases.codigueras.Estado;
import com.clases.codigueras.TipoDocumento;
import com.exceptions.NoValidaParamException;
import com.exceptions.ProblemasNivelSQLException;



public class ClienteGeoPosUy {

	private static String JNDI_USUARIO = "GeoPosUy/com.beans/UsuariosBean!servicios.remote.UsuariosBeanRemote";
	private static String JNDI_FENOMENO = "GeoPosUy/com.beans/FenomenosBean!servicios.remote.FenomenosBeanRemote";
	private static String JNDI_UBICACION = "GeoPosUy/com.beans/UbicacionesBean!servicios.remote.UbicacionesBeanRemote";
	private static String JNDI_LOGIN = "GeoPosUy/com.beans/LoginBean!servicios.remote.LoginBeanRemote";
	private static String JNDI_ROL = "GeoPosUy/com.beans/RolBean!servicios.remote.RolBeanRemote";
	

	

	// Operaciones sobre Usuarios 
	 
	
	public static boolean registrarUsuario(String username, String nombre, String apellido, String direccion, long rol, long localidad, 
			long zona, long estado, long depto, String correo, String password, long tipodoc, String documento) throws Exception {
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.registrarUsuario(username, nombre, apellido, direccion, rol, localidad, zona, estado, depto, correo, password, tipodoc, documento);
	}
	
	public static Usuario buscarUsuarioPorDocumento(long codTipoDoc, String documento) throws Exception {
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.buscarUsuarioPorDocumento(codTipoDoc, documento);
	}
	
	public static Usuario buscarUsuarioPorUsername(String username) throws Exception {
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.buscarUsuarioPorUsername(username);
	}

	public static boolean modificarUsuario(String username, String nombre, String apellido, String direccion, String password, String documento , long estado, long rol, long tipoDoc, String correo, long zona, long departamento, long localidad)
			 throws Exception {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.modificarUsuario(username,  nombre,  apellido,  direccion,  password,
				 documento,  estado,  rol,  tipoDoc,  correo,  zona, departamento, localidad);
	}
	
	public static boolean darDeBajaUsuario(String username) throws NamingException {

		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.eliminarUsuario(username);		
	}

	public static boolean validarCedula(String cedula) throws NamingException {
		
		UsuarioBeanRemote usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarioBeanRemote.validarCedula(cedula);	
	}
	
	public static boolean validarLogin(String username, String password) throws Exception {

		LoginBeanRemote LoginBeanRemote = EJBLocator.getInstance().lookup(LoginBeanRemote.class);
		
		return LoginBeanRemote.verificarLogin(username, password);
	}
	
	/*** Operaciones sobre Ubicaciones ***/
	
	public static List<Zona> obtenerZonas()throws Exception {
		
		UbicacionesBeanRemote ubicaciones = EJBLocator.getInstance().lookup(UbicacionesBeanRemote.class);
		return ubicaciones.retornarZonas();
	}
	
	public static List<Localidad> obtenerLocalidades() throws Exception {

		UbicacionesBeanRemote ubicaciones = EJBLocator.getInstance().lookup(UbicacionesBeanRemote.class);
		return ubicaciones.retornarLocalidades();
	}

	public static List<Localidad> obtenerLocalidadesPorDepto(long depto) throws Exception {

		UbicacionesBeanRemote ubicaciones = EJBLocator.getInstance().lookup(UbicacionesBeanRemote.class);
		return ubicaciones.retornarLocalidadesPorDepto(depto);
	}
	
	public static List<Departamento> obtenerDepartamentos() throws Exception {

		UbicacionesBeanRemote ubicaciones = EJBLocator.getInstance().lookup(UbicacionesBeanRemote.class);
		return ubicaciones.retornarDepartamentos();
	}

	
	/*** Operaciones sobre Roles ***/

	

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*METODOS PARA FENÓMENOS*/
	
	public static boolean crearFenomeno(String codigo, String nombre, String desc, String contacto) throws Exception {
		
	FenomenosBeanRemote	fenomenoBeanRemote = EJBLocator.getInstance().lookup(FenomenosBeanRemote.class);
	return fenomenoBeanRemote.crearFenomeno(codigo, nombre, desc, contacto);
	
	}

	public static boolean ExisteFenomeno(String nombre) throws Exception {

	FenomenosBeanRemote fenomenoBeanRemote = EJBLocator.getInstance().lookup(FenomenosBeanRemote.class);
	return fenomenoBeanRemote.existeFenomeno(nombre);

	}
	
	public static boolean EliminarFenomeno(String codigo) throws Exception {

	FenomenosBeanRemote fenomenoBeanRemote = EJBLocator.getInstance().lookup(FenomenosBeanRemote.class);
	return fenomenoBeanRemote.eliminarFenomeno(codigo);

	}
	
	public static Fenomeno BuscarFenomenoPorCodigo(String codigo) throws Exception {

	FenomenosBeanRemote fenomenoBeanRemote = EJBLocator.getInstance().lookup(FenomenosBeanRemote.class);
	return fenomenoBeanRemote.buscarFenomenoPorCodigo(codigo);

	}
	
	public static List<Fenomeno> ObtenerTodosLosFenomenos() throws Exception {

	FenomenosBeanRemote fenomenoBeanRemote = EJBLocator.getInstance().lookup(FenomenosBeanRemote.class);
	return fenomenoBeanRemote.obtenerTodosLosFenomenos();

	}
	
	public static boolean modificarFenomeno (String codigo, String nombre, String desc, String contacto) throws Exception {
		
	FenomenosBeanRemote	fenomenoBeanRemote = EJBLocator.getInstance().lookup(FenomenosBeanRemote.class);
	return fenomenoBeanRemote.modificarFenomeno(codigo, nombre, desc, contacto);
	
	}
	
	public static DefaultListModel<Fenomeno> obtenerComboFenomenos() throws NamingException {	
		FenomenosBeanRemote fenomenoBeanRemote = EJBLocator.getInstance().lookup(FenomenosBeanRemote.class);	
		return fenomenoBeanRemote.consultarFenomenos();	
	}
		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*METODOS PARA OBSERVACIONES*/

	public static boolean ExisteObservacion(String codigo) throws Exception {

	ObservacionesBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionesBeanRemote.class);
	return observacionesBeanRemote.existeObservacionPorFenomeno(codigo);

		}

	public static List<Observacion> buscarObservacionesPorFenomenos(LinkedList<Long> codigo) throws Exception {	
		ObservacionesBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionesBeanRemote.class);	
		return observacionesBeanRemote.buscarObservacionesPorFenomenos(codigo);	
			}
	
	public static boolean validarLatitudLongitud(String latitudLongitud) throws NamingException, NoValidaParamException {
		ObservacionesBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionesBeanRemote.class);
		return observacionesBeanRemote.validarLatLong(latitudLongitud);
	}
	
	public static List<String> validarPalabrasProhibidas(String texto) throws SQLException, ProblemasNivelSQLException, NamingException {
		ObservacionesBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionesBeanRemote.class);
		return observacionesBeanRemote.contienePalabrasProhibidas(texto);
	}
	
	public static long obtenerNextVal() throws SQLException, NamingException {
		ObservacionesBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionesBeanRemote.class);
		return observacionesBeanRemote.obtenerNextVal();
	}
	
	public static int obtenerCriticidad(String nivel) throws NamingException {
		ObservacionesBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionesBeanRemote.class);
		return observacionesBeanRemote.obtenerCriticidad(nivel);
	}
	
	public static boolean RegistrarObservacion(Observacion obs) throws Exception {

		ObservacionesBeanRemote observacionesBeanRemote = EJBLocator.getInstance().lookup(ObservacionesBeanRemote.class);
		return observacionesBeanRemote.ingresarObservacion(obs);

			}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*MÉTODOS PARA CARGAR LOS COMBOS DE USUARIO*/
	
	public static List<TipoDocumento> obtenerTiposDoc() throws NamingException {
		UsuarioBeanRemote usuarios = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarios.retornarTiposDocumento();
	}

	public static List<Rol> obtenerRoles() throws Exception {

		UsuarioBeanRemote usuarios = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarios.retornarRoles();

	}

	public static List<Estado> obtenerEstado() throws NamingException {
		
		UsuarioBeanRemote usuarios = EJBLocator.getInstance().lookup(UsuarioBeanRemote.class);
		return usuarios.retornarEstado();
	}


	
}
	


