package com.java.swing;

import java.awt.BorderLayout;
import java.awt.Color;
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

import com.clases.Usuario;
import com.interfaz.ClienteGeoPosUy;

public class FrameLogin {
	
	private static JFrame Jframe;
	
	private static JLabel labelUsername;
	private static JLabel labelPassword;
	
	private static JTextField textUsername;
	private static JTextField textPassword;

	private static JButton buttonIngresar;
	private static JButton buttonCancelar;

	private static Usuario usuario;
	
	public static void main(String[] args) throws NamingException {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
			
	}
	
	private static void createAndShowGUI() {

	
	JFrame frame = new JFrame("GeoPosUY");
	
	frame.setSize(200, 200);
	frame.setResizable(false);
    frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setBackground(Color.BLUE);

	
	initializeFrame(frame);
	}
	

	
	
	private static void initializeFrame(JFrame frame) {


		labelUsername = new JLabel("Usuario:");
		labelPassword = new JLabel("Contraseña:");
		
		textUsername = new JTextField(30);
		textPassword = new JTextField(30);
		
		buttonIngresar = new JButton("Ingresar");
		buttonIngresar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	accionIngresar();
	            	
	            }
	        });
		
		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	accionCancelar();
	            	
	            }
	        });

		JPanel loginPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;		
		loginPanel.add(labelUsername,constraints);
		
		constraints.gridx = 1;
		loginPanel.add(textUsername, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		loginPanel.add(labelPassword, constraints);
		
		constraints.gridx = 1;
		loginPanel.add(textPassword, constraints);
		
		buttonPanel.add(buttonIngresar);
		buttonPanel.add(buttonCancelar);
		
		loginPanel
		.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Ingreso al sistema"));
		// Display the window.
		
		frame.add(loginPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
		
		Jframe = frame;
	}

	
	private static void accionIngresar() {
		String username = textUsername.getText();
		String password = textPassword.getText();
		
		//Verifico usuario y contraseña
		boolean loginExitoso = false;
		try {
			loginExitoso = ClienteGeoPosUy.validarLogin(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Verifico el estado del usuario	
		boolean usuarioActivo = false;
		if (loginExitoso) {
			try {
				usuario = ClienteGeoPosUy.buscarUsuarioPorUsername(username);
				usuarioActivo = (usuario.getEstado() == 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		if (usuarioActivo && loginExitoso) {			
			try {
				//Si los datos son validos creo el objeto Usuario para obtener el rol y definir los permisos
				Jframe.dispose();
				FramePrincipal framePrincipal = new FramePrincipal(usuario);
				framePrincipal.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ((!usuarioActivo && loginExitoso)) {
				JOptionPane.showMessageDialog(Jframe, "El usuario fue dado de baja. Contactese con un administrador.", "Ha ocurrido un error!",
						JOptionPane.WARNING_MESSAGE);	
		} else {
			JOptionPane.showMessageDialog(Jframe, "Verifique los datos ingresados.", "Ha ocurrido un error!",
					JOptionPane.WARNING_MESSAGE);	
		} 
	}
	
	private static void accionCancelar() {
		// si se cancela, se eliminar la ventana
		Jframe.dispose();

	}
}
