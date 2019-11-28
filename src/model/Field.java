package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Field implements Comparable<Field>{

	//Attributes
	
	private String name;
	private String image;
	
	//Relations
	
	private Field next;
	private Field prev;
	private Stone rootStone;
	private ArrayList<Trap> traps;
	private Timer timer;
	
	
	
	public Field(String name, String image) {
		this.name = name;
		this.image = image;
		traps = new ArrayList<Trap>();
		generateTraps();
		sortTrapsByDamage();
		timer = new Timer();
	}


	

	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public Field getNext() {
		return next;
	}




	public void setNext(Field next) {
		this.next = next;
	}




	public Field getPrev() {
		return prev;
	}




	public void setPrev(Field prev) {
		this.prev = prev;
	}




	public Stone getRootStone() {
		return rootStone;
	}




	public void setRootStone(Stone rootStone) {
		this.rootStone = rootStone;
	}




	public ArrayList<Trap> getTraps() {
		return traps;
	}




	public void setTraps(ArrayList<Trap> traps) {
		this.traps = traps;
	}




	public Timer getTimer() {
		return timer;
	}




	public void setTimer(Timer timer) {
		this.timer = timer;
	}


	@Override
	public int compareTo(Field o) {
		int comp;
		if(this.name.compareToIgnoreCase(o.getName()) < 0) {
			comp = -1;
		}else if(this.name.compareToIgnoreCase(o.getName()) > 0) {
			comp = 1;
		}else {
			comp = 0;
		}
		return comp;
	}

	
	public void generateTraps() {
		
		int numOfPoisons = (int)(Math.random() * 7) + 2;
		
		for(int i = 0; i < numOfPoisons; i++) {
			int damage = (int)(Math.random() * 200) + 30;
			int x = (int)(Math.random() * 500) + 3;
			int y = (int)(Math.random() * -200) - 10;
			int radius = (int)(Math.random() * 20) + 1;
			Poison p = new Poison(damage,x,y,radius);
			traps.add(p);
		}
		
		int numOfEnemys = (int)(Math.random() * 7) + 2;
		
		for(int i = 0; i < numOfEnemys; i++) {
			int damage = (int)(Math.random() * 200) + 30;
			int x = (int)(Math.random() * 500) + 3;
			int y = (int)(Math.random() * -200) - 10;
			int numStone = (int)(Math.random() * 3) + 1;
			Enemy e = new Enemy(damage,x,y,numStone);
			traps.add(e);
		}
	}
	
	public void addStone(int s) {
		Stone t = new Stone(s);
		if(rootStone != null) {
			rootStone.addStone(t);
		}else {
			rootStone = t;
		}
	}
	
	public void sortTrapsByDamage(){
		for(int i = 0; i < traps.size(); i++) {
			int c = i;
			Trap g = traps.get(i);
			for(int j = i+1; j < traps.size(); j++) {
				if(g.compareTo(traps.get(j)) < 0) {
					g = traps.get(j);
					c = j;
				}
			}
			Trap temp = traps.get(i);
			traps.set(i, g);
			traps.set(c, temp);
		}
	}
	
	public Stone searchStone(int power) {
		if(this.rootStone == null) {
			return null;
		}else {
			return rootStone.searchStone(power);
		}
	}
	
	public ArrayList<Stone> showListOfStones(){
		ArrayList<Stone> o = new ArrayList<>();
		if(rootStone == null) 
			rootStone.showStonesList(o);
		return o;
	}
	
	public Field searchField(String name) {
		Field ret = null;
		if(this.name.equals(name)) {
			ret = this;
		}else {
			ret = this.next.searchField(name);
		}
		return ret;
	}
	
	public void loadStones() {
		FileWriter fo;
		try {
			fo = new FileWriter("data/Stones.txt");
			BufferedWriter b = new BufferedWriter(fo);
			int i = 0;
			while(i < showListOfStones().size()) {
				b.write("Stone " +i +" "+ showListOfStones().get(i).getPower());
				b.newLine();
				i++;
			}
			b.flush();
			fo.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
