package application.decks.get;

public final class DeckGetCommand {
	
	private final long id;
	
	public DeckGetCommand(Long id) {
		this.id = id;
	}
	
	public final Long getId() {
		return id;
	}
}
