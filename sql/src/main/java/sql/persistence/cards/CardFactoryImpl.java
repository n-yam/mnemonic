package sql.persistence.cards;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.models.cards.Card;
import domain.models.cards.CardText;
import domain.models.cards.ICardFactory;
import domain.models.decks.FactoryException;
import sql.providers.DatabaseConnectionProvider;

public final class CardFactoryImpl implements ICardFactory {
	
	private final DatabaseConnectionProvider provider = new DatabaseConnectionProvider();
	
	@Override
	public Card create(CardText frontText, CardText backText) throws FactoryException {
		try {
			Long id = assignId();
			return new Card(id, frontText, backText);	
		
		} catch (SQLException e) {
			throw new FactoryException(e);
		}
	}
	
	private final Long assignId() throws SQLException {
		try (Connection con = provider.getConnection(); Statement stmt = con.createStatement()) {
			String sql = "SELECT id FROM cards ORDER BY id DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next())
				return rs.getLong("id") + 1L;

			return 1L;
		}
	}
}
