package domain.models.decks;

import java.util.List;

import domain.models.cards.Card;

public final class Deck {
	
	private final Long id;
	private DeckName name;
	private final List<Long> cards;

	public Deck(Long id, DeckName name, List<Long> cards) {
		this.id = id;
		this.name = name;
		this.cards = cards;
	}
	
	public final void changeName(DeckName newName) {
		name = newName;
	}

	public final void add(Card card) throws DeckFullException {
		if (card == null)
			throw new IllegalArgumentException("Card is null");
		if (this.isFull())
			throw new DeckFullException("Deck is full");

		cards.add(card.getId());
	}

	public final boolean isFull() {
		return getCards().size() > 100 ? true : false;
	}

	public final Long getId() {
		return id;
	}

	public final DeckName getName() {
		return name;
	}

	public final List<Long> getCards() {
		return cards;
	}
}
