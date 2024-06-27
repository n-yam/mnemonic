package domain.models.decks;

public final class DeckName {
	
	private final String value;

	public DeckName(String value) {
		if (value == null) throw new IllegalArgumentException("Value is null");
		if (value.length() > 20) throw new IllegalArgumentException("Value is too long");
		
		this.value = value;
	}

	public final String getValue() {
		return value;
	}
}
