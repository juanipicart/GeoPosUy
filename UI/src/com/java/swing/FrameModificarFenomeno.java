package com.java.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import com.clases.Fenomeno;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;
import com.interfaz.ClienteGeoPosUy;

public class FrameModificarFenomeno implements ActionListener {

	public static void main(String[] args) {

	}
	
	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelDescripcion;
	private JLabel labelNombre;
	private JLabel labelTelefono;
	private JLabel labelCodigo;

	/** Atributos de TexField */
	private JTextField textDescripcion;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textTelefono;
		
	/** Atributos de Botones */
	private JButton buttonConfirmar;
	private JButton buttonCancelar;
	
	private Fenomeno fenom;

	public FrameModificarFenomeno(JFrame framePadre, Fenomeno fenomeno) {

		this.fenom = fenomeno;
		this.labelDescripcion = new JLabel("(*) Descripción:");
		this.labelNombre = new JLabel("(*) Nombre:");
		this.labelTelefono = new JLabel("(*) Teléfono:");
		this.labelCodigo = new JLabel("(*) Código:");
		
		this.textDescripcion = new JTextField(30);
		this.textNombre = new JTextField(30);
		this.textTelefono = new JTextField(30);
		this.textCodigo = new JTextField(30);

		JButton buttonConfirmar = new JButton("Confirmar");
		buttonConfirmar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonConfirmar = buttonConfirmar;
		this.buttonCancelar = buttonCancelar;

		this.initializeFrame(framePadre, fenom);	
	}
	
	private void initializeFrame(JFrame framePadre, Fenomeno fenomeno) {

		JFrame frame = new JFrame("Modificar Fenómeno");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel buscarFenomenoPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		buscarFenomenoPanel.add(this.labelCodigo, constraints);
		
		constraints.gridx = 1;
		textCodigo.setEditable(false);
		textCodigo.setText(fenom.getCodigo());
		buscarFenomenoPanel.add(this.textCodigo, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		buscarFenomenoPanel.add(this.labelNombre, constraints);
		
		
		constraints.gridx = 1;
		textNombre.setText(fenom.getNombre());
		buscarFenomenoPanel.add(this.textNombre, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		buscarFenomenoPanel.add(this.labelDescripcion, constraints);

		constraints.gridx = 1;
		textDescripcion.setText(fenom.getDescripcion());
		buscarFenomenoPanel.add(this.textDescripcion, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		buscarFenomenoPanel.add(this.labelTelefono, constraints);

		constraints.gridx = 1;
		textTelefono.setText(fenom.getContacto_emergencia());
		buscarFenomenoPanel.add(this.textTelefono, constraints);

		buttonPanel.add(buttonConfirmar);
		buttonPanel.add(buttonCancelar);

		buscarFenomenoPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del fenómeno"));

		frame.add(buscarFenomenoPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);

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
			try {
				this.accionIngresar();
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (NoSeRealizoOperacionException e1) {
				e1.printStackTrace();
			} catch (ProblemasNivelSQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	private void accionIngresar() throws NamingException, NoSeRealizoOperacionException, ProblemasNivelSQLException {

		// Si es ingresar se validan datos!

		String fieldNombre = this.textNombre.getText();
		String fieldDescripcion = this.textDescripcion.getText();
		String fieldTelefono = this.textTelefono.getText();

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldDescripcion.equals("") || fieldTelefono.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos marcados con (*).", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return; }
		
		// Controlo el maximo de caracteres
		if (fieldNombre.length() > 20) {
			JOptionPane.showMessageDialog(frame, "El nombre puede contener máximo 20 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldDescripcion.length() > 100) {
			JOptionPane.showMessageDialog(frame, "La descripción puede contener máximo 100 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (fieldTelefono.length() > 20) {
			JOptionPane.showMessageDialog(frame, "El teléfono puede contener máximo 20 caracteres", "Datos inválidos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//Controlo que el fenomeno no exista ya
		
		boolean existe = false;
		boolean cambioNombre = !(fenom.getNombre().equalsIgnoreCase(fieldNombre));
		
		if (cambioNombre) {
			try{
				existe = ClienteGeoPosUy.ExisteFenomeno(fieldNombre);
			} catch (Exception e){
				JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
					"Error de conexión!", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
		if (existe)  {
			JOptionPane.showMessageDialog(frame, "Ya existe un fenómeno con ese nombre",
					"Fenómeno existente!", JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		try {
			if (ClienteGeoPosUy.ExisteObservacion(fenom.getCodigo())) {
				JOptionPane.showMessageDialog(frame, "El fenómeno no se puede modificar, tiene observaciones asociadas",
						"Error!", JOptionPane.WARNING_MESSAGE);

				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			//Si todo va bien modifico

		boolean almacenado = false;
		
		try {
			almacenado = ClienteGeoPosUy.modificarFenomeno(fenom.getCodigo(),fieldNombre, fieldDescripcion, fieldTelefono);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El fenómeno ha sido modificado con éxito.",
					"Fenómeno Modificado!", JOptionPane.INFORMATION_MESSAGE);
			
			// cerramos la ventanta
			this.frame.dispose();

			
		}
		else{
			JOptionPane.showMessageDialog(frame, "Hubo un error al almacenar. Intente nuevamente más tarde",
					"Error al registrar!", JOptionPane.ERROR_MESSAGE);
		}

}
	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}
}
