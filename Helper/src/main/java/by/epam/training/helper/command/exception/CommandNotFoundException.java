package by.epam.training.helper.command.exception;

public class CommandNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public CommandNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CommandNotFoundException(String message, Exception cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CommandNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CommandNotFoundException(Exception cause) {
		super(cause);
	}


}
