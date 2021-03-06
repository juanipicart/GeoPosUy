package com.java.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import com.clases.Usuario;

public class FramePrincipal {

	private static Usuario usuario;
	
	/*private static JButton botonRegistrarFenomeno;
	private static JButton botonRegistrarObservacion;
	private static JButton botonRegistrarUsuario;
	private static JButton botonModificarUsuario;
	private static JButton botonEliminarUsuario;
	private static JButton botonListarObservaciones;*/	

		
	public FramePrincipal(Usuario user) {
		usuario = user;
			
	}
	
	public void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
				}
			});
		}
		
		private static void createAndShowGUI() {

		
		JFrame frame = new JFrame("GeoPosUY");
		
		frame.setSize(800, 450);
		frame.setResizable(false);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initializeMenuBar(frame);
		//initializeCentralPanel(frame);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.WHITE);
		// Display the window.
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setSize(800,600);
		lblNewLabel.setIcon(new ImageIcon(FramePrincipal.class.getResource("/resources/fondo.jpg")));
		panel.add(lblNewLabel);
		frame.setVisible(true);
		}
		
		//Inicializamos la barra del men� superior
		
		private static void initializeMenuBar(JFrame frame) {
			
			JMenuBar menuBar = new JMenuBar();
			
			if (usuario.getRol() == 1) {
				initializeMenuUsuarios(menuBar, frame);
				initializeMenuObservaciones(menuBar, frame);
				initializeMenuFenomenos(menuBar, frame);
			} else {
				initializeMenuObservaciones(menuBar, frame);
			}
						
			frame.setJMenuBar(menuBar);

		}
		
		//Agrego la opci�n USUARIOS al menu
		private static void initializeMenuUsuarios(JMenuBar menuBar, final JFrame frame) {
			
			JMenu usuarios = new JMenu("Usuarios");
			
			JMenuItem nuevoUsuario = new JMenuItem("Nuevo Usuario");
			
			nuevoUsuario.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameAltaUsuario(frame);
	            	
	            }
	        });
			
			JMenuItem modificarUsuario = new JMenuItem("Modificar Usuario");
			
			modificarUsuario.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameCargarUsuario(frame);
	            	
	            }
	        });
			
			JMenuItem darDeBajaUsuario = new JMenuItem("Dar de baja usuario");
			
			darDeBajaUsuario.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameBajaUsuario(frame, usuario);
	            	
	            }
	        });


			usuarios.add(nuevoUsuario);
			usuarios.add(modificarUsuario);
			usuarios.add(darDeBajaUsuario);
			menuBar.add(usuarios);
			
		}
		
		private static void initializeMenuObservaciones(JMenuBar menuBar, final JFrame frame) {
			
			JMenu observaciones = new JMenu("Observaciones");
			
			JMenuItem nuevaObservacion = new JMenuItem("Nueva Observaci�n");
			
			nuevaObservacion.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameAltaObservacion(frame, usuario);
	            	
	            }
	        });
		
			
			JMenuItem listarObservaciones = new JMenuItem("Buscar Observaciones");
			
			listarObservaciones.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameConsultaObservaciones(frame);
	            	
	            }
	        });

			observaciones.add(nuevaObservacion);
			observaciones.add(listarObservaciones);
			
			menuBar.add(observaciones);
			
		}
		
		private static void initializeMenuFenomenos(JMenuBar menuBar, final JFrame frame) {
			
			JMenu fenomenos = new JMenu("Fen�menos");
			
			JMenuItem nuevoFenomeno = new JMenuItem("Nuevo Fen�meno");
			
			nuevoFenomeno.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameAltaFenomeno(frame);
	            	
	            }
	        });
			JMenuItem modifFenomeno = new JMenuItem("Modificar Fen�meno");
			
			modifFenomeno.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameCargarFenomeno(frame);
	            	
	            }
	        });
			
			JMenuItem bajaFenomeno = new JMenuItem("Eliminar Fen�meno");
			
			bajaFenomeno.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameBajaFenomeno(frame);
	            	
	            }
	        });

			fenomenos.add(nuevoFenomeno);
			fenomenos.add(modifFenomeno);
			fenomenos.add(bajaFenomeno);
			menuBar.add(fenomenos);
		}
			
		//Accesos directos
		/*private static void initializeCentralPanel(JFrame frame) {
			
		botonRegistrarFenomeno = new JButton("Registrar fen�meno");
		botonRegistrarFenomeno.setFont(new Font("Dialog",Font.BOLD,12));
		botonRegistrarFenomeno.setSize(50, 10);
	
		botonRegistrarObservacion = new JButton("Registrar observaci�n");
		botonRegistrarObservacion.setFont (new Font("Dialog",Font.BOLD,12));
		botonRegistrarObservacion.setSize(50, 10);
		
		botonRegistrarUsuario = new JButton("Nuevo usuario");
		botonRegistrarUsuario.setFont (new Font("Dialog",Font.BOLD,12));
		botonRegistrarUsuario.setSize(50, 10);
		
		botonModificarUsuario = new JButton("Modificar usuario");
		botonModificarUsuario.setFont (new Font("Dialog",Font.BOLD,12));
		botonModificarUsuario.setSize(50, 10);
		
		botonEliminarUsuario = new JButton("Eliminar usuario");
		botonModificarUsuario.setFont (new Font("Dialog",Font.BOLD,12));
		botonModificarUsuario.setSize(50, 10);
		
		botonListarObservaciones = new JButton("Consultar observaciones");	
		botonListarObservaciones.setFont(new Font("Dialog",Font.BOLD,12));	
		botonListarObservaciones.setSize(50, 10);
		
	    JPanel panelCentroSistema = new JPanel (new GridBagLayout());
	    GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
	    panelCentroSistema.setBorder(new EmptyBorder(30,30,30,30));
	    
	    if (usuario.getRol() == 1) {
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.gridwidth = 1;
	    panelCentroSistema.add (botonRegistrarFenomeno, constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    constraints.gridwidth = 1;
	    panelCentroSistema.add (botonRegistrarObservacion);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.gridwidth = 1;
	    
	    panelCentroSistema.add (botonRegistrarUsuario);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 3;
	    constraints.gridwidth = 1;
	    
	    panelCentroSistema.add (botonModificarUsuario);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 4;
	    constraints.gridwidth = 1;
	    
	    panelCentroSistema.add (botonEliminarUsuario); 
	    
	    constraints.gridx = 0;	
	    constraints.gridy = 5;	
	    constraints.gridwidth = 1;	
	    	
	    panelCentroSistema.add (botonListarObservaciones); 
	    } else {
	    	constraints.gridx = 0;
		    constraints.gridy = 0;
		    constraints.gridwidth = 1;
		    panelCentroSistema.add (botonRegistrarObservacion);	    	
	    }
   
	    frame.add(panelCentroSistema);
	    frame.pack();
	    frame.setVisible(true);
	    
	   /* botonRegistrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
             
                              		
            	new FrameAltaUsuario(frame);
            	
            }
        });
	    
	    botonRegistrarObservacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameAltaObservacion(frame, usuario);
            	
            }
        });
	    
	    botonRegistrarFenomeno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameAltaFenomeno(frame);
            	
            	
            	
            }
        });
	    
	    botonModificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameCargarUsuario(frame);          	
            	
            	
            }
        });
	    
	    botonEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameBajaUsuario(frame, usuario);          	
            	
            	
            }
        });
	    
	    botonListarObservaciones.addActionListener(new ActionListener() {	
            @Override	
            public void actionPerformed(ActionEvent event) {	
                	
            	new FrameConsultaObservaciones(frame);          		
            		
            		
            }	
        });
	 */
	
		
		public void setVisible(boolean b) {
			createAndShowGUI();
		}
		
		
}


