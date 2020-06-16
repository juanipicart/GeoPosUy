package com.clases;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	long id_user;
	String usuario; //max 15 not null
	String nombre; //max 30 not null
	String apellido; //max 30 not null
	String direccion; //max 60 not null
	String correo;
	String password;
	long estado;
	long tipodoc;
	String documento;
	long rol; //not null
	long departamento;
	long localidad;
	long zona;
	

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public long getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(int tipodoc) {
		this.tipodoc = tipodoc;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public long getRol() {
		return rol;
	}
	
	public void setRol(int rol) {
		this.rol = rol;
	}

	public long getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public long getLocalidad() {
		return localidad;
	}

	public void setLocalidad(int localidad) {
		this.localidad = localidad;
	}

	public long getZona() {
		return zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}

	
	
	public Usuario(long id_user, String usuario, String nombre, String apellido, String direccion, String correo,
			String password, int estado, int tipodoc, String documento, int rol, int departamento, int localidad,
			int zona) {
		
		this.id_user = id_user;
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.password = password;
		this.estado = estado;
		this.tipodoc = tipodoc;
		this.documento = documento;
		this.rol = rol;
		this.departamento = departamento;
		this.localidad = localidad;
		this.zona = zona;
	}
	
	
	public Usuario(String usuario, String nombre, String apellido, String direccion, String correo, String password,
			long estado, long tipodoc, String documento, long rol, long departamento, long localidad, long zona) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.password = password;
		this.estado = estado;
		this.tipodoc = tipodoc;
		this.documento = documento;
		this.rol = rol;
		this.departamento = departamento;
		this.localidad = localidad;
		this.zona = zona;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}




}
