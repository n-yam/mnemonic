package application.cards.create;

public final class CardCreateCommand {
	
	private final String frontText;
	private final String backText;
	
	public CardCreateCommand(String frontText, String backText) {
		this.frontText = frontText;
		this.backText = backText;
	}
	
	public final String getFrontText() {
		return frontText;
	}
	
	public final String getBackText() {
		return backText;
	}
}
