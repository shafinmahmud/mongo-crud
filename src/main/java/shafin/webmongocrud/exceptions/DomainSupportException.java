package shafin.webmongocrud.exceptions;

public class DomainSupportException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DomainSupportException() {

	}

	public DomainSupportException(String message) {
		super(message);
	}
	
	public DomainSupportException(Throwable cause) {
		super(cause);
	}
	
	public DomainSupportException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
