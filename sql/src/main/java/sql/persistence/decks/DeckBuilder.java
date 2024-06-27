package sql.persistence.decks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import domain.models.decks.Deck;
import domain.models.decks.DeckName;

final class DeckBuilder {
	
	private Long id;
	private String name;
	private List<Long> cards;
	
	public DeckBuilder(ResultSet rs) throws SQLException {
		readDeck(rs);
	}
	
	private final void readDeck(ResultSet rs) throws SQLException {
		id = rs.getLong("id");
		name = rs.getString("name");
	}
	
	public final Deck build() {
		var deckName = new DeckName(name);
		var deck = new Deck(id, deckName, cards);
		
		return deck;
	}
}
