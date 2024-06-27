package application;

public final class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApplicationException(Exception cause) {
		super(String.format("Caused by %s", cause), cause);
	}
}
