/**
 * 
 */
package by.epam.training.helper.dao.exception;

/**
 * This is an exception for working on a DAO layer. This exception is used to work with the connection pool.
 * @author Nikolaev Ilya
 *
 */
public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolException() {
		super();
	}
	public ConnectionPoolException(String message, Exception cause) {
		super(message, cause);
	}
	public ConnectionPoolException(String message) {
		super(message);
	}
	public ConnectionPoolException(Exception cause) {
		super(cause);
	}
}
