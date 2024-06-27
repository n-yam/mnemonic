package application.cards.get;

import application.CardData;

public final class CardGetResult {
	
	private final CardData card;
	
	public CardGetResult(CardData card) {
		this.card = card;
	}

	public final CardData getCard() {
		return card;
	}
}
