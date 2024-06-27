package web.di.cards;

import org.glassfish.hk2.api.Factory;

import application.cards.CardApplicationService;
import domain.models.cards.ICardFactory;
import domain.models.cards.ICardRepository;
import jakarta.inject.Inject;

public final class CardApplicationServiceFactory implements Factory<CardApplicationService> {

	private final ICardFactory cardFactory;
	private final ICardRepository cardRepository;

	@Inject
	public CardApplicationServiceFactory(ICardFactory cardFactory, ICardRepository cardRepository) {
		this.cardFactory = cardFactory;
		this.cardRepository = cardRepository;
	}

	@Override
	public CardApplicationService provide() {
		return new CardApplicationService(cardFactory, cardRepository);
	}

	@Override
	public void dispose(CardApplicationService instance) {
	}
}
