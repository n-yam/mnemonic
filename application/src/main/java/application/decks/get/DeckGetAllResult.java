package application.decks.get;

import java.util.List;

import application.DeckData;

public final class DeckGetAllResult {

	private final List<DeckData> decks;
	
	public DeckGetAllResult(List<DeckData> decks) {
		this.decks = decks;
	}
	
	public final List<DeckData> getDecks() {
		return decks;
	}
}
