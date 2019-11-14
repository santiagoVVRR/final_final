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
	
	
}
