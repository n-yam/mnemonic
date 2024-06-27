package sql.persistence.cards;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.models.cards.Card;
import domain.models.cards.CardText;

final class CardBuilder {
	
	private Long id;
	private String frontText;
	private String backText;
	
	public CardBuilder(ResultSet rs) throws SQLException {
		readCard(rs);
	}
	
	private final void readCard(ResultSet rs) throws SQLException {
		id = rs.getLong("id");
		frontText = rs.getString("frontText");
		backText = rs.getString("backText");
	}
	
	public final Card build() {
		var cardFrontText = new CardText(frontText);
		var cardBackText = new CardText(backText);
		var card = new Card(id, cardFrontText, cardBackText);
		
		return card;
	}
}
