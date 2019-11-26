package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Comparator;

import exceptions.*;

public class Index implements Comparator<User>{

	//Attributes
	
	private ArrayList<User> users;
	private Field upField;
	private Character upCharacter;
	private Character characterChoose;
	private Field fieldChoose;
	private User userChoose;
	
	
	public Index() {
		File f = new File("files/Users.dat");
		if(f.exists() == false) {
			users = new ArrayList<User>();
		}else {
			users = recoverUsers();
		}
		loadCharactersFile();
		loadFieldsFile();
		charactersCircularList();
		characterChoose = upCharacter;
		fieldChoose = upField;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public Field getUpField() {
		return upField;
	}

	public void setUpField(Field upField) {
		this.upField = upField;
	}

	public Character getUpCharacter() {
		return upCharacter;
	}

	public void setUpCharacter(Character upCharacter) {
		this.upCharacter = upCharacter;
	}

	public User getUserChoose() {
		return userChoose;
	}

	public void setUserChoose(User userChoose) {
		this.userChoose = userChoose;
	}
	
	public Field visualizeNextField(Field current) {
		return current.getNext();
	}
	
	public Character visualizePrevCharacter(Character current) {
		return current.getPrev();
	}
	
	
	
	public void visualizeNextCharacter() {
		this.characterChoose = this.characterChoose.getNext();
	}
	
	public void visualizePrevCharacter() {
		this.characterChoose = this.characterChoose.getPrev();
	}
	
	public void visualizeNextField() {
		this.fieldChoose = this.fieldChoose.getNext();
	}
	
	public void visualizePrevField() {
		this.fieldChoose = this.fieldChoose.getPrev();
	}
	
	public void registerUsers(String name) throws UserIsAlreadyTaken, NickNameIsNotValid{
		if(name.length() < 3) {
			throw new NickNameIsNotValid();
		}
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getName().equalsIgnoreCase(name)) {
				throw new UserIsAlreadyTaken();
			}
		}
		User added = new User(name,0);
		users.add(added);
		this.userChoose = added;
	}
	
	public void sortUsersByNameBubble() {
		for(int i = 0; i < users.size(); i++) {
			for(int j = users.size()-1; j >= i; j--) {
				if(users.get(j).compareTo(users.get(j-1)) < 0) {
					User auxiliar = users.get(j);
					users.set(j, users.get(j-1));
					users.set(j-1, auxiliar);
				}
			}
		}
	}
	
	public void sortUsersByScore() {
		for(int i = 0; i < users.size(); i++) {
			int c = i;
			User big = users.get(i);
			for(int j = i+1; j < users.size(); j++) {
				if(compare(big, users.get(j)) < 0) {
					big = users.get(j);
					c = j;
				}
			}
			User temp = users.get(i);
			users.set(i, big);
			users.set(c, temp);
		}
	}
	
	public void sortUsersByBestGameInsertion() {
		for(int i = 1; i < users.size();i++) {
			for(int j = users.size()-1; j >= i; j--) {
				if(users.get(j).getBestGame().compareTo(users.get(j-1).getBestGame()) > 0) {
					User auxiliar = users.get(j);
					users.set(j, users.get(j-1));
					users.set(j-1, auxiliar);
				}
			}
		}
	}
	
	public void sortUsersFistGameInsertion() {
		for(int i = 1; i<users.size();i++) {
			for(int j = 1;(j>0) && (users.get(j-1).getFirstGame().compareTo(users.get(j).getFirstGame())> 0); j--){
				User auxiliar = users.get(j);
				users.set(j, users.get(j-1));
				users.set(j-1, auxiliar);
			}
		}
	}
	
	public void sortUsersLastGameInsertion() {
		for(int i = 1; i<users.size();i++) {
			for(int j = 1;(j>0) && (users.get(j-1).getLastGame().compareTo(users.get(j).getLastGame())> 0); j--){
				User auxiliar = users.get(j);
				users.set(j, users.get(j-1));
				users.set(j-1, auxiliar);
			}
		}
	}
	
	/*
	 * Method writeUsersNames
	 * returns a String with all users information
	 * @return c - list of Users with their names and scores
	 */
	public String writeUsersNames() {
		String c = "";
		for(int i = 0; i < users.size(); i++) {
			c += users.get(i).getName() + "\t" + users.get(i).getScore() + ",";
		}
		return c;
	}
	
	public String writeUsersNScores() {
		sortUsersByScore();
		String c = "";
		for(int i = 0; i < users.size(); i++) {
			c += users.get(i).getName() +"\t"+ users.get(i).getScore()+",";
		}
		return c;
	}
	
	public String writeUsersBestGame() {
		sortUsersByBestGameInsertion();
		String c = "";
		for(int i = 0; i < users.size(); i++) {
			c += users.get(i).getName() +"\t"+ users.get(i).getBestGame() +",";
		}
		return c;
	}
	
	public String writeUserLastGame() {
		sortUsersLastGameInsertion();
		String c = "";
		for(int i = 0; i < users.size(); i++) {
			c += users.get(i).getName() +"\t"+ users.get(i).getLastGame()+",";
		}
		return c;
	}
	
	public String writeUsersFirstGame() {
		sortUsersFistGameInsertion();
		String c = "";
		for(int i = 0; i < users.size(); i++) {
			c += users.get(i).getName() + "\t"+ users.get(i).getFirstGame() + ",";
		}
		return c;
	}
	
	public void loadCharactersFile() {
		try {
			FileReader fr = new FileReader("data/Characters.txt");
			BufferedReader br = new BufferedReader(fr);
			String c = "";
			while((c = br.readLine()) != null) {
				String[] line = c.split(";");
				int life = Integer.parseInt(line[0]);
				int power = Integer.parseInt(line[1]);
				String name = line[2];
				String image = line[3];
				Character c1 = new Character(life,power,name,image);
				saveCharactersList(c1,this.upCharacter,null);
			}
			br.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFieldsFile() {
		try {
			FileReader fr = new FileReader("data/Fields.txt");
			BufferedReader br = new BufferedReader(fr);
			String c = "";
			while((c = br.readLine()) != null) {
				String[] line = c.split(";");
				String name = line[0];
				String image = line[1];
				Field f = new Field(name,image);
				saveFiedlList(f,this.upField,null);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveFiedlList(Field f,Field current,Field previous) {
		if(upField != null) {
			if(upField.getPrev() != null) {
				notCircularListField();
			}
		}
		if(this.upField == null) {
			this.upField = f;
		}else {
			if(current == null) {
				f.setPrev(previous);
				previous.setNext(f);
			}else {
				if(this.upField.compareTo(f) <= 0) {
					f.setNext(upField);
					upField.setPrev(f);
					upField = f;
				}else if(current.compareTo(f) <= 0) {
					if(previous != null) {
						previous.setNext(f);
					}
					current.setPrev(f);
					f.setNext(current);
					f.setPrev(previous);
				}else {
					previous = current;
					current = current.getNext();
					saveFiedlList(f, current, previous);
				}
			}
		}
	}
	
	public void saveCharactersList(Character c, Character current, Character previous) {
		if(upCharacter != null) {
			if(upCharacter.getPrev() != null) {
				notCircularListCharacter();
			}
		}
		if(this.upCharacter == null) {
			this.upCharacter = c;
		}else {
			if(current == null) {
				c.setPrev(previous);
				previous.setNext(c);
			}else {
				if(this.upCharacter.compareTo(c) <= 0) {
					c.setNext(upCharacter);
					upCharacter.setPrev(c);
					upCharacter = c;
				}else if(current.compareTo(c) <= 0) {
					if(previous != null) {
						previous.setNext(c);
					}
					current.setPrev(c);
					c.setNext(current);
					c.setPrev(previous);
				}else {
					previous = current;
					current = current.getNext();
					saveCharactersList(c, current, previous);
				}
			}
		}
	}
	
	public void charactersCircularList() {
		Character current = upCharacter;
		Character previous = null;
		while(current != null) {
			previous = current;
			current = current.getNext();
		}
		previous.setNext(upCharacter);
		upCharacter.setPrev(previous);
	}

	public void fieldsCircularList() {
		Field current = upField;
		Field previous = null;
		while(current != null) {
			previous = current;
			current = current.getNext();
		}
		previous.setNext(upField);
		upField.setPrev(previous);
	}
	
	
	
	
	public void selectedCharacter(boolean s) throws NotChoosenCharacter{
		if(s == false) {
			throw new NotChoosenCharacter();
		}
	}
	
	public void selectedField(boolean s) throws NotChoosenField{
		if(s == false) {
			throw new NotChoosenField();
		}
	}
	
	public void UsersSerialize() throws FileNotFoundException, IOException {
		File f = new File("data/Users.dat");
		if(f.exists() == false) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(users);
			oos.close();	
		}else {
			FileWriter fl = new FileWriter(f);
			BufferedWriter bf = new BufferedWriter(fl);
			bf.write("");
			bf.close();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(users);
			oos.close();
		}
	}
	
	public ArrayList<User> recoverUsers(){
		ArrayList<User> a = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Users.dat"));
			a = (ArrayList<User>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	
	
	public Character getCharacterChoose() {
		return characterChoose;
	}

	public void setCharacterChoose(Character characterChoose) {
		this.characterChoose = characterChoose;
	}

	public Field getFieldChoose() {
		return fieldChoose;
	}

	public void setFieldChoose(Field fieldChoose) {
		this.fieldChoose = fieldChoose;
	}

	/*
	 * makes the circular list into a non circular list
	 */
	public void notCircularListCharacter(){
		upCharacter.getPrev().setNext(null);
		upCharacter.setPrev(null);
	}
	
	public void notCircularListField() {
		upField.getPrev().setNext(null);
		upField.setPrev(null);
	}
	
	
	public Character searchCharacterByName(String name) throws CharacterNull{
		Character c = null;
		if(upCharacter != null) {
			if(upCharacter.getPrev() != null) {
				notCircularListCharacter();
			}
			c = upCharacter.searchCharacterByName(name);
		}else {
			c = null;
		}
		charactersCircularList();
		if(c == null) {
			throw new CharacterNull();
		}
		return c;
	}
	
	public User searchUsersByName(String name) throws UserNull{
		sortUsersByNameBubble();
		User u = null;
		boolean found = false;
		int first = 0;
		int last = users.size()-1;
		int mid;
		while(first <= last && !found) {
			mid = (first + last) / 2;
			if(users.get(mid).getName().compareToIgnoreCase(name) == 0) {
				u = users.get(mid);
				found = true;
			}else if(users.get(mid).getName().compareToIgnoreCase(name)< 0) {
				first = mid+1;
			}else {
				last = mid-1;
			}
		}
		if(u == null) {
			throw new UserNull();
		}
		return u;
	}
	
	
	
	public Field searchFieldByName(String name) {
		if(upField == null) {
			return null;
		}else {
			return upField.searchField(name);
		}
	}
	
	
	
	

	public void userChoosenInfo(String d) throws UserNull{
		String[] info = d.split("\t");
		String name = info[0];
		userChoose = searchUsersByName(name);
	}
	
	@Override
	public int compare(User u1, User u2) {
		int comp;
		if(u1.getScore() < u2.getScore()) {
			comp = -1;
		}else if(u1.getScore() > u2.getScore()) {
			comp = 1;
		}else {
			comp = 0;
		}
		return comp;
	}
	
}
