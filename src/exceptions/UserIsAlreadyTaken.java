package exceptions;

public class UserIsAlreadyTaken extends Exception{

	public static final String ME = "Ya existe un usuario registrado con este nombre";
	
	public UserIsAlreadyTaken() {
		super(ME);
	}
	
	
}
