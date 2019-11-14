package model;

import java.util.ArrayList;

public class Stone implements Movement{

	//Attributes
	
	private int power;
	private int posy;
	private int posx;
	
	//Relations
	
	private Stone left;
	private Stone rigth;
	
	public Stone(int power) {
		
		this.power = power;
	}

	
	
	public int getPower() {
		return power;
	}



	public void setPower(int power) {
		this.power = power;
	}



	public int getPosy() {
		return posy;
	}



	public void setPosy(int posy) {
		this.posy = posy;
	}



	public int getPosx() {
		return posx;
	}



	public void setPosx(int posx) {
		this.posx = posx;
	}



	public Stone getLeft() {
		return left;
	}



	public void setLeft(Stone left) {
		this.left = left;
	}



	public Stone getRigth() {
		return rigth;
	}



	public void setRigth(Stone rigth) {
		this.rigth = rigth;
	}



	@Override
	public void move() {
		if(this.posy > LIMITY) {
			this.posy = (int)(Math.random() * -200) - 10;
			int random = (int)(Math.random() * LIMITX) + 5;
			this.posx = random;
		}
		this.posy += MOVEMENT_INDEX_STONE;
	}
	
	
	public void addStone(Stone newStone) {
		if(this.getPower() < newStone.getPower()) {
			if(rigth == null) {
				rigth = newStone;
			}else {
				rigth.addStone(newStone);
			}
		}else if(this.power > newStone.power) {
			if(left == null) {
				left = newStone;
			}else {
				left.addStone(newStone);
			}
		}
	}
	
	public Stone searchStone(int power) {
		if(this.power == power) {
			return this;
		}else if(this.power > power) {
			return (left == null) ? null : left.searchStone(power);
		}else {
			return (rigth == null) ? null : rigth.searchStone(power);
		}
	}
	
	public int getWeight() {
		int q1 = (left == null) ? 0 : left.getWeight();
		int q2 = (rigth == null) ? 0 : left.getWeight();
		return 1 + q1 + q2;
	}
	
	
	public void showStonesList(ArrayList<Stone> l) {
		if(left != null) {
			left.showStonesList(l);
		}
		l.add(this);
		if(rigth != null) {
			rigth.showStonesList(l);
		}
	}
	
	
}
