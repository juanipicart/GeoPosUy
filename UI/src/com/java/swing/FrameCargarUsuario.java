package com.java.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clases.Usuario;
import com.clases.codigueras.TipoDocumento;
import com.interfaz.ClienteGeoPosUy;

public class FrameCargarUsuario implements ActionListener {

	public static void main(String[] args) {

	}
	
	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelUsername;
	private JLabel labelDocumento;
	private JLabel labelTipoDoc;

	/** Atributos de TexField */
	private JTextField textUsername;
	private JTextField textDocumento;
	
	private JComboBox<String> comboTipoDoc;
		
	/** Atributos de Botones */
	private JButton buttonBuscar;
	private JButton buttonCancelar;
	
	HashMap<String, Long> mapTiposDoc;

	public FrameCargarUsuario(JFrame framePadre) {
		
		this.labelUsername = new JLabel("Nombre de usuario:");
		this.labelDocumento = new JLabel("Documento:");
		this.labelTipoDoc = new JLabel("Tipo de documento:");
		
		this.textUsername = new JTextField(30);
		this.textDocumento = new JTextField(30);

		JButton buttonBuscar = new JButton("Buscar");
		buttonBuscar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonBuscar = buttonBuscar;
		this.buttonCancelar = buttonCancelar;

		this.initializeFrame(framePadre);
	}
	
	private void initializeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Buscar usuario a modificar");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel buscarUsuarioPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		buscarUsuarioPanel.add(this.labelUsername, constraints);
		
		
		constraints.gridx = 1;
		buscarUsuarioPanel.add(this.textUsername, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		buscarUsuarioPanel.add(this.labelTipoDoc, constraints);
		
		try {
			
			constraints.gridx = 1;
			constraints.gridy = 1;
			this.comboTipoDoc = cargarComboTiposDoc();
			buscarUsuarioPanel.add(this.comboTipoDoc, constraints);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		buscarUsuarioPanel.add(this.labelDocumento, constraints);
		
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		buscarUsuarioPanel.add(this.textDocumento, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		buscarUsuarioPanel.add(buttonBuscar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		buscarUsuarioPanel.add(buttonCancelar, constraints);

		buscarUsuarioPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Ingrese al menos un criterio de búsqueda"));

		frame.add(buscarUsuarioPanel);

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

		// Si es ingresar se validan datos!

		String fieldUsername = this.textUsername.getText();
		String fieldDocumento = this.textDocumento.getText();
		String tipoDoc = (String) comboTipoDoc.getSelectedItem();

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldUsername.equals("") && fieldDocumento.equals(""))  {
			JOptionPane.showMessageDialog(frame, "Debe ingresar nombre de usuario o documento", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return; }
		
		// Controlo el maximo de caracteres
		if (fieldUsername.length() > 15) {
			JOptionPane.showMessageDialog(frame, "El nombre de usuario puede contener máximo 15 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
		}
		
		if (fieldDocumento.length() > 15) {
			JOptionPane.showMessageDialog(frame, "El documento puede contener máximo 15 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
		}
				
		Usuario usuarioAModificar = null;
		
		if (fieldUsername.equals("")) {
			try {
				usuarioAModificar = ClienteGeoPosUy.buscarUsuarioPorDocumento(mapTiposDoc.get(tipoDoc), fieldDocumento);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	else  {
			try {
				usuarioAModificar = ClienteGeoPosUy.buscarUsuarioPorUsername(fieldUsername);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(usuarioAModificar == null) {
			JOptionPane.showMessageDialog(frame, "No se encontró un usuario",
					"Usuario inexistente!", JOptionPane.WARNING_MESSAGE);

			return;			
		}else {
			new FrameModificarUsuario(frame, usuarioAModificar);			
		}
				

}
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}
	
	private JComboBox<String> cargarComboTiposDoc() throws Exception {
		
		//Creo un map para guardar la correspondencia de ids con descripciones
		mapTiposDoc = new HashMap<String,Long >();
		List<TipoDocumento> tiposDoc = new ArrayList<TipoDocumento>();
		
		tiposDoc = ClienteGeoPosUy.obtenerTiposDoc();

		JComboBox<String> combo = new JComboBox<>();

		for (TipoDocumento tipo : tiposDoc) {
			combo.addItem(tipo.getDescripcion());
			mapTiposDoc.put(tipo.getDescripcion(),  tipo.getID_TIPO_DOC());
		}

		return combo;
	}
}
