package application.cards.delete;

public final class CardDeleteCommand {
	
	private final Long id;

	public CardDeleteCommand(Long id) {
		this.id = id;
	}

	public final Long getId() {
		return id;
	}
}
