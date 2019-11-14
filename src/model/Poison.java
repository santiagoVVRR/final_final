package model;

public class Poison extends Trap{

	
	private int radius;
	
	
	public Poison(int damage, int x, int y, int radius) {
		super(damage, x, y);
		this.radius = radius;
	}


	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}

	
	
	
	
	
	
}
