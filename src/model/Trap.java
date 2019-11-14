package model;

public class Trap implements Movement,Comparable<Trap>{

	//Attributes
	
	private int damage;
	private int x;
	private int y;
	
	public Trap(int damage, int x, int y) {
		
		this.damage = damage;
		this.x = x;
		this.y = y;
	}
	
	

	public int getDamage() {
		return damage;
	}



	public void setDamage(int damage) {
		this.damage = damage;
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



	@Override
	public int compareTo(Trap t2) {
		int comp;
		if(this.damage < t2.damage) {
			comp = -1;
		}else if(this.damage > t2.damage) {
			comp = 1;
		}else {
			comp = 0;
		}
		return comp;
	}

	@Override
	public void move() {
		if(this.y > LIMITY) {
			this.y = (int)(Math.random() * -40) - 10;
			this.x = (int)(Math.random() * LIMITX) + 5;
		}
		this.y += MOVEMENT_INDEX_TRAP;
	}

}
