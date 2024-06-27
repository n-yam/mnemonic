package application.decks.delete;

public final class DeckDeleteCommand {

	private final long id;

	public DeckDeleteCommand(Long id) {
		this.id = id;
	}

	public final Long getId() {
		return id;
	}
}
