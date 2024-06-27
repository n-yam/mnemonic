package application;

import java.util.List;

import domain.models.decks.Deck;

public final class DeckData {

	private final Long id;
	private final String name;
	private final List<Long> cardIds;

	public DeckData(Deck deck) {
		id = deck.getId();
		name = deck.getName().getValue();
		cardIds = deck.getCards();
	}

	public final Long getId() {
		return id;
	}

	public final String getName() {
		return name;
	}

	public final List<Long> getCardIds() {
		return cardIds;
	}
}
