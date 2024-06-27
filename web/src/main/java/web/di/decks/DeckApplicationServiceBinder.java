package web.di.decks;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import application.decks.DeckApplicationService;
import domain.models.decks.IDeckFactory;
import domain.models.decks.IDeckRepository;
import sql.persistence.decks.DeckFactoryImpl;
import sql.persistence.decks.DeckRepositoryImpl;

public final class DeckApplicationServiceBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(DeckFactoryImpl.class).to(IDeckFactory.class);
		bind(DeckRepositoryImpl.class).to(IDeckRepository.class);
		bindFactory(DeckApplicationServiceFactory.class).to(DeckApplicationService.class);
	}
}
