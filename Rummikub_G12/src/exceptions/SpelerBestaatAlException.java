package exceptions;

public class SpelerBestaatAlException extends RuntimeException {

	public SpelerBestaatAlException() {
		super();
	}

	public SpelerBestaatAlException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SpelerBestaatAlException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpelerBestaatAlException(String message) {
		super(message);
	}

	public SpelerBestaatAlException(Throwable cause) {
		super(cause);
	}
	
}
