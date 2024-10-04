package entity;

import java.util.ArrayList;

public class Tablero {

	private Casilla [][] casillas;
	
	private int numFilas;
	private int numColumnas;
	
	public Tablero(int numFilas, int numColumnas) {
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
	}
	
	public void crearTablero () {
		this.casillas = new Casilla[numFilas][numColumnas];
		
		for (int x = 0;x < casillas.length; x++) {
			for (int y = 0; y < casillas[x].length; y++) {
				this.casillas[x][y] = new Casilla(x, y);
			}
		}
	}
	
	public void cargarMinas(int numMinas) {
		int minasColocadas = 0;
		
		while(minasColocadas < numMinas) {
			
			int posicionX = (int) (Math.random() * this.numFilas);
			int posicionY = (int) (Math.random() * this.numColumnas);
			
			Casilla casilla = casillas[posicionX][posicionY];
			
			if (!casilla.isMina()) {
				casillas[posicionX][posicionY].colocarMina();
				System.out.println("Mina colocada en: " + posicionX + "," + posicionY);
				minasColocadas++;
			}
		}
	}
	
	public int detectarMinas(Casilla casilla) {
		int posicionX = casilla.getX();
		int posicionY = casilla.getY();
		
		int minas = 0;
		ArrayList<Casilla> lista = new ArrayList<>();
		
		// Posición 1
		if(posicionX - 1 > 0 && posicionY - 1 > 0) {
			lista.add(new Casilla(posicionX - 1, posicionY - 1));
		}
		
		// Posición 2
		if(posicionX - 1 > 0) {
			lista.add(new Casilla(posicionX - 1, posicionY));			
		}
		
		// Posición 3
		if(posicionX - 1 > 0 && posicionY + 1 < numColumnas) {
			lista.add(new Casilla(posicionX - 1, posicionY + 1));			
		}
		
		// Posición 4
		if(posicionY + 1 < numColumnas) {
			lista.add(new Casilla(posicionX, posicionY + 1));
		}
		
		// Posición 5
		if(posicionX + 1 < numFilas && posicionY + 1 < numColumnas) {
			lista.add(new Casilla(posicionX + 1, posicionY + 1));
		}
		
		// Posición 6
		if(posicionX + 1 < numFilas) {
			lista.add(new Casilla(posicionX + 1, posicionY));
		}
		
		// Posición 7
		if(posicionX + 1 < numFilas && posicionY - 1 > 0 ) {
			lista.add(new Casilla(posicionX + 1, posicionY - 1));
		}
		
		// Posición 8
		if(posicionY - 1 > 0) {
			lista.add(new Casilla(posicionX, posicionY - 1));
		}
		
		for (Casilla posibleMina : lista) {
            if(posibleMina.isMina()) {
            	minas++;
            }
        }
		
		return minas;
	}
}
