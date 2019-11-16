package model;

import java.io.File;
import java.util.ArrayList;

public class Index {

	//Attributes
	
	private ArrayList<User> users;
	private Field upField;
	private Character upCharacter;
	private Character characterChoose;
	private Field fieldChoose;
	private User userChoose;
	
	
	public index() {
		File f = new File("files/Users.dat");
		if(f.exists() == false) {
			users = new ArrayList<User>();
		}else {
			users = recoverUsers();
		}
		loadCharacter();
		loadFiles();
		circularListOfCharacters();
		characterChoose = upCharacter;
		fieldChoose = upField;
	}
	
	public void registerUsers(String name) {
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
	
}
