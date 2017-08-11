package controller;

public class SLogoException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SLogoException(String message){
		super(message);
	}
	
	public SLogoException(String message, Throwable cause){
		super(message, cause);
	}
	
	public SLogoException(Throwable cause){
		super(cause);
	}

}
