package domain.models.decks;

public final class FactoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public FactoryException(Exception cause) {
		super(String.format("Caused by %s", cause), cause);
	}
}