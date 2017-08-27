/**
 * 
 */
package by.epam.training.helper.dao.exception;

/**
 * This is an exception for working on a DAO layer
 * @author Nikolaev Ilya
 *
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException() {
		super();
	}

	public DAOException(String message, Exception cause) {
		super(message, cause);
	}

	public DAOException(Exception cause) {
		super(cause);
	}
}
