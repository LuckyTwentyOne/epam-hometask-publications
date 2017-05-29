package ua.kh.butov.subpub.exception;

public class ValidationException extends Exception {
	private static final long serialVersionUID = -6843925636139273536L;

	public ValidationException(String s) {
		super(s);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
