package com.java.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.clases.Fenomeno;
import com.clases.Observacion;
import com.clases.Usuario;
import com.clases.codigueras.Departamento;
import com.clases.codigueras.Localidad;
import com.clases.codigueras.Zona;
import com.exceptions.NoValidaParamException;
import com.exceptions.ProblemasNivelSQLException;
import com.interfaz.ClienteGeoPosUy;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FrameAltaObservacion implements ActionListener {
	
	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelIdentificador;
	private JLabel labelDescripcion;
	private JLabel labelLatitud;
	private JLabel labelLongitud;
	private JLabel labelCriticidad;
	private JLabel labelFecha;
	private JLabel labelFenomeno;
	private JLabel labelDepartamento;
	private JLabel labelLocalidad;
	private JLabel labelZona;
	
	/** Atributos de TexField */
	private JTextField textIdentificador;
	private JTextField textDescripcion;
	private JTextField textLatitud;
	private JTextField textLongitud;
	private JScrollPane scrollDesc;
	
	/*Atributos de Combobox*/
	private JComboBox<String> comboCriticidad;
	private JComboBox<String> comboDepto;
	private JComboBox<String> comboLocalidad;
	private JComboBox<String> comboZona;
	private JComboBox<String> comboFenomeno;
	
	/*Atributo de Fecha*/
	private JDatePickerImpl calendario;
	
	/** Atributos de Botones */
	private JButton buttonRegistrar;
	private JButton buttonCancelar;
	
	//Hashmaps para guardar los combos
	private Map< String,  Long> mapDeptos;
	private Map< String,  Long> mapLocs;
	private Map< String,  Long> mapZonas;
	private Map< String,  Long> mapFenom;
	
	//usuario que registra
	Usuario usuario = new Usuario();

	public FrameAltaObservacion(JFrame framePadre, Usuario usuario) {

		this.usuario = usuario;
		this.labelIdentificador = new JLabel("Identificador:");
		this.labelDescripcion = new JLabel("Descripción:");
		this.labelLatitud = new JLabel("Latitud:");
		this.labelLongitud = new JLabel("Longitud:");
		this.labelFenomeno = new JLabel("Fenómeno");
		new JLabel("Imagenes:");
		this.labelCriticidad = new JLabel("Criticidad:");
		this.labelDepartamento = new JLabel("Departamento:");
		this.labelLocalidad = new JLabel("Localidad:");
		this.labelZona = new JLabel("Zona:");
		this.labelFecha = new JLabel("Fecha:");
		
		this.textIdentificador = new JTextField(30);
		this.textDescripcion = new JTextField(30);
		scrollDesc = new JScrollPane(textDescripcion);
		this.textLatitud = new JTextField(30);
		this.textLongitud = new JTextField(30);
	
		JButton buttonRegistrar = new JButton("Registrar Observación");
		buttonRegistrar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar registro");
		buttonCancelar.addActionListener(this);

		this.buttonRegistrar = buttonRegistrar;
		this.buttonCancelar = buttonCancelar;

		this.initializeFrame(framePadre);
	}
	
	private void initializeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nueva Observación");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevaObservacionPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevaObservacionPanel.add(this.labelIdentificador, constraints);
		
		constraints.gridx = 1;
		try {
			textIdentificador.setText(Long.toString(ClienteGeoPosUy.obtenerNextVal()));
		} catch (SQLException | NamingException e1) {
			e1.printStackTrace();
		}
		textIdentificador.setEditable(false);
		nuevaObservacionPanel.add(this.textIdentificador, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevaObservacionPanel.add(this.labelDescripcion, constraints);
		
		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textDescripcion, constraints);
		nuevaObservacionPanel.add(scrollDesc);
		

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevaObservacionPanel.add(this.labelLatitud, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textLatitud, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevaObservacionPanel.add(this.labelLongitud, constraints);

		constraints.gridx = 1;
		nuevaObservacionPanel.add(this.textLongitud, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		nuevaObservacionPanel.add(this.labelCriticidad, constraints);

		
		constraints.gridx = 1;
		this.comboCriticidad = completarComboCriticidad();
		nuevaObservacionPanel.add(this.comboCriticidad, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		nuevaObservacionPanel.add(this.labelFecha, constraints);

		constraints.gridx = 1;
		this.calendario = this.createDatePicker();
		nuevaObservacionPanel.add(this.calendario, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		nuevaObservacionPanel.add(this.labelFenomeno, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 6;
			this.comboFenomeno = cargarComboFenomenos();
			nuevaObservacionPanel.add(this.comboFenomeno, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		nuevaObservacionPanel.add(this.labelDepartamento, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 7;
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
			nuevaObservacionPanel.add(this.comboDepto, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 8;
		nuevaObservacionPanel.add(this.labelLocalidad, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 8;
			this.comboLocalidad = new JComboBox<String>();
			this.comboLocalidad.addItem("Seleccione una localidad");
			nuevaObservacionPanel.add(this.comboLocalidad, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 9;
		nuevaObservacionPanel.add(this.labelZona, constraints);
		
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 9;
			this.comboZona = cargarComboZonas();
			nuevaObservacionPanel.add(this.comboZona, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevaObservacionPanel.add(buttonRegistrar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 11;
		constraints.gridwidth = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevaObservacionPanel.add(buttonCancelar, constraints);

		nuevaObservacionPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos de la observación"));

		frame.add(nuevaObservacionPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}

	private JDatePickerImpl createDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		return datePicker;
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

		String fieldIdentificador = this.textDescripcion.getText();
		String fieldDescripcion = this.textDescripcion.getText();
		String fieldLatitud = this.textLatitud.getText();
		String fieldLongitud = this.textLongitud.getText();
		String latLong = fieldLatitud + "," + fieldLongitud;
		Date fecha = (Date) calendario.getModel().getValue();
		String criticidad = (String) comboCriticidad.getSelectedItem();
		String depto = (String)  comboDepto.getSelectedItem();
		String zona =  (String)  comboZona.getSelectedItem();
		String localidad =  (String) comboLocalidad.getSelectedItem();
		String fenomeno = (String) comboFenomeno.getSelectedItem();
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Valido los campos requeridos
		if (fieldIdentificador.equals("") ||fieldDescripcion.equals("") || fieldLatitud.equals("") || fieldLongitud.equals("") ) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return; }
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Valido el valor de los combos
		if (comboCriticidad.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione la criticidad", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (comboDepto.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione el departamento", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (comboLocalidad.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione la localidad", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (comboZona.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione la zona", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (comboFenomeno.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(frame, "Seleccione un fenómeno", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} 		
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Controlo el largo máximo de los campos
		
		if (fieldDescripcion.length() > 100 ) {
					JOptionPane.showMessageDialog(frame, "La descripción no puede contener más de 100 caracteres", "Datos inválidos!",
							JOptionPane.WARNING_MESSAGE);
					return;
		} else if (fieldLatitud.length() > 20 ) {
					JOptionPane.showMessageDialog(frame, "El nombre no puede contener más de 50 caracteres", "Datos inválidos!",
							JOptionPane.WARNING_MESSAGE);
					return;
		} else if (fieldLongitud.length() > 20 ) {
					JOptionPane.showMessageDialog(frame, "El apellido no puede contener más de 50 caracteres", "Datos inválidos!",
							JOptionPane.WARNING_MESSAGE);
					return;
		}
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Valido que la logitud y latitud sean validas y esten permitidas
		Pattern numbersOnly = Pattern.compile("[0-9.-]");
		
		if (!numbersOnly.matcher(fieldLongitud).find() ) { 
			JOptionPane.showMessageDialog(frame, "La longitud ingresada no es válida", "Datos inválidos!",
				JOptionPane.WARNING_MESSAGE);
		return;
	    } else if (!numbersOnly.matcher(fieldLatitud).find() ) { 
			JOptionPane.showMessageDialog(frame, "La latitud ingresada no es válida", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
				
		try {
			if (!ClienteGeoPosUy.validarLatitudLongitud(latLong)) {
				JOptionPane.showMessageDialog(frame, "Las coordenadas geográficas no pertenecen al territorio uruguayo", "Ubicación inválida!",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (NoValidaParamException e) {
			e.printStackTrace();
		}
		
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Valido si tiene palabras prohibidas
		try {
			if (!(ClienteGeoPosUy.validarPalabrasProhibidas(fieldDescripcion).isEmpty())) {
				List<String> palabras = ClienteGeoPosUy.validarPalabrasProhibidas(fieldDescripcion);
				String mensaje = "La observación ingresada contiene las siguientes palabras prohibidas: ";
				for (int i=0; i<palabras.size(); i++) {
					if (i< palabras.size()-1) {
						mensaje = mensaje + palabras.get(i) + ", ";
					} else {
						mensaje = mensaje + palabras.get(i) + ".";
					}
				}
								
				JOptionPane.showMessageDialog(frame, mensaje  , "Palabras Prohibidas!",
						JOptionPane.WARNING_MESSAGE);
				return;
				
			}
		} catch (SQLException | ProblemasNivelSQLException | NamingException e) {
			e.printStackTrace();
		}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Valido que la fecha no se futura
		if (fecha.after(new Date())) {
			JOptionPane.showMessageDialog(frame, "La fecha no puede ser mayor a hoy"  , "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Si llego acá, intento guardar
	//Me fijo la criticidad cuanto vale
	//Primero creo el objeto 
	Observacion observacion;
	
	try {
		observacion = new Observacion(fieldDescripcion, latLong, fecha, usuario.getId_user(), ClienteGeoPosUy.obtenerCriticidad(criticidad), 
				mapLocs.get(localidad), mapDeptos.get(depto), mapZonas.get(zona), 0, "", mapFenom.get(fenomeno));
		
		ClienteGeoPosUy.RegistrarObservacion(observacion);
		
		JOptionPane.showMessageDialog(frame, "Observación registrada con éxito", "Registro completado!",
				JOptionPane.WARNING_MESSAGE);
		frame.dispose();
	} catch (Exception e){
		JOptionPane.showMessageDialog(frame, "Ocurrió un error"  , "Error del sistema!",
				JOptionPane.WARNING_MESSAGE);
		return;
	}
	
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Métodos para cargar los combos 
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
	
	private JComboBox<String> cargarComboFenomenos() throws Exception {
		
		mapFenom = new HashMap<String,Long >();
		List<Fenomeno> fenomenos = new ArrayList<Fenomeno>();
		
		try {
			fenomenos = ClienteGeoPosUy.ObtenerTodosLosFenomenos();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		JComboBox<String> combo = new JComboBox<>();

		combo.addItem("Seleccione un fenómeno");
		for (Fenomeno fenomeno : fenomenos) {
			combo.addItem(fenomeno.getNombre());
			mapFenom.put(fenomeno.getNombre(), fenomeno.getId_fenomeno());
		}

		return combo;
	}
	
	private JComboBox<String> completarComboCriticidad() {
		
		String[] valores = {"Seleccione un valor","Alta", "Media", "Baja", "Informe"};
		return new JComboBox<>(valores);
		
	}
	
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

	
	

}
