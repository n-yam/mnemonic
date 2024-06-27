package domain.models.cards;

public final class CardText {
	
	private final String value;
	
	public CardText(String value) {
		if (value == null) throw new IllegalArgumentException("Value is null");
		if (value.length() > 140) throw new IllegalArgumentException("Value is too long");
		
		this.value = value;
	}
	
	public final String getValue() {
		return value;
	}
}
