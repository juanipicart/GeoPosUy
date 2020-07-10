package com.java.swing;

	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JOptionPane;
	import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.clases.Fenomeno;
import com.clases.Observacion;
import com.interfaz.ClienteGeoPosUy;

	public class FrameListadoObservaciones implements ActionListener {

		public static void main(String[] args) {

		}
			
		List<Observacion> observaciones = new ArrayList();
		/** Frame de la ventana */
		private JFrame frame;
		
		private JTable tablaObservaciones;

		/** Atributos de labels */
		private JLabel labelListado;

			
		/** Atributos de Botones */

		private JButton buttonVolver;

		public FrameListadoObservaciones(JFrame framePadre,List<Observacion> observaciones) {
			
			//this.labelListado = new JLabel("Listado de observaciones");
			
			JButton buttonVolver = new JButton("Volver");
			buttonVolver.addActionListener(this);
			
			this.observaciones = observaciones;
			
			this.buttonVolver = buttonVolver;
			
			this.initializeFrame(framePadre, observaciones);
		}
		
		private void initializeFrame(JFrame framePadre,List<Observacion> observaciones) {

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
			
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.anchor = GridBagConstraints.CENTER;
			this.tablaObservaciones = this.cargarTablaObservaciones(observaciones);
			
			try {
				
				if (this.tablaObservaciones!=null){
					ListadoObservaciones.add(new JScrollPane(this.tablaObservaciones), constraints);
			
					ListadoObservaciones
							.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lista de Observaciones"
									+ ""));
			
					frame.add(ListadoObservaciones);
			
					//this.textPatente.getDocument().addDocumentListener(this);
					//this.comboTipo.addItemListener(this);
			
					// frame.pack();
					frame.setVisible(true);
			
					this.frame = frame;
				}
				else{
					JOptionPane.showMessageDialog(frame, "Error de conexión con el servidor. Intente más tarde.",
							"Error de conexión!", JOptionPane.WARNING_MESSAGE);
					frame.dispose();
				}
				
				
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

			frame.pack();
			frame.setVisible(true);
			
			this.frame = frame;

		}				
	        
		private JTable cargarTablaObservaciones(List<Observacion> observaciones) {

			List<Fenomeno> fenomenos = new ArrayList<Fenomeno>();
			List<Observacion> observacionesAMostrar; 
			
			observacionesAMostrar = (List<Observacion>) observaciones;

			String[] nombreColumnas = { "FENÓMENO", "DESCRIPCION", "GEOLOCALIZACIÓN", "CRITICIDAD", "FECHA"};

			/*
			 * El tamaño de la tabla es, 6 columnas (cantidad de datos a mostrar) y
			 * la cantidad de filas depende de la cantida de mascotas
			 */
			Object[][] datos = new Object[observacionesAMostrar.size()][5];

			
			
			int fila = 0;

			try {
				fenomenos = ClienteGeoPosUy.ObtenerTodosLosFenomenos();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Observacion o : observacionesAMostrar) {
				
				Fenomeno fen = new Fenomeno();
				for (Fenomeno f : fenomenos) {
		            if(f.getId_fenomeno() == o.getId_fenomeno()) {
		            	
		            	datos[fila][0] = f.getNombre();
		            	
		            }
		        }
				
				
				datos[fila][1] = o.getDescripcion();				
				datos[fila][2] = o.getGeolocalizacion();
				
				if(o.getNivel_criticidad() == 1)
				{
					datos[fila][3] = "BAJO";
					
				}else if(o.getNivel_criticidad() == 2) 
				{
					datos[fila][3] = "MEDIA-BAJA";
					
				}else if(o.getNivel_criticidad() == 3) 
				{
					datos[fila][3] = "MEDIA";
					
				}else if(o.getNivel_criticidad() == 4) 
				{
					datos[fila][3] = "MEDIA-ALTA";
					
				}else if(o.getNivel_criticidad() == 5) 
				{
					datos[fila][3] = "ALTA";
				}
				
				
				datos[fila][4] = o.getFecha_hora();
				
				
				fila++;

			}

			/*
			 * Este codigo indica que las celdas no son editables y que son todas
			 * del tipos String
			 */
			DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return String.class;
				}
			};

			JTable table = new JTable(model);
			table.setAutoscrolls(true);
			table.setCellSelectionEnabled(false);
			table.setSize(2000, 1000);

			this.tablaObservaciones = table;

			return table;

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