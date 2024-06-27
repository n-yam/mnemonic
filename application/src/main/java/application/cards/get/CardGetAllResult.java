package application.cards.get;

import java.util.List;

import application.CardData;

public final class CardGetAllResult {
	
	private final List<CardData> cards;
	
	public CardGetAllResult(List<CardData> cards) {
		this.cards = cards;
	}
	
	public final List<CardData> getCards() {
		return cards;
	}
}
