package domain.models.cards;

public final class Card {
	
	private final Long id;
	private CardText frontText;
	private CardText backText;

	public Card(Long id, CardText frontText, CardText backText) {
		this.id = id;
		this.frontText = frontText;
		this.backText = backText;
	}
	
	public final void changeFrontText(CardText newFrontText) {
		frontText = newFrontText;
	}
	
	public final void changeBackText(CardText newBackText) {
		backText = newBackText;
	}

	public final Long getId() {
		return id;
	}

	public final CardText getFrontText() {
		return frontText;
	}

	public final CardText getBackText() {
		return backText;
	}
}
