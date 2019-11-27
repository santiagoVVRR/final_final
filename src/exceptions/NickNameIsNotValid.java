package exceptions;

public class NickNameIsNotValid extends Exception{

	public static final String ME = "The nickName must have at least 4 letters";
	
	public NickNameIsNotValid() {
		super(ME);
	}
	
}
