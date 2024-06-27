package domain.models.decks;

public final class DeckFullException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public DeckFullException(String message) {
		super(message);
	}
}
