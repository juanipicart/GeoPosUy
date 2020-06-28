package com.java.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	private JTextField textDescripcion;
	private JTextField textLatitud;
	private JTextField textLongitud;
	
	/*Atributos de Combobox*/
	private JComboBox<String> comboEstado;
	private JComboBox<String> comboDepto;
	private JComboBox<String> comboLocalidad;
	private JComboBox<String> comboZona;
	private JComboBox<String> comboFenomeno;
	
	/*Atributo de Fecha*/
	//private JDatePickerImpl calendario;
	
	/** Atributos de Botones */
	private JButton buttonRegistrar;
	private JButton buttonCancelar;

	public FrameAltaObservacion(JFrame framePadre) {

		this.labelIdentificador = new JLabel("Identificador:");
		this.labelDescripcion = new JLabel("Descripción:");
		this.labelLatitud = new JLabel("Latitud:");
		this.labelLongitud = new JLabel("Longitud:");
		this.labelImagenes = new JLabel("Imagenes:");
		this.labelEstado = new JLabel("Criticidad:");
		this.labelDepartamento = new JLabel("Departamento:");
		this.labelLocalidad = new JLabel("Localidad:");
		this.labelZona = new JLabel("Zona:");
		this.labelFecha = new JLabel("Fecha:");
		
		this.textIdentificador = new JTextField(30);
		this.textDescripcion = new JTextField(30);
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

		/*constraints.gridx = 1;
		this.calendario = this.createDatePicker();
		nuevaObservacionPanel.add(this.calendario, constraints);*/
		
		/*constraints.gridx = 0;
		constraints.gridy = 6;
		nuevaObservacionPanel.add(this.labelFenomeno, constraints);*/
		
		/* ACA HAY QUE AGREGAR EL COMBO DE FENOMENOS
		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.textCorreo, constraints);*/
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		nuevaObservacionPanel.add(this.labelDepartamento, constraints);
		
		/*ACA VA EL COMBO DEPARTAMENTO
		constraints.gridx = 1;
		nuevoUsuarioPanel.add(this.)*/

		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevaObservacionPanel.add(buttonRegistrar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 9;
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

	/*private JDatePickerImpl createDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		return datePicker;
	}*/
	
	@Override
	public void actionPerformed(ActionEvent e) {

		/* Debo primero conocer que botón fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			this.accionIngresar();
		}

	}

	private void accionIngresar() {/*

		// Si es ingresar se validan datos!

		String fieldNombre = this.textNombre.getText();
		String fieldApellido = this.textApellido.getText();
		String fieldDoc = this.textDoc.getText();
		String fieldDireccion = this.textDireccion.getText();
		String fieldUsername = this.textUsername.getText();
		String fieldCorreo = this.textCorreo.getText();

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldApellido.equals("") || fieldDoc.equals("") || fieldDireccion.equals("") || fieldUsername.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return; */}
	
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

	
	

}
