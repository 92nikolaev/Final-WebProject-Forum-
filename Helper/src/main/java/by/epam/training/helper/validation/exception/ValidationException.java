package by.epam.training.helper.validation.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidationException(String message, Exception cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ValidationException(Exception cause) {
		super(cause);
	}

	
}
