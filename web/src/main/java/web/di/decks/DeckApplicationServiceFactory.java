package web.di.decks;

import org.glassfish.hk2.api.Factory;

import application.decks.DeckApplicationService;
import domain.models.decks.IDeckFactory;
import domain.models.decks.IDeckRepository;
import jakarta.inject.Inject;

public final class DeckApplicationServiceFactory implements Factory<DeckApplicationService> {

	private final IDeckFactory DeckFactory;
	private final IDeckRepository DeckRepository;

	@Inject
	public DeckApplicationServiceFactory(IDeckFactory DeckFactory, IDeckRepository DeckRepository) {
		this.DeckFactory = DeckFactory;
		this.DeckRepository = DeckRepository;
	}

	@Override
	public DeckApplicationService provide() {
		return new DeckApplicationService(DeckFactory, DeckRepository);
	}

	@Override
	public void dispose(DeckApplicationService instance) {
	}
}
