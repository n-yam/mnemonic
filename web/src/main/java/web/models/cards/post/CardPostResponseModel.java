package web.models.cards.post;

public final class CardPostResponseModel {
	
	private Long createdUserId;
	
	public CardPostResponseModel(Long createdUserId) {
		this.createdUserId = createdUserId;
	}
	
	public final Long getCreatedUserId() {
		return createdUserId;
	}
}
