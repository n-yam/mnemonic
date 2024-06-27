package web.models.cards.get;

import java.util.List;

import web.models.cards.CardResponseModel;

public final class CardGetAllResponseModel {
	
	private final List<CardResponseModel> cards;
	
	public CardGetAllResponseModel(List<CardResponseModel> cards) {
		this.cards = cards;
	}
	
	public final List<CardResponseModel> getCards() {
		return cards;
	}
}
