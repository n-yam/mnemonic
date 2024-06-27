package domain.models.cards;

import domain.models.decks.FactoryException;

public interface ICardFactory {
	
	public Card create(CardText frontText, CardText backText) throws FactoryException;
}
