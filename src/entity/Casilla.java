package entity;

public class Casilla {

	private int x;
	private int y;
	
	private boolean mina;
	private boolean abierta;
	private boolean bandera;
	
	public Casilla(int x, int y) {
		this.x = x;
		this.y = y;
		this.mina = false;
		this.abierta = false;
		this.bandera = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isMina() {
		return mina;
	}
	
	public boolean isAbierta() {
		return abierta;
	}
	
	public boolean isBandera() {
		return bandera;
	}
	
	public void colocarMina() {
		this.mina = true;
	}
	
	public void abrirCasilla() {
		this.abierta = true;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
	
	
}
