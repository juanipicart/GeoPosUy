package com.java.swing;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.clases.Usuario;
import com.clases.codigueras.CodDepartamento;
import com.clases.codigueras.CodLocalidad;
import com.clases.codigueras.Rol;
import com.clases.codigueras.TipoDocumento;
import com.clases.codigueras.CodZona;
import com.clases.codigueras.Estado;
import com.interfaz.ClienteGeoPosUy;

public class FrameModificarUsuario implements ActionListener {
	
	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelUsername;
	private JLabel labelNombre;
	private JLabel labelApellido;
	private JLabel labelTipoDoc;
	private JLabel labelDoc;
	private JLabel labelDireccion;
	private JLabel labelDepto;
	private JLabel labelLocalidad;
	private JLabel labelZona;
	private JLabel labelRol;
	private JLabel labelCorreo;
	private JLabel labelPassword;
	private JLabel labelEstado;

	/** Atributos de TexField */
	private JTextField textUsername;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDoc;
	private JTextField textDireccion;
	private JTextField textPassword;
	private JTextField textCorreo;
	
	/*Atributos de Combobox*/
	private JComboBox<String> comboTipoDoc;
	private JComboBox<String> comboDepto;
	private JComboBox<String> comboLocalidad;
	private JComboBox<String> comboZona;
	private JComboBox<String> comboRol;	
	private JComboBox<String> comboEstado;
	
	/** Atributos de Botones */
	private JButton buttonRegistrar;
	private JButton buttonCancelar;

	private Map<String, Long> mapTiposDoc;
	private Map< String,  Long> mapDeptos;
	private Map< String,  Long> mapLocs;
	private Map< String,  Long> mapEstados;
	private Map< String,  Long> mapRoles;
	private Map< String,  Long> mapZonas;
	
	private Map< Long, String> mapTiposDocReverse;
	private Map< Long, String> mapDeptosReverse;
	private Map< Long, String> mapLocsReverse;
	private Map< Long, String> mapEstadosReverse;
	private Map< Long, String> mapRolesReverse;
	private Map< Long, String> mapZonasReverse;
	
	private Usuario user;
	
	public FrameModificarUsuario(JFrame framePadre, Usuario usuario) {

		this.user = usuario;
		this.labelApellido = new JLabel("Apellido:");
		this.labelNombre = new JLabel("Nombre:");
		this.labelTipoDoc = new JLabel("Tipo de documento:");
		this.labelDoc = new JLabel("Documento:");
		this.labelDireccion = new JLabel("Dirección:");
		this.labelUsername = new JLabel("Username:");
		this.labelDepto = new JLabel("Departamento:");
		this.labelLocalidad = new JLabel("Localidad:");
		this.labelZona = new JLabel("Zona:");
		this.labelRol = new JLabel("Rol:");
		this.labelPassword = new JLabel("Contraseña:");
		this.labelCorreo = new JLabel("Correo:");
		this.labelEstado = new JLabel("Estado:");
		
		this.textDoc = new JTextField(30);
		this.textNombre = new JTextField(30);
		this.textApellido = new JTextField(30);
		this.textDireccion = new JTextField(30);
		this.textUsername = new JTextField(30);
		this.textPassword = new JTextField(30);
		this.textCorreo = new JTextField(30);
		
		
		
		JButton buttonRegistrar = new JButton("Modificar Usuario");
		buttonRegistrar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar registro");
		buttonCancelar.addActionListener(this);

		this.buttonRegistrar = buttonRegistrar;
		this.buttonCancelar = buttonCancelar;

		this.initializeFrame(framePadre, usuario);
	}

	private void initializeFrame(JFrame framePadre, Usuario usuario) {

		JFrame frame = new JFrame("Modificar Usuario");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevoUsuarioPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;		
		nuevoUsuarioPanel.add(this.labelUsername,constraints);
		
		constraints.gridx = 1;
		this.textUsername.setText(usuario.getUsuario());
		this.textUsername.setEnabled(false);
		nuevoUsuarioPanel.add(this.textUsername, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevoUsuarioPanel.add(this.labelPassword, constraints);
		
		constraints.gridx = 1;
		this.textPassword.setText(usuario.getPassword());
		nuevoUsuarioPanel.add(this.textPassword, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevoUsuarioPanel.add(this.labelNombre, constraints);

		constraints.gridx = 1;
		this.textNombre.setText(usuario.getNombre());
		nuevoUsuarioPanel.add(this.textNombre, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevoUsuarioPanel.add(this.labelApellido, constraints);

		constraints.gridx = 1;
		this.textApellido.setText(usuario.getApellido());
		nuevoUsuarioPanel.add(this.textApellido, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		nuevoUsuarioPanel.add(this.labelTipoDoc, constraints);
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 4;
			this.comboTipoDoc = cargarComboTiposDoc();
			this.comboTipoDoc.setSelectedItem(mapTiposDocReverse.get(usuario.getTipodoc()));			
			nuevoUsuarioPanel.add(this.comboTipoDoc, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		nuevoUsuarioPanel.add(this.labelDoc, constraints);

		constraints.gridx = 1;
		this.textDoc.setText(user.getDocumento());
		nuevoUsuarioPanel.add(this.textDoc, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		nuevoUsuarioPanel.add(this.labelDireccion, constraints);

		constraints.gridx = 1;
		this.textDireccion.setText(user.getDireccion());
		nuevoUsuarioPanel.add(this.textDireccion, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		nuevoUsuarioPanel.add(this.labelCorreo, constraints);
		
		constraints.gridx = 1;
		this.textCorreo.setText(user.getCorreo());
		nuevoUsuarioPanel.add(this.textCorreo, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 8;
		nuevoUsuarioPanel.add(this.labelZona, constraints);
		
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 8;			
			this.comboZona = cargarComboZonas();
			this.comboZona.setSelectedItem(mapZonasReverse.get(user.getZona()));
			nuevoUsuarioPanel.add(this.comboZona, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 9;
		nuevoUsuarioPanel.add(this.labelLocalidad, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 9;
			this.comboLocalidad = cargarComboLocalidad();
			this.comboLocalidad.setSelectedItem(mapLocsReverse.get(user.getLocalidad()));
			nuevoUsuarioPanel.add(this.comboLocalidad, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 10;
		nuevoUsuarioPanel.add(this.labelDepto, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 10;
			this.comboDepto = cargarComboDepartamento();
			this.comboDepto.setSelectedItem(mapDeptosReverse.get(user.getDepartamento()));
			nuevoUsuarioPanel.add(this.comboDepto, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 11;
		nuevoUsuarioPanel.add(this.labelRol, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 11;
			this.comboRol = cargarComboRol();
			this.comboRol.setSelectedItem(mapRolesReverse.get(user.getRol()));
			nuevoUsuarioPanel.add(this.comboRol, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 12;
		nuevoUsuarioPanel.add(this.labelEstado, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 12;
			this.comboEstado = cargarComboEstados();
			this.comboEstado.setEnabled(false);
			this.comboEstado.setSelectedItem(mapEstadosReverse.get(user.getEstado()));
			nuevoUsuarioPanel.add(this.comboEstado, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		buttonPanel.add(buttonRegistrar);
		buttonPanel.add(buttonCancelar);
		
		nuevoUsuarioPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del usuario"));

		frame.add(nuevoUsuarioPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}

	/**
	 * Como implementos Action Listener, quiere decir que soy escuchado de
	 * eventos. Este método es quien se ejecutan cuando tocan un boton .
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		/* Debo primero conocer que botón fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		}	else {
			this.accionIngresar();
		}
	}
	

	private void accionIngresar() {

		// Si es ingresar se validan datos!
		
		String fieldNombre = this.textNombre.getText();
		String fieldApellido = this.textApellido.getText();
		String fieldPassword = this.textPassword.getText();
		String fieldDoc = this.textDoc.getText();
		String fieldDireccion = this.textDireccion.getText();
		String fieldUsername = this.textUsername.getText();
		String fieldCorreo = this.textCorreo.getText();
		String tipoDoc = (String) comboTipoDoc.getSelectedItem();
		String depto =  (String) comboDepto.getSelectedItem();
		String zona =  (String) comboZona.getSelectedItem();
		String localidad =  (String) comboLocalidad.getSelectedItem();
		String rol =  (String) comboRol.getSelectedItem();
		String estado =  (String) comboEstado.getSelectedItem();
		

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldApellido.equals("") || fieldDoc.equals("") || fieldDireccion.equals("") || fieldUsername.equals("") || fieldPassword.equals("") || tipoDoc.equals("") || depto.equals("") || zona.equals("") || localidad.equals("") || rol.equals("") || estado.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return; }
		
		if (tipoDoc.equals("CI")) {
			try {
				if (!fieldDoc.matches("[0-9]+"))  {
					JOptionPane.showMessageDialog(frame, "Ingrese la cédula sin puntos ni guiones", "Cédula inválida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else if (fieldDoc.length()>8 || fieldDoc.length()<7 || !ClienteGeoPosUy.validarCedula(fieldDoc)) {
					JOptionPane.showMessageDialog(frame, "La cédula ingresada no es válida", "Cédula inválida!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} 
		} 
		
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		
		Matcher matcher = pattern.matcher(fieldCorreo);
		
	
		if (!matcher.matches()) {
			JOptionPane.showMessageDialog(frame, "El formato del correo no es correcto", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		//Chequeo si modifique los campos tipo y numero de documento.
		
		System.out.println(user.getDocumento().equalsIgnoreCase(fieldDoc));
		System.out.println((user.getTipodoc()  == mapTiposDoc.get(tipoDoc)));
		
		if (!(user.getDocumento().equalsIgnoreCase(fieldDoc)) || !(user.getTipodoc()  == mapTiposDoc.get(tipoDoc))) {
		
		//Si los modifique me fijo si ya existe un usuario con esos datos	
		Usuario usuarioExiste = null;
		try {
			usuarioExiste = ClienteGeoPosUy.buscarUsuarioPorDocumento(mapTiposDoc.get(tipoDoc), fieldDoc);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (!(usuarioExiste == null) ) {
			JOptionPane.showMessageDialog(frame, "Ya existe un cliente registrado con ese documento.",
					"Cliente Existente!", JOptionPane.WARNING_MESSAGE);

			return;
		}		
	}
		try {
			boolean correctamente = ClienteGeoPosUy.modificarUsuario(fieldUsername, fieldNombre, fieldApellido, fieldDireccion, fieldPassword, fieldDoc , mapEstados.get(estado), mapRoles.get(rol), mapTiposDoc.get(tipoDoc), fieldCorreo, mapZonas.get(zona), mapDeptos.get(depto), mapLocs.get(localidad));
			if(correctamente ) {
				JOptionPane.showMessageDialog(frame, "Usuario modificado correctamente", "Modificación completada!",
						JOptionPane.WARNING_MESSAGE);
				this.frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "La modificación no ha sido posible", "Ha ocurrido un error!",
						JOptionPane.WARNING_MESSAGE);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

	private JComboBox<String> cargarComboZonas() throws Exception {
		
		mapZonas = new HashMap<String,Long >();
		mapZonasReverse = new HashMap<Long, String>();
		List<CodZona> zonas = new ArrayList<CodZona>();
		
		try {
			zonas = ClienteGeoPosUy.obtenerZonas();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		JComboBox<String> combo = new JComboBox<>();

		for (CodZona zona : zonas) {
			combo.addItem(zona.getDescCodZona());
			mapZonas.put(zona.getDescCodZona(),  zona.getIdCodZona());
			mapZonasReverse.put(zona.getIdCodZona(), zona.getDescCodZona());
		}

		return combo;
	}
	
	private JComboBox<String> cargarComboLocalidad() throws Exception {
		
		mapLocs = new HashMap<String,Long >();
		mapLocsReverse = new HashMap<Long, String>();
		List<CodLocalidad> localidades = new ArrayList<CodLocalidad>();
		
		try {
			localidades = ClienteGeoPosUy.obtenerLocalidades();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		JComboBox<String> combo = new JComboBox<>();

		for (CodLocalidad localidad : localidades) {
			combo.addItem(localidad.getDescCodLocalidad());
			mapLocs.put(localidad.getDescCodLocalidad(),  localidad.getIdCodLocalidad());
			mapLocsReverse.put(localidad.getIdCodLocalidad(), localidad.getDescCodLocalidad());
		}

		return combo;
	}
	
	private JComboBox<String> cargarComboDepartamento() throws Exception {
		
		mapDeptos = new HashMap<String, Long>();
		mapDeptosReverse = new HashMap<Long, String>();
		List<CodDepartamento> deptos = new ArrayList<CodDepartamento>();
		
		try {
			deptos = ClienteGeoPosUy.obtenerDepartamentos();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		JComboBox<String> combo = new JComboBox<>();

		for (CodDepartamento dep : deptos) {
			combo.addItem(dep.getDescCodDepartamento());
			mapDeptos.put(dep.getDescCodDepartamento(),  dep.getIdCodDepartamento());
			mapDeptosReverse.put(dep.getIdCodDepartamento(), dep.getDescCodDepartamento());
		}

		return combo;
	}
	
	private JComboBox<String> cargarComboTiposDoc() throws Exception {
		
		//Creo dos maps para guardar la correspondencia de ids con descripciones
		mapTiposDoc = new HashMap<String , Long>();
		mapTiposDocReverse = new HashMap<Long, String>();
		List<TipoDocumento> tiposDoc = new ArrayList<TipoDocumento>();
		
		tiposDoc = ClienteGeoPosUy.obtenerTiposDoc();

		JComboBox<String> combo = new JComboBox<>();

		for (TipoDocumento tipo : tiposDoc) {
			combo.addItem(tipo.getDescripcion());
			mapTiposDoc.put(tipo.getDescripcion(), tipo.getID_TIPO_DOC());
			mapTiposDocReverse.put(tipo.getID_TIPO_DOC(), tipo.getDescripcion());
			
		}

		return combo;
	}
	
	private JComboBox<String> cargarComboRol() throws Exception {
		
		mapRoles = new HashMap<String,Long >();
		mapRolesReverse = new HashMap<Long,String>();
		List<Rol> roles = new ArrayList<Rol>();
		
		roles = ClienteGeoPosUy.obtenerRoles();

		JComboBox<String> combo = new JComboBox<>();

		for (Rol rol : roles) {
			combo.addItem(rol.getDescripcion_rol());
			mapRoles.put(rol.getDescripcion_rol(),  rol.getId_rol());
			mapRolesReverse.put(rol.getId_rol(), rol.getDescripcion_rol());
		}

		return combo;
	}
	
	private JComboBox<String> cargarComboEstados() throws Exception {
		
		mapEstados = new HashMap<String,Long >();
		mapEstadosReverse = new HashMap<Long, String>();
		List<Estado> estados = new ArrayList<Estado>();
		
		estados = ClienteGeoPosUy.obtenerEstado();

		JComboBox<String> combo = new JComboBox<>();

		for (Estado estado : estados) {
			combo.addItem(estado.getDesc_estado());
			mapEstados.put(estado.getDesc_estado(),  estado.getId_estado());
			mapEstadosReverse.put(estado.getId_estado(), estado.getDesc_estado());
		}

		return combo;
	}
	  	    	  
}