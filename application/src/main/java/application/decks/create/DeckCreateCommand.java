package application.decks.create;

public final class DeckCreateCommand {
	
	private final String name;

	public DeckCreateCommand(String name) {
		this.name = name;
	}

	public final String getName() {
		return name;
	}
}
