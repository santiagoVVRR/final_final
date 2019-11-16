package exceptions;

public class NotChoosenField extends Exception{

	public static final String ME = "No se ha seleccionado un campo de juego";
	
	public NotChoosenField() {
		super(ME);
	}
	
}
