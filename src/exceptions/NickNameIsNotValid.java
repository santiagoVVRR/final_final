package exceptions;

public class NickNameIsNotValid extends Exception{

	public static final String ME = "El nick name debe tener al menos 3 caracteres";
	
	public NickNameIsNotValid() {
		super(ME);
	}
	
}
