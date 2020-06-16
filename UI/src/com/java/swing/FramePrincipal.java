package com.java.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.clases.Usuario;

public class FramePrincipal {

	private static Usuario usuario;
	
	private static JButton botonRegistrarFenomeno;
	private static JButton botonRegistrarObservacion;
	private static JButton botonRegistrarUsuario;
	private static JButton botonModificarUsuario;
	private static JButton botonEliminarUsuario;

		
	public FramePrincipal(Usuario user) {
		usuario = user;
	}
	
		private static void createAndShowGUI() {

		
		JFrame frame = new JFrame("GeoPosUY");
		
		frame.setSize(200, 200);
		frame.setResizable(false);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLUE);
		
		initializeMenuBar(frame);
		initializeCentralPanel(frame);

		// Display the window.
		frame.setVisible(true);
		}
		
		//Inicializamos la barra del menú superior
		
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
		
		//Agrego la opción USUARIOS al menu
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
			
			nuevoUsuario.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameCargarUsuario(frame);
	            	
	            }
	        });
			
			JMenuItem darDeBajaUsuario = new JMenuItem("Dar de baja usuario");
			
			nuevoUsuario.addActionListener(new ActionListener() {
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
			
			JMenuItem nuevaObservacion = new JMenuItem("Nueva Observación");
			
			nuevaObservacion.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameAltaObservacion(frame);
	            	
	            }
	        });
		
			
			/*JMenuItem listarObservaciones = new JMenuItem("Listar Observaciones");
			
			listarObservaciones.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameListarObservaciones(frame);
	            	
	            }
	        });*/

			observaciones.add(nuevaObservacion);
			//observaciones.add(listarObservaciones);
			
			menuBar.add(observaciones);
			
		}
		
		private static void initializeMenuFenomenos(JMenuBar menuBar, final JFrame frame) {
			
			JMenu fenomenos = new JMenu("Fenómenos");
			
			JMenuItem nuevoFenomeno = new JMenuItem("Nuevo Fenómeno");
			
			nuevoFenomeno.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameAltaFenomeno(frame);
	            	
	            }
	        });
			JMenuItem modifFenomeno = new JMenuItem("Modificar Fenómeno");
			
			modifFenomeno.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                
	            	new FrameCargarFenomeno(frame);
	            	
	            }
	        });
			
			JMenuItem bajaFenomeno = new JMenuItem("Eliminar Fenómeno");
			
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
		private static void initializeCentralPanel(JFrame frame) {
			
		botonRegistrarFenomeno = new JButton("Registrar fenómeno");
		botonRegistrarFenomeno.setFont(new Font("Dialog",Font.BOLD,12));
		botonRegistrarFenomeno.setSize(50, 10);
	
		botonRegistrarObservacion = new JButton("Registrar observación");
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
	    } else {
	    	constraints.gridx = 0;
		    constraints.gridy = 0;
		    constraints.gridwidth = 1;
		    panelCentroSistema.add (botonRegistrarObservacion);	    	
	    }
   
	    frame.add(panelCentroSistema);
	    frame.pack();
	    frame.setVisible(true);
	    
	    botonRegistrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
             
                              		
            	new FrameAltaUsuario(frame);
            	
            }
        });
	    
	    botonRegistrarObservacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameAltaObservacion(frame);
            	
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
	 
	}
		
		public void setVisible(boolean b) {
			createAndShowGUI();
		}
		
		
}


