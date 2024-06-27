package web.di.cards;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import application.cards.CardApplicationService;
import domain.models.cards.ICardFactory;
import domain.models.cards.ICardRepository;
import sql.persistence.cards.CardFactoryImpl;
import sql.persistence.cards.CardRepositoryImpl;

public final class CardApplicationServiceBinder extends AbstractBinder {
	
	@Override
	protected void configure() {
		bind(CardFactoryImpl.class).to(ICardFactory.class);
		bind(CardRepositoryImpl.class).to(ICardRepository.class);
		bindFactory(CardApplicationServiceFactory.class).to(CardApplicationService.class);
	}
}
