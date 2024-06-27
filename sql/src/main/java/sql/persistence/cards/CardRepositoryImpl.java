package sql.persistence.cards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.models.cards.Card;
import domain.models.cards.ICardRepository;
import domain.models.decks.RepositoryException;
import sql.providers.DatabaseConnectionProvider;

public final class CardRepositoryImpl implements ICardRepository {
	
	private final DatabaseConnectionProvider provider = new DatabaseConnectionProvider();
	
	@Override
	public Optional<Card> findById(Long id) throws RepositoryException {
		String sql = "SELECT * FROM cards WHERE id=?";

		try (Connection con = provider.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next())
				return Optional.empty();
			
			Card card = new CardBuilder(rs).build();
			
			return Optional.of(card);
			
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<Card> findAll() throws RepositoryException {
		String sql = "SELECT * FROM cards";
		List<Card> cards = new ArrayList<>();
		
		try (Connection con = provider.getConnection(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				var card = new CardBuilder(rs).build();
				cards.add(card);
			}
			return cards;
			
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public void save(Card card) throws RepositoryException {
		String sql = """
				INSERT INTO cards (id, frontText, backText) VALUES (?, ?, ?) 
				ON CONFLICT DO 
				UPDATE SET frontText=?, backText=? WHERE id=?
				""";
		
		try (Connection con = provider.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, card.getId());
			ps.setString(2, card.getFrontText().getValue());
			ps.setString(3, card.getBackText().getValue());
			ps.setString(4, card.getFrontText().getValue());
			ps.setString(5, card.getBackText().getValue());
			ps.setLong(6, card.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public void deleteById(Long id) throws RepositoryException {
		String sql = """
				DELETE FROM cards WHERE id=?
				""";
		
		try (Connection con = provider.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}
}
