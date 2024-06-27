package web.models.decks.get;

import web.models.decks.DeckResponseModel;

public final class DeckGetResponseModel {

	private final DeckResponseModel deck;

	public DeckGetResponseModel(DeckResponseModel deck) {
		this.deck = deck;
	}

	public final DeckResponseModel getDeck() {
		return deck;
	}
}
