package domain.models.decks;

public interface IDeckFactory {
	
	public Deck create(DeckName name) throws FactoryException;
}
