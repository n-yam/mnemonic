package application.cards.get;

public final class CardGetCommand {
	
	private final Long id;

	public CardGetCommand(Long id) {
		this.id = id;
	}

	public final Long getId() {
		return id;
	}
}
