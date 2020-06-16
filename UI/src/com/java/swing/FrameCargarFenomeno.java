
package com.java.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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

import com.clases.Fenomeno;
import com.interfaz.ClienteGeoPosUy;

public class FrameCargarFenomeno implements ActionListener {

	public static void main(String[] args) {

	}
	
	/** Frame de la ventana */
	public JFrame frame;

	/** Atributos de labels */
	private JLabel labelCodigo;


	/** Atributos de TexField */
	private JTextField textCodigo;

		
	/** Atributos de Botones */
	private JButton buttonConfirmar;
	private JButton buttonCancelar;

	public FrameCargarFenomeno(JFrame framePadre) {

		this.labelCodigo = new JLabel("(*) Código:");
		
		
		this.textCodigo = new JTextField(30);
	

		JButton buttonConfirmar = new JButton("Buscar fenomeno");
		buttonConfirmar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonConfirmar = buttonConfirmar;
		this.buttonCancelar = buttonCancelar;

		this.initializeFrame(framePadre);
	}
	
	private void initializeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Modificar Fenómeno");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel buscarFenomenoPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		buscarFenomenoPanel.add(this.labelCodigo, constraints);
		
		constraints.gridx = 1;
		buscarFenomenoPanel.add(this.textCodigo, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		buscarFenomenoPanel.add(buttonConfirmar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		buscarFenomenoPanel.add(buttonCancelar, constraints);

		buscarFenomenoPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Ingrese el código del fenómeno"));

		frame.add(buscarFenomenoPanel);

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

		String fieldCodigo = this.textCodigo.getText();

		// Si es vacío, mostramos una ventana de mensaje
		if (fieldCodigo.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe ingresar el código del fenómeno", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return; }
		
		// Controlo el maximo de caracteres
		if (fieldCodigo.length() > 5) {
			JOptionPane.showMessageDialog(frame, "El código puede contener máximo 5 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Fenomeno fenomeno = null;
		try {
			fenomeno = ClienteGeoPosUy.BuscarFenomenoPorCodigo(fieldCodigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		if (fenomeno == null) {
				JOptionPane.showMessageDialog(frame, "El fenómeno no existe", "Datos inválidos!",
						JOptionPane.WARNING_MESSAGE);
				return;
		} else {
				new FrameModificarFenomeno(frame, fenomeno);		
	}

 }
        
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}
}
