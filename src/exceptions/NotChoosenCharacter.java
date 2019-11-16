package exceptions;

public class NotChoosenCharacter extends Exception{

	public static final String ME = "No se ha seleccionado un personaje para jugar";
	
	public NotChoosenCharacter() {
		super(ME);
	}
	
}
