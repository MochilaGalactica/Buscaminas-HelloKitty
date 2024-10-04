package entity;

public class Casilla {

	private int x;
	private int y;
	
	private boolean mina;
	
	public Casilla(int x, int y) {
		this.x = x;
		this.y = y;
		this.mina = false;
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

	public void setMina(boolean mina) {
		this.mina = mina;
	}
	
	public void colocarMina() {
		this.mina = true;
	}
	
}
