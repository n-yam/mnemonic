package application.cards.update;

public final class CardUpdateCommand {
	
	private final Long id;
	private final String frontText;
	private final String backText;

	public CardUpdateCommand(Long id, String frontText, String backText) {
		this.id = id;
		this.frontText = frontText;
		this.backText = backText;
	}

	public final Long getId() {
		return id;
	}
	
	public final String getFrontText() {
		return frontText;
	}
	
	public final String getBackText() {
		return backText;
	}
}
