package exceptions;

public class AanmeldenException extends RuntimeException{

	public AanmeldenException() {
		super();
	}

	public AanmeldenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AanmeldenException(String message, Throwable cause) {
		super(message, cause);
	}

	public AanmeldenException(String message) {
		super(message);
	}

	public AanmeldenException(Throwable cause) {
		super(cause);
	}
}
