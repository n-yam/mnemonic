package sql.persistence.decks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import domain.models.decks.Deck;
import domain.models.decks.DeckName;
import domain.models.decks.FactoryException;
import domain.models.decks.IDeckFactory;
import sql.providers.DatabaseConnectionProvider;

public class DeckFactoryImpl implements IDeckFactory {

	private final DatabaseConnectionProvider provider = new DatabaseConnectionProvider();
	
	@Override
	public Deck create(DeckName name) throws FactoryException {
		try {
			Long id = assignId();
			List<Long> cards = List.of();
			return new Deck(id, name, cards);	
		
		} catch (SQLException e) {
			throw new FactoryException(e);
		}
	}
	
	private final Long assignId() throws SQLException {
		try (Connection con = provider.getConnection(); Statement stmt = con.createStatement()) {
			String sql = "SELECT id FROM decks ORDER BY id DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next())
				return rs.getLong("id") + 1L;

			return 1L;
		}
	}
}
