package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonListener;

import entity.Casilla;
import entity.Tablero;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Juego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel cabecera;
	private JPanel tablero;
	private JButton mainButton;
	private JButton[][] botonesTablero;
	
	private Tablero tableroMinas;
	
	private ImageIcon mainIconoNormal;
    private ImageIcon mainIconoHover;
    private ImageIcon mainIconoVictoria;
    private ImageIcon mainIconoGameOver;
	
	private int numFilas = 9;
	private int numColumnas = 9;
	private int numMinas = 9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego frame = new Juego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Juego() {
		cargarControles();
		iniciarPartida();
	}
	
	private void iniciarPartida() {
		tableroMinas = new Tablero(numFilas, numColumnas);
		tableroMinas.crearTablero();
		tableroMinas.cargarMinas(numMinas);
		
		reiniciarTablero();
	}
	
	private void cargarControles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Cabecera
		cabecera = new JPanel();
		cabecera.setBounds(10, 11, 414, 101);
		contentPane.add(cabecera);
		cabecera.setLayout(null);
		
		mainButton = new JButton();
		mainButton.setBounds(161, 23, 89, 55);
		mainButton.setText(null);  // No mostrar texto
		mainButton.setBorderPainted(false);  // No mostrar borde
		mainButton.setContentAreaFilled(false);  // No pintar el área de contenido
		mainButton.setFocusPainted(false);  // No mostrar el foco
		mainButton.setOpaque(false);  // No hacer el botón opaco
		
		mainIconoNormal = new ImageIcon("resources/hellokitty_mini.png");
		mainIconoHover = new ImageIcon("resources/hellokitty_hover.png");
		mainIconoGameOver = new ImageIcon("resources/hellokitty_muerta.png");
		mainIconoVictoria = new ImageIcon("resources/hellokitty_victoria.png");
		
		// Hacer click en el botón
		mainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainButtonClick();
			}
		});
		
		cabecera.add(mainButton);
		
		// Tablero
		tablero = new JPanel();
		tablero.setBounds(10, 123, 414, 427);
		contentPane.add(tablero);
		tablero.setLayout(new GridLayout(9, 9, 0, 0));
		
		botonesTablero = new JButton[numFilas][numColumnas];
		for(int i = 0; i < botonesTablero.length; i++) {
			for(int j = 0; j < botonesTablero[i].length; j++) {
				botonesTablero[i][j] = new JButton();
				tablero.add(botonesTablero[i][j]);
			}
		}
		
	}
	
	private void reiniciarTablero() {
		mainButton.setIcon(new ImageIcon("resources/hellokitty_mini.png"));
		
		// Agregar un MouseListener para cambiar el icono al pasar el ratón
		mainButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mainButton.setIcon(mainIconoHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	mainButton.setIcon(mainIconoNormal);
            }
        });
		
		for(int i = 0; i < botonesTablero.length; i++) {
			for(int j = 0; j < botonesTablero[i].length; j++) {
				botonesTablero[i][j].setEnabled(true);
				botonesTablero[i][j].setText("");
				botonesTablero[i][j].setBackground(getBackground());
				botonesTablero[i][j].setIcon(null);
				
				final int x = i;
                final int y = j;
                
                Casilla casilla = tableroMinas.getCasillas()[x][y];
                // Evento para descubrir la casilla
				botonesTablero[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {						
						casillaClick(casilla);
					}
				});
				
				// Evento para añadir bandera
				botonesTablero[i][j].addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseReleased(MouseEvent e) {
		                if (e.getButton() == MouseEvent.BUTTON3) {
		                    colocarBandera(casilla);
		                }
		            }
		        });
			}
		}
	}
	
	private void mainButtonClick() {
		iniciarPartida();
	}
	
	private void casillaClick(Casilla casilla) {
		int x = casilla.getX();
		int y = casilla.getY();
		
		JButton boton = botonesTablero[x][y];
		boton.setEnabled(false);
		
		if(casilla.isMina()) {
			boton.setBackground(new Color(255, 0, 0));
			gameOver();
		}else {
			abrirCasillasAlrededor(casilla);
			
			if(tableroMinas.checkVictoria()) {
				victoria();
			}
			
		}
	}
	
	private void colocarBandera(Casilla casilla) {
		if(!casilla.isAbierta()) {
			if(casilla.isBandera()) {
				casilla.setBandera(false);
				botonesTablero[casilla.getX()][casilla.getY()].setIcon(null);
			}else {
				casilla.setBandera(true);
				botonesTablero[casilla.getX()][casilla.getY()].setIcon(new ImageIcon("resources/corazon.png"));
			}
		}
		
	}
	
	private void gameOver() {
		bloquearBotones();
		
		mainButton.setIcon(mainIconoGameOver);
		
		mostrarMinas();
		JOptionPane.showMessageDialog(null, "Has perdido =(");
	}
	
	private void victoria() {
		bloquearBotones();
		
		mainButton.setIcon(mainIconoVictoria);
		
		mostrarMinas();
		JOptionPane.showMessageDialog(null, "Has ganado =)");
	}
	
	private void bloquearBotones() {
		
		// Quitar eventos botón principal
		for (MouseListener listener : mainButton.getMouseListeners()) {
			if(listener instanceof BasicButtonListener) {
				
			}else {
				mainButton.removeMouseListener(listener);
			}
        }
		
		// Quitar eventos botones tablero
		for(int i = 0; i < botonesTablero.length; i++) {
			for(int j = 0; j < botonesTablero[i].length; j++) {
				for (ActionListener al : botonesTablero[i][j].getActionListeners()) {
					botonesTablero[i][j].removeActionListener(al);
		        }
				
				for (MouseListener listener : botonesTablero[i][j].getMouseListeners()) {
					if(listener instanceof BasicButtonListener) {
						
					}else {
						botonesTablero[i][j].removeMouseListener(listener);
					}
		        }
			}
		}
	}
	
	private void mostrarMinas() {
		for(int i = 0; i < botonesTablero.length; i++) {
			for(int j = 0; j < botonesTablero[i].length; j++) {
				if(tableroMinas.getCasillas()[i][j].isMina()) {
					botonesTablero[i][j].setIcon(new ImageIcon("resources/fresa.png"));
				}
			}
		}
	}
	
	private void abrirCasillasAlrededor(Casilla casilla) {
		if(!casilla.isAbierta()) {
			
			casilla.abrirCasilla();
			botonesTablero[casilla.getX()][casilla.getY()].setEnabled(false);
			botonesTablero[casilla.getX()][casilla.getY()].setIcon(null);
			botonesTablero[casilla.getX()][casilla.getY()].setBackground(null);
			
			int numMinasAlrededor = tableroMinas.detectarMinas(casilla);
			
			if(numMinasAlrededor == 0) {
				ArrayList<Casilla> casillasAlrededor = tableroMinas.casillasDisponibles(casilla.getX(), casilla.getY());
				ArrayList<Casilla> casillasAlrededorDisponibles = new ArrayList<>();
				
				for (Casilla casillaAlrededor : casillasAlrededor) {
		            if(!casillaAlrededor.isAbierta()) {
		            	casillasAlrededorDisponibles.add(casillaAlrededor);
		            }
		        }
				
				for (Casilla casillaParaAbrir : casillasAlrededorDisponibles) {
					abrirCasillasAlrededor(casillaParaAbrir);
				}
			}else {
				botonesTablero[casilla.getX()][casilla.getY()].setText(Integer.toString(numMinasAlrededor));
			}
		}
	}
}
