package application.decks.get;

import application.DeckData;

public final class DeckGetResult {
	
	private final DeckData deck;
	
	public DeckGetResult(DeckData deck) {
		this.deck = deck;
	}
	
	public final DeckData getDeck() {
		return deck;
	}
}
