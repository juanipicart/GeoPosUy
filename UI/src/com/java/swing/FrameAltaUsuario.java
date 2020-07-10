package com.java.swing;


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
import java.util.logging.Logger;
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
import com.clases.codigueras.Departamento;
import com.clases.codigueras.Localidad;
import com.clases.codigueras.Rol;
import com.clases.codigueras.Zona;
import com.clases.codigueras.TipoDocumento;
import com.interfaz.ClienteGeoPosUy;

public class FrameAltaUsuario implements ActionListener {
	
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
		
	/** Atributos de Botones */
	private JButton buttonRegistrar;
	private JButton buttonCancelar;

	private Map< String,  Long> mapTiposDoc;
	private Map< String,  Long> mapDeptos;
	private Map< String,  Long> mapLocs;
	private Map< String,  Long> mapRoles;
	private Map< String,  Long> mapZonas;
	
	public FrameAltaUsuario(JFrame framePadre) {

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
		new JLabel("Estado:");
		
		this.textDoc = new JTextField(30);
		this.textNombre = new JTextField(30);
		this.textApellido = new JTextField(30);
		this.textDireccion = new JTextField(30);
		this.textUsername = new JTextField(30);
		this.textPassword = new JTextField(30);
		this.textCorreo = new JTextField(30);
		
		
		JButton buttonRegistrar = new JButton("Registrar Usuario");
		buttonRegistrar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar registro");
		buttonCancelar.addActionListener(this);

		this.buttonRegistrar = buttonRegistrar;
		this.buttonCancelar = buttonCancelar;

		this.initializeFrame(framePadre);
	}

	private void initializeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nuevo Usuario");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevoUsuarioPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevoUsuarioPanel.add(this.labelUsername, constraints);
		
		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textUsername, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevoUsuarioPanel.add(this.labelPassword, constraints);
		
		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textPassword, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevoUsuarioPanel.add(this.labelNombre, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textNombre, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevoUsuarioPanel.add(this.labelApellido, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textApellido, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		nuevoUsuarioPanel.add(this.labelTipoDoc, constraints);
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 4;
			this.comboTipoDoc = cargarComboTiposDoc();
			nuevoUsuarioPanel.add(this.comboTipoDoc, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		nuevoUsuarioPanel.add(this.labelDoc, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textDoc, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		nuevoUsuarioPanel.add(this.labelDireccion, constraints);

		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textDireccion, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		nuevoUsuarioPanel.add(this.labelCorreo, constraints);
		
		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textCorreo, constraints);
				
		constraints.gridx = 0;
		constraints.gridy = 8;
		nuevoUsuarioPanel.add(this.labelDepto, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 8;
			this.comboDepto = cargarComboDepartamento();
			this.comboDepto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
    	   
			try {
				cargarComboLocalidad((String) comboDepto.getSelectedItem());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
       }
			});
			nuevoUsuarioPanel.add(this.comboDepto, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 9;
		nuevoUsuarioPanel.add(this.labelLocalidad, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 9;
			this.comboLocalidad = new JComboBox<String>();
			this.comboLocalidad.addItem("Seleccione una localidad");
			nuevoUsuarioPanel.add(this.comboLocalidad, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 10;
		nuevoUsuarioPanel.add(this.labelZona, constraints);
		
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 10;
			this.comboZona = cargarComboZonas();
			nuevoUsuarioPanel.add(this.comboZona, constraints);
			
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
			nuevoUsuarioPanel.add(this.comboRol, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		constraints.gridx = 0;
		constraints.gridy = 12;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevoUsuarioPanel.add(buttonRegistrar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 12;
		constraints.gridwidth = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevoUsuarioPanel.add(buttonCancelar, constraints);

		nuevoUsuarioPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del usuario"));

		frame.add(nuevoUsuarioPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

		
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {

		/* Debo primero conocer que botón fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			this.accionIngresar();
		}	
	}



	private void accionIngresar() {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Acá arranco las validaciones de los datos ingresados
		
		String fieldUsername = this.textUsername.getText();
		String fieldPassword = this.textPassword.getText();
		String fieldNombre = this.textNombre.getText();
		String fieldApellido = this.textApellido.getText();
		String fieldDoc = this.textDoc.getText();
		String fieldDireccion = this.textDireccion.getText();
		String fieldCorreo = this.textCorreo.getText();
		String tipoDoc = (String) comboTipoDoc.getSelectedItem();
		String depto = (String)  comboDepto.getSelectedItem();
		String zona =  (String)  comboZona.getSelectedItem();
		String localidad =  (String) comboLocalidad.getSelectedItem();
		String rol = (String) comboRol.getSelectedItem();
		
		

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Si alguno es vacío, mostramos una ventana de mensaje
		
		if (fieldNombre.equals("") || fieldApellido.equals("") || fieldDoc.equals("") || fieldDireccion.equals("") || fieldUsername.equals("") || fieldPassword.equals("")	 ||  depto.equals("") || zona.equals("") || localidad.equals("")  ) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return; }
		
		//Verifico que haya seleccionado los combos
		if (comboDepto.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione un departamento", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (comboTipoDoc.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione un tipo de documento", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (comboZona.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione una zona", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (comboRol.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione un rol", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}  else if (comboLocalidad.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione una localidad", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Controlo el largo máximo de los campos
		
		if (fieldUsername.length() > 25 ) {
			JOptionPane.showMessageDialog(frame, "El username no puede contener más de 25 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldNombre.length() > 50 ) {
			JOptionPane.showMessageDialog(frame, "El nombre no puede contener más de 50 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldApellido.length() > 25 ) {
			JOptionPane.showMessageDialog(frame, "El apellido no puede contener más de 50 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldDireccion.length() > 200 ) {
			JOptionPane.showMessageDialog(frame, "La dirección no puede contener más de 200 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldCorreo.length() > 100 ) {
			JOptionPane.showMessageDialog(frame, "El correo no puede contener más de 100 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldDoc.length() > 20 ) {
			JOptionPane.showMessageDialog(frame, "El documento no puede contener más de 20 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Controlo que la password siga los criterios de seguridad (debe contener una mayúscula, un número y una minúscula al menos)
		
		Pattern UpperCasePattern = Pattern.compile("[A-Z ]");
		Pattern LowerCasePattern = Pattern.compile("[a-z ]");
		Pattern numberPattern = Pattern.compile("[0-9 ]");
		
		if (fieldPassword.length() < 8 || !UpperCasePattern.matcher(fieldPassword).find()|| !LowerCasePattern.matcher(fieldPassword).find() || !numberPattern.matcher(fieldPassword).find()) { 
			JOptionPane.showMessageDialog(frame, "La password debe tener más de 8 caracteres y contener al menos una mayúscula, una minúscula y un número", "Datos inválidos!",
				JOptionPane.WARNING_MESSAGE);
		return;
	    } else if (fieldPassword.length() > 100) {
	    	JOptionPane.showMessageDialog(frame, "La password no puede contener más de 100 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
	    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		//Verifico el formato de la cédula
			
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
				Logger.getLogger("");
			} 
		} 
				
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Valido el formato del correo
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		
		Matcher matcher = pattern.matcher(fieldCorreo);
		
	
		if (!matcher.matches()) {
			JOptionPane.showMessageDialog(frame, "El formato del correo no es correcto", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Si llego acá estoy listo para insertar
		try {
			//Me fijo si el username o el documento ya existen en el sistema
			Usuario usuarioByUsername = ClienteGeoPosUy.buscarUsuarioPorUsername(fieldUsername); 
			Usuario usuarioByDoc = ClienteGeoPosUy.buscarUsuarioPorDocumento(mapTiposDoc.get(tipoDoc), fieldDoc);
			
			boolean username = false;
			boolean userDoc =false;
			
			if (!(usuarioByUsername == null)) {
				username = true;
			} else if (!(usuarioByDoc == null)) {
				userDoc = true;
			}
			
			//Si no existe lo registro, si existe muestro el mensaje de error correspondiente
			if (!username && !userDoc) {
			ClienteGeoPosUy.registrarUsuario(fieldUsername, fieldNombre, fieldApellido, fieldDireccion, mapRoles.get(rol), mapLocs.get(localidad), 
					mapZonas.get(zona), 1, mapDeptos.get(depto), fieldCorreo, fieldPassword, mapTiposDoc.get(tipoDoc), fieldDoc);
			
				JOptionPane.showMessageDialog(frame, "Usuario registrado correctamente", "Registro completado!",
						JOptionPane.WARNING_MESSAGE);
				frame.dispose();
			} else if (username) {
				JOptionPane.showMessageDialog(frame, "El nombre de usuario ya se encuentra registrado", "Ha ocurrido un error",
						JOptionPane.WARNING_MESSAGE);
			} else if (userDoc) {
				JOptionPane.showMessageDialog(frame, "El documento ya se encuentra registrado", "Ha ocurrido un error",
						JOptionPane.WARNING_MESSAGE);
			} 
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "El servidor no se encuentra disponible. Contacte al administrador", "Ha ocurrido un error",
					JOptionPane.WARNING_MESSAGE);
		} 
		
			
		}
	

	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Funciones para cargar los combos
	private JComboBox<String> cargarComboZonas() throws Exception {
		
		mapZonas = new HashMap<String,Long >();
		List<Zona> zonas = new ArrayList<Zona>();
		
		try {
			zonas = ClienteGeoPosUy.obtenerZonas();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		JComboBox<String> combo = new JComboBox<>();
		
		combo.addItem("Seleccione una opción");
		for (Zona zona : zonas) {
			combo.addItem(zona.getDescCodZona());
			mapZonas.put(zona.getDescCodZona(),  zona.getIdCodZona());
		}

		return combo;
	}
		
	private void cargarComboLocalidad(String depto) throws Exception {
		
		mapLocs = new HashMap<String,Long >();
		List<Localidad> localidades = new ArrayList<Localidad>();
		
		try {
			localidades = ClienteGeoPosUy.obtenerLocalidadesPorDepto(mapDeptos.get(depto));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		comboLocalidad.removeAllItems();
		comboLocalidad.addItem("Seleccione una localidad");
		for (Localidad localidad : localidades) {
			comboLocalidad.addItem(localidad.getDescCodLocalidad());
			mapLocs.put(localidad.getDescCodLocalidad(),  localidad.getIdCodLocalidad());
		}

	}
	
	private JComboBox<String> cargarComboDepartamento() throws Exception {
		
		mapDeptos = new HashMap<String,Long >();
		List<Departamento> deptos = new ArrayList<Departamento>();
		
		try {
			deptos = ClienteGeoPosUy.obtenerDepartamentos();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		JComboBox<String> combo = new JComboBox<>();

		combo.addItem("Seleccione un departamento");
		for (Departamento dep : deptos) {
			combo.addItem(dep.getDescCodDepartamento());
			mapDeptos.put(dep.getDescCodDepartamento(),  dep.getIdCodDepartamento());
		}

		return combo;
	}
	
	private JComboBox<String> cargarComboTiposDoc() throws Exception {
		
		//Creo un map para guardar la correspondencia de ids con descripciones
		mapTiposDoc = new HashMap<String,Long >();
		List<TipoDocumento> tiposDoc = new ArrayList<TipoDocumento>();
		
		tiposDoc = ClienteGeoPosUy.obtenerTiposDoc();

		JComboBox<String> combo = new JComboBox<>();

		combo.addItem("Seleccione una opción");
		for (TipoDocumento tipo : tiposDoc) {
			combo.addItem(tipo.getDescripcion());
			mapTiposDoc.put(tipo.getDescripcion(),  tipo.getID_TIPO_DOC());
		}

		return combo;
	}
	
	private JComboBox<String> cargarComboRol() throws Exception {
		
		mapRoles = new HashMap<String,Long >();
		List<Rol> roles = new ArrayList<Rol>();
		
		roles = ClienteGeoPosUy.obtenerRoles();

		JComboBox<String> combo = new JComboBox<>();

		combo.addItem("Seleccione una opción");
		for (Rol rol : roles) {
			combo.addItem(rol.getDescripcion_rol());
			mapRoles.put(rol.getDescripcion_rol(),  rol.getId_rol());
		}

		return combo;
	}
	
}
