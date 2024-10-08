package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Buscaminas de la Hello Kitty");
		setIconImage(new ImageIcon("resources/hellokitty.png").getImage());
		setResizable(false);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Buscaminas de la Hello Kitty");
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTitulo.setBounds(185, 36, 248, 50);
		contentPane.add(lblTitulo);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(185, 107, 248, 265);
		contentPane.add(panelButtons);
		panelButtons.setLayout(new GridLayout(4, 1, 0, 5));
		
		JButton facilButton = new JButton("Facil");
		panelButtons.add(facilButton);
		facilButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarJuego("facil");
			}
		});
		
		JButton normalButton = new JButton("Normal");
		panelButtons.add(normalButton);
		normalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarJuego("normal");
			}
		});
		
		JButton dificilButton = new JButton("Dificil");
		panelButtons.add(dificilButton);
		dificilButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarJuego("dificil");
			}
		});
		
		JButton personalizadoButton = new JButton("Personalizado");
		panelButtons.add(personalizadoButton);
		personalizadoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarJuego("personalizado");
			}
		});
	}
	
	private void iniciarJuego(String tipo) {
		Juego juego;
		
		switch(tipo) {
		case "facil":
			juego = new Juego(450, 600, 8, 8, 10);
			juego.run();
			break;
		case "normal":
			juego = new Juego(800, 1000, 16, 16, 40);
			juego.run();
			break;
			
		case "dificil":
			juego = new Juego(1400, 1000, 16, 30, 99);
			juego.run();
			break;
			
		case "personalizado":
			JOptionPane.showMessageDialog(null, "Aún no está implementado jiji");
			break;
		}
	}
}
