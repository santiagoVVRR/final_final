package model;

public class Enemy extends Trap{

	private int numStone;
	
	public Enemy(int damage, int x, int y, int numStone) {
		super(damage, x, y);
		this.numStone = numStone;
	}

	public int getNumStone() {
		return numStone;
	}

	public void setNumStone(int numStone) {
		this.numStone = numStone;
	}

	
	
	
	
}
