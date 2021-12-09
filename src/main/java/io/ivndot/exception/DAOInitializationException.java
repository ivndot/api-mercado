package io.ivndot.exception;

public class DAOInitializationException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public DAOInitializationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public DAOInitializationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DAOInitializationException(String message) {
		super(message);
	}
	
	public DAOInitializationException(Throwable cause) {
		super(cause);
	}

}
