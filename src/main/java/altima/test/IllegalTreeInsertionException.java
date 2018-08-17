package altima.test;

/**
* Thrown to indicate illegal insertion into a tree.
* 
* @author Luka Grgić
* @version 1.0
*/
public class IllegalTreeInsertionException extends RuntimeException{

	/**
	 * Auto generated.
	 */
	private static final long serialVersionUID = -3711885874022472808L;

	/**
    * Constructs IllegalTreeInsertionException with no detail message.
    */
	public IllegalTreeInsertionException () {
		super();
	}

	/**
    * Constructs IllegalTreeInsertionException with the specified detail message.
    *
    * @param message The detail message.
    */
	public IllegalTreeInsertionException (String message) {
		super(message);
	}

	/**
    * Constructs IllegalTreeInsertionException with the
    * specified detail message and cause.
    *
    * @param message The detail message.
    * @param cause cause for exception.
    */
	public IllegalTreeInsertionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
    * Constructs IllegalTreeInsertionException with the
    * specified cause.
    *
    * @param cause cause for exception.
    */
	public IllegalTreeInsertionException(Throwable cause) {
		super(cause);
	}
	
}
