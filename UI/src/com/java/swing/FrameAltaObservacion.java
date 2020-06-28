package com.java.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.clases.Fenomeno;
import com.clases.codigueras.CodDepartamento;
import com.clases.codigueras.CodLocalidad;
import com.clases.codigueras.CodZona;
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
	private JLabel labelImagenes;
	private JLabel labelEstado;
	private JLabel labelFecha;
	private JLabel labelHora;
	private JLabel labelFenomeno;
	private JLabel labelDepartamento;
	private JLabel labelLocalidad;
	private JLabel labelZona;
	
	/** Atributos de TexField */
	private JTextField textIdentificador;
	private JTextArea textDescripcion;
	private JTextField textLatitud;
	private JTextField textLongitud;
	private JScrollPane scrollDesc;
	
	/*Atributos de Combobox*/
	private JComboBox<String> comboEstado;
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

	public FrameAltaObservacion(JFrame framePadre) {

		this.labelIdentificador = new JLabel("Identificador:");
		this.labelDescripcion = new JLabel("Descripción:");
		this.labelLatitud = new JLabel("Latitud:");
		this.labelLongitud = new JLabel("Longitud:");
		this.labelFenomeno = new JLabel("Fenómeno");
		this.labelImagenes = new JLabel("Imagenes:");
		this.labelEstado = new JLabel("Criticidad:");
		this.labelDepartamento = new JLabel("Departamento:");
		this.labelLocalidad = new JLabel("Localidad:");
		this.labelZona = new JLabel("Zona:");
		this.labelFecha = new JLabel("Fecha:");
		
		this.textIdentificador = new JTextField(30);
		this.textDescripcion = new JTextArea(5, 30);
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
		nuevaObservacionPanel.add(this.labelEstado, constraints);

		/* ACA HAY QUE AGREGAR EL COMBO DE CRITICIDADES
		 * constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textDoc, constraints);*/
		
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

		String latitud = this.textLatitud.getText();
		String longitud = this.textLongitud.getText();
		String descripcion = this.textDescripcion.getText();
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Valido que la logitud y latitud esten permitidas
		String latLong = latitud + "," + longitud;
		
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
			if (!(ClienteGeoPosUy.validarPalabrasProhibidas(descripcion).isEmpty())) {
				List<String> palabras = ClienteGeoPosUy.validarPalabrasProhibidas(descripcion);
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
	
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Métodos para cargar los combos 
	private JComboBox<String> cargarComboZonas() throws Exception {
		
		mapZonas = new HashMap<String,Long >();
		List<CodZona> zonas = new ArrayList<CodZona>();
		
		try {
			zonas = ClienteGeoPosUy.obtenerZonas();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		JComboBox<String> combo = new JComboBox<>();
		
		combo.addItem("Seleccione una opción");
		for (CodZona zona : zonas) {
			combo.addItem(zona.getDescCodZona());
			mapZonas.put(zona.getDescCodZona(),  zona.getIdCodZona());
		}

		return combo;
	}
		
	private void cargarComboLocalidad(String depto) throws Exception {
		
		mapLocs = new HashMap<String,Long >();
		List<CodLocalidad> localidades = new ArrayList<CodLocalidad>();
		
		try {
			localidades = ClienteGeoPosUy.obtenerLocalidadesPorDepto(mapDeptos.get(depto));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		comboLocalidad.removeAllItems();
		comboLocalidad.addItem("Seleccione una localidad");
		for (CodLocalidad localidad : localidades) {
			comboLocalidad.addItem(localidad.getDescCodLocalidad());
			mapLocs.put(localidad.getDescCodLocalidad(),  localidad.getIdCodLocalidad());
		}

	}
	
	private JComboBox<String> cargarComboDepartamento() throws Exception {
		
		mapDeptos = new HashMap<String,Long >();
		List<CodDepartamento> deptos = new ArrayList<CodDepartamento>();
		
		try {
			deptos = ClienteGeoPosUy.obtenerDepartamentos();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		JComboBox<String> combo = new JComboBox<>();

		combo.addItem("Seleccione un departamento");
		for (CodDepartamento dep : deptos) {
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
	
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

	
	

}
