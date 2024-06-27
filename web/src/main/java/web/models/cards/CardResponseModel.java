package web.models.cards;

import application.CardData;

public final class CardResponseModel {

	private final Long id;
	private final String frontText;
	private final String backText;

	public CardResponseModel(CardData data) {
		id = data.getId();
		frontText = data.getFrontText();
		backText = data.getBackText();
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
