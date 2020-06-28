package com.java.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

public class FrameBajaFenomeno implements ActionListener {

	public static void main(String[] args) {

	}
	
	/** Frame de la ventana */
	public JFrame frameBaja;

	/** Atributos de labels */
	private JLabel labelCodigo;


	/** Atributos de TexField */
	private JTextField textCodigo;
		
	/** Atributos de Botones */
	private JButton buttonConfirmar;
	private JButton buttonCancelar;
	
	Fenomeno fenomeno;

	public FrameBajaFenomeno(JFrame framePadre) {

		this.labelCodigo = new JLabel("(*) Código:");
			
		this.textCodigo = new JTextField(20);

		JButton buttonConfirmar = new JButton("Eliminar fenómeno");
		buttonConfirmar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonConfirmar = buttonConfirmar;
		this.buttonCancelar = buttonCancelar;

		this.initializeFrame(framePadre);
	}
	
	private void initializeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Eliminar Fenómeno");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel eliminarFenomenoPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		eliminarFenomenoPanel.add(this.labelCodigo, constraints);
		
		constraints.gridx = 1;
		eliminarFenomenoPanel.add(this.textCodigo, constraints);

		buttonPanel.add(buttonConfirmar);
		buttonPanel.add(buttonCancelar);

		eliminarFenomenoPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Ingrese el código del fenómeno que desea eliminar"));

		frame.add(eliminarFenomenoPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

		this.frameBaja = frame;

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
			JOptionPane.showMessageDialog(frameBaja, "Debe ingresar el código del fenómeno", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return; }
		
		// Controlo el maximo de caracteres
		if (fieldCodigo.length() > 5) {
			JOptionPane.showMessageDialog(frameBaja, "El código puede contener máximo 5 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			fenomeno = ClienteGeoPosUy.BuscarFenomenoPorCodigo(fieldCodigo);
			if (fenomeno == null) {
					JOptionPane.showMessageDialog(frameBaja, "El fenómeno no existe", "Datos inválidos!",
							JOptionPane.WARNING_MESSAGE);
					return;
			} 
		} catch (HeadlessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		try {
			if (ClienteGeoPosUy.ExisteObservacion(fieldCodigo)) {
					JOptionPane.showMessageDialog(frameBaja, "El fenómeno tiene observaciones asociadas por lo "
							+ "que no puede ser eliminado", "No se puede completar la acción",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		//Muestro un mensaje de confirmación
		int dialogResult = 0;
		try {
			Object[] options = {"Confirmar", "Cancelar"};
			dialogResult = JOptionPane.showOptionDialog(frameBaja, "Se dará de baja el fenómeno: " + fenomeno.getNombre() + ". Desea continuar?"
					,"Confirmar baja de fenómeno", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		} catch (HeadlessException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (dialogResult == JOptionPane.YES_OPTION) {
		boolean eliminado = false;
		try {
			eliminado = ClienteGeoPosUy.EliminarFenomeno(fieldCodigo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (eliminado) {
			JOptionPane.showMessageDialog(frameBaja, "El fenómeno fue eliminado", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			
			// cerramos la ventanta
			this.frameBaja.dispose();

			
		}
		else{
			JOptionPane.showMessageDialog(frameBaja, "Hubo un error al eliminar. Intente nuevamente",
					"Error al registrar!", JOptionPane.ERROR_MESSAGE);
		}
		}
			
	}		
        	
		         
         
                  

	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frameBaja.dispose();

	
	}
	}
