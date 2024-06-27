package web.models.decks.get;

import java.util.List;

import web.models.decks.DeckResponseModel;

public final class DeckGetAllResponseModel {

	private final List<DeckResponseModel> decks;

	public DeckGetAllResponseModel(List<DeckResponseModel> decks) {
		this.decks = decks;
	}

	public final List<DeckResponseModel> getDecks() {
		return decks;
	}
}
