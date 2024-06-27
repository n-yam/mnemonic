package web.models.cards.get;

import web.models.cards.CardResponseModel;

public final class CardGetResponseModel {

	private final CardResponseModel card;

	public CardGetResponseModel(CardResponseModel card) {
		this.card = card;
	}

	public final CardResponseModel getCard() {
		return card;
	}
}
