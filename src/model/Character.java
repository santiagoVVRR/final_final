package model;

public class Character implements Comparable<Character>, Movement{

	
	//Attributes
	
	private int life;
	private int power;
	private String nickName;
	private String image;
	
	
	//Relations
	
	private Character next;
	private Character prev;
	private Stone rootStone;
	
	//More attributes
	
	private int posx;
	private int posy;
	private boolean left;
	private boolean right;
	private boolean down;
	private boolean up;
	
	
	
	public Character(int life, int power, String nickName, String image) {
		super();
		this.life = life;
		this.power = power;
		this.nickName = nickName;
		this.image = image;
		this.next = null;
		this.prev = null;
		this.posx = 346;
		this.posy = 243;
		this.rootStone = null;
	}
	
	

	public int getLife() {
		return life;
	}



	public void setLife(int life) {
		this.life = life;
	}



	public int getPower() {
		return power;
	}



	public void setPower(int power) {
		this.power = power;
	}



	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public Character getNext() {
		return next;
	}



	public void setNext(Character next) {
		this.next = next;
	}



	public Character getPrev() {
		return prev;
	}



	public void setPrev(Character prev) {
		this.prev = prev;
	}



	public Stone getRootStone() {
		return rootStone;
	}



	public void setRootStone(Stone rootStone) {
		this.rootStone = rootStone;
	}



	public int getPosx() {
		return posx;
	}



	public void setPosx(int posx) {
		this.posx = posx;
	}



	public int getPosy() {
		return posy;
	}



	public void setPosy(int posy) {
		this.posy = posy;
	}



	public boolean isLeft() {
		return left;
	}



	public void setLeft(boolean left) {
		this.left = left;
	}



	public boolean isRight() {
		return right;
	}



	public void setRight(boolean right) {
		this.right = right;
	}



	public boolean isDown() {
		return down;
	}



	public void setDown(boolean down) {
		this.down = down;
	}



	public boolean isUp() {
		return up;
	}



	public void setUp(boolean up) {
		this.up = up;
	}



	@Override
	public void move() {
	
		if(left) {
			if(posx > 0) {
				this.posx = MOVEMENT_INDEX;
			}else {
				this.posx = LIMITX;
			}
		}if(right) {
			if(posx < LIMITX) {
				this.posx += MOVEMENT_INDEX;
			}else {
				this.posx = 0;
			}
		}if(up) {
			if(posy > 0) {
				this.posy -= MOVEMENT_INDEX;
			}
		}if(down) {
			if(posy + 110 < LIMITY) {
				this.posy += MOVEMENT_INDEX;
			}
		}
		
	}

	@Override
	public int compareTo(Character c2) {
		return this.getNickName().compareToIgnoreCase(c2.getNickName());
	}

	public boolean take(int a, int b) {
		boolean catched = false;
		
		if(a < posx + 100 && a > posx && b > posy && b < posy + 110) {
			catched = true;
		}
		return catched;
	}
	
	public void grabbStone(Stone s) {
		if(rootStone == null) {
			rootStone = s;
		}else {
			rootStone.addStone(s);
		}
	}
	
	public int getweight() {
		if(rootStone == null) {
			return 0;
		}else {
			return this.rootStone.getWeight();
		}
	}
	
	public Character searchCharacterByName(String name) {
		Character ret = null;
		
		if(this.nickName.equalsIgnoreCase(name)) {
			ret = this;
		}else {
			if(this.next != null) {
				ret = this.next.searchCharacterByName(name);
			}
		}
		return ret;
	}



	@Override
	public String toString() {
		return "Character [life=" + life + ", power=" + power + ", nickName=" + nickName + "]";
	}
	
	
	
	
}
