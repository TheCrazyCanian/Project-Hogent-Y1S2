package exceptions;

public class LeegVeldException extends RuntimeException {

	public LeegVeldException() {
		super();
		
	}

	public LeegVeldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LeegVeldException(String message, Throwable cause) {
		super(message, cause);
	}

	public LeegVeldException(String message) {
		super(message);
	}

	public LeegVeldException(Throwable cause) {
		super(cause);
	}

	
	
}
