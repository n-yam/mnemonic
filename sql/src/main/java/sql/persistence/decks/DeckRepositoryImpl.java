package sql.persistence.decks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.models.decks.Deck;
import domain.models.decks.IDeckRepository;
import domain.models.decks.RepositoryException;
import sql.providers.DatabaseConnectionProvider;

public final class DeckRepositoryImpl implements IDeckRepository {

	private final DatabaseConnectionProvider provider = new DatabaseConnectionProvider();
	
	@Override
	public Optional<Deck> findById(Long id) throws RepositoryException {
		String sql = "SELECT * FROM decks WHERE id=?";

		try (Connection con = provider.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next())
				return Optional.empty();
			
			Deck deck = new DeckBuilder(rs).build();
			
			return Optional.of(deck);
			
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<Deck> findAll() throws RepositoryException {
		String sql = "SELECT * FROM decks";
		List<Deck> decks = new ArrayList<>();
		
		try (Connection con = provider.getConnection(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				var card = new DeckBuilder(rs).build();
				decks.add(card);
			}
			return decks;
			
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public final void save(Deck deck) throws RepositoryException {
		String sql = """
				INSERT INTO decks (id, name) VALUES (?, ?) 
				ON CONFLICT DO 
				UPDATE SET name=? WHERE id=?
				""";
		
		try (Connection con = provider.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, deck.getId());
			ps.setString(2, deck.getName().getValue());
			ps.setString(3, deck.getName().getValue());
			ps.setLong(4, deck.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public void deleteById(Long id) throws RepositoryException {
		String sql = """
				DELETE FROM decks WHERE id=?
				""";
		
		try (Connection con = provider.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}
}
