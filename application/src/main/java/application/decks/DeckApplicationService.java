package application.decks;

import java.util.Optional;
import java.util.stream.Collectors;

import application.ApplicationException;
import application.DeckData;
import application.decks.create.DeckCreateCommand;
import application.decks.create.DeckCreateResult;
import application.decks.delete.DeckDeleteCommand;
import application.decks.get.DeckGetAllResult;
import application.decks.get.DeckGetCommand;
import application.decks.get.DeckGetResult;
import application.decks.update.DeckUpdateCommand;
import domain.models.decks.DeckName;
import domain.models.decks.FactoryException;
import domain.models.decks.IDeckFactory;
import domain.models.decks.IDeckRepository;
import domain.models.decks.RepositoryException;

public final class DeckApplicationService {

	private final IDeckFactory deckFactory;
	private final IDeckRepository deckRepository;

	public DeckApplicationService(IDeckFactory deckFactory, IDeckRepository deckRepository) {
		this.deckFactory = deckFactory;
		this.deckRepository = deckRepository;
	}
	
	public final Optional<DeckGetResult> get(DeckGetCommand command) throws ApplicationException {
		try {
			var id = command.getId();
			var optional = deckRepository.findById(id);
			
			if (optional.isEmpty())
				return Optional.empty();
			
			var deck = optional.get();
			var data = new DeckData(deck);
			var result = new DeckGetResult(data);
			
			return Optional.of(result);
		
		} catch (RepositoryException e) {
			throw new ApplicationException(e);
		}
	}
	
	public final DeckGetAllResult getAll() throws ApplicationException {
		try {
			var decks = deckRepository.findAll();
			var data = decks.stream()
					.map(deck -> new DeckData(deck))
					.collect(Collectors.toList());
						
			return new DeckGetAllResult(data);
					
		} catch (RepositoryException e) {
			throw new ApplicationException(e);
		}
	}
	
	public final DeckCreateResult create(DeckCreateCommand command) throws ApplicationException {
		try {
			var name = new DeckName(command.getName());
			
			var deck = deckFactory.create(name);

			deckRepository.save(deck);

			return new DeckCreateResult(deck.getId());

		} catch (FactoryException | RepositoryException e) {
			throw new ApplicationException(e);	
		}
	}
	
	public final void update(DeckUpdateCommand command) throws ApplicationException, DeckNotFoundException {
		try {
			var id = command.getId();
			var optional = deckRepository.findById(id);
			
			if (optional.isEmpty())
				throw new DeckNotFoundException();
			
			var deck = optional.get();			
			var name = new DeckName(command.getName());
			
			deck.changeName(name);
			
			deckRepository.save(deck);
			
		} catch (RepositoryException e) {
			throw new ApplicationException(e);
		}
	}
	
	public final void delete(DeckDeleteCommand command) throws ApplicationException, DeckNotFoundException {
		try {
			var id = command.getId();
			var optional = deckRepository.findById(id);
			
			if (optional.isEmpty())
				throw new DeckNotFoundException();
			
			deckRepository.deleteById(id);
		
		} catch (RepositoryException e) {
			throw new ApplicationException(e);
		}
	}
}
