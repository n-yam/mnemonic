package web.models.decks;

import java.util.List;

import application.DeckData;

public final class DeckResponseModel {
	
	private final Long id;
	private final String name;
	private final List<Long> cardIds;
	
	public DeckResponseModel(DeckData data) {
		id = data.getId();
		name = data.getName();
		cardIds = data.getCardIds();
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
