package web.models.decks.post;

public final class DeckPostResponseModel {

	private Long createdDeckId;

	public DeckPostResponseModel(Long createdDeckId) {
		this.createdDeckId = createdDeckId;
	}

	public final Long getCreatedDeckId() {
		return createdDeckId;
	}
}
