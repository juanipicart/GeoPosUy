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

	public class FrameConsultaObservaciones implements ActionListener {

		public static void main(String[] args) {

		}
		
		JList<Fenomeno> lista = new JList();		
		JList<Observacion> observaciones = new JList();
		/** Frame de la ventana */
		private JFrame frame;

		/** Atributos de labels */
		private JLabel labelCombo;
		private JLabel labelDocumento;
		private JLabel labelTipoDoc;

		
		private JList<Fenomeno> comboFenomenos;
			
		/** Atributos de Botones */

		private JButton buttonCancelar;
		private JButton buttonBuscar;

		public FrameConsultaObservaciones(JFrame framePadre) {
			
			this.labelCombo = new JLabel("Seleccione uno o más fenómenos");

			JButton buttonBuscar = new JButton("Consultar Observaciones");
			buttonBuscar.addActionListener(this);

			JButton buttonCancelar = new JButton("Cancelar");
			buttonCancelar.addActionListener(this);

			this.buttonBuscar = buttonBuscar;
			this.buttonCancelar = buttonCancelar;
			
			this.initializeFrame(framePadre);
		}
		
		private void initializeFrame(JFrame framePadre) {

			JFrame frame = new JFrame("Consulta de observación/es mediante fenómeno/s");
			frame.setSize(600, 400);
			frame.setResizable(false);
			frame.setLocationRelativeTo(framePadre);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JPanel consultarObservacionesPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets = new Insets(10, 10, 10, 10);
			
			constraints.gridx = 0;
			constraints.gridy = 0;
			consultarObservacionesPanel.add(this.labelCombo, constraints);
			
			try {
				
				constraints.gridx = 0;
				constraints.gridy = 1;
				this.comboFenomenos = cargarComboFenomenos();
				consultarObservacionesPanel.add(this.comboFenomenos, constraints);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.gridwidth = 5;
			constraints.anchor = GridBagConstraints.CENTER;
			consultarObservacionesPanel.add(buttonBuscar, constraints);
			
			consultarObservacionesPanel
					.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Ingrese al menos un criterio de búsqueda"));

			frame.add(consultarObservacionesPanel);

			frame.pack();
			frame.setVisible(true);

			this.frame = frame;

		}				
	        	
		private JList<Fenomeno> cargarComboFenomenos() throws Exception {
			
			//Creo un map para guardar la correspondencia de ids con descripciones
			/*HashMap<String, Long> mapFenomenos = new HashMap<String,Long >();*/
			DefaultListModel<Fenomeno> fenomenos = new DefaultListModel<Fenomeno>();
			
			fenomenos = ClienteGeoPosUy.obtenerComboFenomenos();
			
			
			
			lista.setModel(fenomenos);
			lista.setVisibleRowCount(10);
			lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			
			/*JComboBox<String> combo = new JComboBox<>();

			for (Fenomeno fen : fenomenos) {
				combo.addItem(Long.toString(fen.getId_fenomeno()));
				mapFenomenos.put(fen.getDescripcion(),  fen.getId_fenomeno());
			}*/

			return lista;
		}
	         
	             

		private void accionCancelar() {
			// si se cancela, se eliminar la ventana
			this.frame.dispose();

		
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			/* Debo primero conocer que botón fue clickeado */

			if (e.getSource() == this.buttonCancelar) {
				this.accionCancelar();
			} else {
				this.accionConsultar();
			}	
		}
		
		private void accionConsultar() {
			
			ArrayList<Fenomeno> CapturarLista = new ArrayList();
			
			if(lista.getSelectedValuesList().size() > 0 && lista.getSelectedValuesList() != null) {
				
				CapturarLista = (ArrayList<Fenomeno>) lista.getSelectedValuesList();
				List<Observacion> observacionesLista = new ArrayList<>();
				
				LinkedList<Long> listaIdsFenomenos = new LinkedList<>();


				for(Fenomeno fenomeno : CapturarLista){
					listaIdsFenomenos.add(fenomeno.getId_fenomeno());
				}
				
				
				
				try {
					observacionesLista = ClienteGeoPosUy.buscarObservacionesPorFenomenos(listaIdsFenomenos);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				
			    new FrameListadoObservaciones(this.frame, observacionesLista);
				
			    
			}else {
				
				JOptionPane.showMessageDialog(frame, "Verifique haber seleccionado al menos un fenómeno.", "Ha ocurrido un error!",
						JOptionPane.WARNING_MESSAGE);
				
			}	
		}
	}



