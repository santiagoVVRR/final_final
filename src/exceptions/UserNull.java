package exceptions;

public class UserNull extends Exception{

	public static final String ME = "El jugador que desea buscar no se encuentra registrado";

	public UserNull(){
		super(ME);
	}
	
}
