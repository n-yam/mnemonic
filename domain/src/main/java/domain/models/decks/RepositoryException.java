package domain.models.decks;

public final class RepositoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public RepositoryException(Exception cause) {
		super(String.format("Caused by %s", cause), cause);
	}
}