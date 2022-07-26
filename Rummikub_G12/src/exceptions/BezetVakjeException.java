package exceptions;

public class BezetVakjeException extends RuntimeException {

	public BezetVakjeException() {
		super();
		
	}

	public BezetVakjeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BezetVakjeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BezetVakjeException(String message) {
		super(message);
	}

	public BezetVakjeException(Throwable cause) {
		super(cause);
	}

	
	
}
