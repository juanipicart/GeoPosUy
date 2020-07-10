package com.java.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.interfaz.ClienteGeoPosUy;

public class FrameAltaFenomeno implements ActionListener {

	public void main(String[] args) throws NamingException {
		
		
		
	}
	
	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelCodigo;
	private JLabel labelDescripcion;
	private JLabel labelNombre;
	private JLabel labelTelefono;

	/** Atributos de TexField */
	private JTextField textCodigo;
	private JTextField textDescripcion;
	private JTextField textNombre;
	private JTextField textTelefono;
		
	/** Atributos de Botones */
	private JButton buttonRegistrar;
	private JButton buttonCancelar;

	public FrameAltaFenomeno(JFrame framePadre) {

		this.labelCodigo = new JLabel("(*) C�digo:");
		this.labelDescripcion = new JLabel("(*) Descripci�n:");
		this.labelNombre = new JLabel("(*) Nombre:");
		this.labelTelefono = new JLabel("(*) Tel�fono:");
		
		this.textCodigo = new JTextField(30);
		this.textDescripcion = new JTextField(30);
		this.textNombre = new JTextField(30);
		this.textTelefono = new JTextField(30);

		JButton buttonRegistrar = new JButton("Registrar Fen�meno");
		buttonRegistrar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar registro");
		buttonCancelar.addActionListener(this);

		this.buttonRegistrar = buttonRegistrar;
		this.buttonCancelar = buttonCancelar;

		this.initializeFrame(framePadre);
	}
	
	private void initializeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nuevo Fen�meno");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevoFenomenoPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevoFenomenoPanel.add(this.labelCodigo, constraints);
		
		constraints.gridx = 1;
		nuevoFenomenoPanel.add(this.textCodigo, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevoFenomenoPanel.add(this.labelNombre, constraints);
		
		constraints.gridx = 1;
		nuevoFenomenoPanel.add(this.textNombre, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevoFenomenoPanel.add(this.labelDescripcion, constraints);

		constraints.gridx = 1;
		nuevoFenomenoPanel.add(this.textDescripcion, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevoFenomenoPanel.add(this.labelTelefono, constraints);

		constraints.gridx = 1;
		nuevoFenomenoPanel.add(this.textTelefono, constraints);

		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevoFenomenoPanel.add(buttonRegistrar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.gridwidth = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevoFenomenoPanel.add(buttonCancelar, constraints);

		nuevoFenomenoPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del fen�meno"));

		frame.add(nuevoFenomenoPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		/* Debo primero conocer que bot�n fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			this.accionIngresar();
		}

	}

	private void accionIngresar() {

		// Si es ingresar se validan datos!

		String fieldCodigo = this.textCodigo.getText();
		String fieldNombre = this.textNombre.getText();
		String fieldDescripcion = this.textDescripcion.getText();
		String fieldTelefono = this.textTelefono.getText();

		// Si alguno es vac�o, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldCodigo.equals("") || fieldDescripcion.equals("") || fieldTelefono.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos marcados con (*).", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return; }
		
		// Controlo el maximo de caracteres
		if (fieldCodigo.length() > 5) {
			JOptionPane.showMessageDialog(frame, "El c�digo puede contener m�ximo 5 caracteres", "Datos inv�lidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldNombre.length() > 20) {
			JOptionPane.showMessageDialog(frame, "El nombre puede contener m�ximo 20 caracteres", "Datos inv�lidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldDescripcion.length() > 100) {
			JOptionPane.showMessageDialog(frame, "La descripci�n puede contener m�ximo 100 caracteres", "Datos inv�lidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldTelefono.length() > 20) {
			JOptionPane.showMessageDialog(frame, "El tel�fono puede contener m�ximo 20 caracteres", "Datos inv�lidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//Controlo que el fenomeno no exista ya
		
		boolean existe;
		
		try{
			existe = ClienteGeoPosUy.ExisteFenomeno(fieldNombre);
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "Error de conexi�n con el servidor. Intente m�s tarde.",
					"Error de conexi�n!", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (existe) {
			JOptionPane.showMessageDialog(frame, "Ya existe un fen�meno con ese nombre",
					"Fen�meno existente!", JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		//Si todo va bien lo doy de alta
		
		boolean almacenado = false;
		try {
			almacenado = ClienteGeoPosUy.crearFenomeno(fieldCodigo, fieldNombre, fieldDescripcion, fieldTelefono);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El fen�meno ha sido registrado con �xito.",
					"Cliente Registrado!", JOptionPane.INFORMATION_MESSAGE);
			
			// cerramos la ventanta
			this.frame.dispose();

			
		}
		else{
			JOptionPane.showMessageDialog(frame, "Hubo un error al almacenar. Intente nuevamente m�s tarde",
					"Error al registrar!", JOptionPane.ERROR_MESSAGE);
		}
		
		
}
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

}