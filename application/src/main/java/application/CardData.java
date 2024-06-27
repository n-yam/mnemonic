package application;

import domain.models.cards.Card;

public final class CardData {
	
	private final Long id;
	private final String frontText;
	private final String backText;
	
	public CardData(Card card) {
		this.id = card.getId();
		this.frontText = card.getFrontText().getValue();
		this.backText = card.getBackText().getValue();
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
