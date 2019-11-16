package exceptions;

public class CharacterNull extends Exception{

	public static final String ME = "El personaje no existe en el juego";
	
	public CharacterNull() {
		super(ME);
	}
}
