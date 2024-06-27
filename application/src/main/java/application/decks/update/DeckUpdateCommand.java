package application.decks.update;

public final class DeckUpdateCommand {

	private final Long id;
	private final String name;
	
	public DeckUpdateCommand(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public final Long getId() {
		return id;
	}
	
	public final String getName() {
		return name;
	}
}
