package com.java.swing;

	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.HeadlessException;
	import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;
	import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
	import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import com.clases.Fenomeno;
import com.clases.Observacion;
import com.clases.Usuario;
import com.clases.codigueras.TipoDocumento;
import com.interfaz.ClienteGeoPosUy;

	public class FrameListadoObservaciones implements ActionListener {

		public static void main(String[] args) {

		}
			
		JList<Observacion> observaciones = new JList();
		/** Frame de la ventana */
		private JFrame frame;

		/** Atributos de labels */
		private JLabel labelListado;

			
		/** Atributos de Botones */

		private JButton buttonVolver;

		public FrameListadoObservaciones(JFrame framePadre,JList<Observacion> observaciones) {
			
			//this.labelListado = new JLabel("Listado de observaciones");

			JButton buttonVolver = new JButton("Volver");
			buttonVolver.addActionListener(this);
			
			this.observaciones = observaciones;
			
			this.buttonVolver = buttonVolver;
			
			this.initializeFrame(framePadre);
		}
		
		private void initializeFrame(JFrame framePadre) {

			JFrame frame = new JFrame("Listado de observaciones");
			frame.setSize(600, 300);
			frame.setResizable(false);
			frame.setLocationRelativeTo(framePadre);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JPanel ListadoObservaciones = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridwidth = GridBagConstraints.REMAINDER;
			//constraints.anchor = GridBagConstraints.WEST;
			constraints.insets = new Insets(10, 10, 10, 10);
			
			//constraints.gridx = 0;
			//constraints.gridy = 0;
			//ListadoObservaciones.add(this.labelListado, constraints);
			
			try {
				
				constraints.gridx = 0;
				constraints.gridy = 0;
				constraints.anchor = GridBagConstraints.CENTER;
				ListadoObservaciones.add(this.observaciones, constraints);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.anchor = GridBagConstraints.CENTER;
			ListadoObservaciones.add(buttonVolver, constraints);
			
			ListadoObservaciones
					.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Listado filtrado por fenómeno/s"));

			frame.add(ListadoObservaciones);

			//frame.pack();
			frame.setVisible(true);
			frame.pack();
			
			this.frame = frame;

		}				
	        
	         
	             

		private void accionCancelar() {
			// si se cancela, se eliminar la ventana
			this.frame.dispose();

		
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			/* Debo primero conocer que botón fue clickeado */

			if (e.getSource() == this.buttonVolver) {
				this.accionCancelar();
			} else {
				//this.accionConsultar();
			}	
		}
		
	}