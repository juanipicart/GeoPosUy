package com.java.swing;

	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
	import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.clases.Fenomeno;
import com.clases.Observacion;
import com.interfaz.ClienteGeoPosUy;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

	public class FrameConsultaObservaciones implements ActionListener {

		public static void main(String[] args) {

		}
		
		JList<Fenomeno> lista = new JList();		
		JList<Observacion> observaciones = new JList();
		/** Frame de la ventana */
		private JFrame frame;

		/** Atributos de labels */
		private JLabel labelCombo;
		private JLabel fechaDesde;
		private JLabel fechaHasta;
			
		private JList<Fenomeno> comboFenomenos;
		private JDatePickerImpl fieldfechaDesde;
		private JDatePickerImpl fieldfechaHasta;
			
		/** Atributos de Botones */

		private JButton buttonCancelar;
		private JButton buttonBuscar;

		public FrameConsultaObservaciones(JFrame framePadre) {
			
			this.labelCombo = new JLabel("Seleccione uno o más fenómenos presionando la tecla CONTROL");
			this.fechaDesde = new JLabel("Fecha desde");
			this.fechaHasta = new JLabel("Fecha hasta");

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
			frame.setSize(300, 300);
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
			consultarObservacionesPanel.add(this.fechaDesde, constraints);

			constraints.gridy = 3;
			this.fieldfechaDesde = this.createDatePicker();
			consultarObservacionesPanel.add(this.fieldfechaDesde, constraints);
			
			constraints.gridx = 0;
			constraints.gridy = 4;
			consultarObservacionesPanel.add(this.fechaHasta, constraints);

			constraints.gridy = 5;
			this.fieldfechaHasta = this.createDatePicker();
			consultarObservacionesPanel.add(this.fieldfechaHasta, constraints);

			constraints.gridx = 0;
			constraints.gridy = 6;
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
	       
		private JDatePickerImpl createDatePicker() {

			UtilDateModel model = new UtilDateModel();
			JDatePanelImpl datePanel = new JDatePanelImpl(model);
			JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
			return datePicker;
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



