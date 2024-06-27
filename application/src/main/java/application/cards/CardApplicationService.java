package application.cards;

import java.util.Optional;
import java.util.stream.Collectors;

import application.ApplicationException;
import application.CardData;
import application.cards.create.CardCreateCommand;
import application.cards.create.CardCreateResult;
import application.cards.delete.CardDeleteCommand;
import application.cards.get.CardGetAllResult;
import application.cards.get.CardGetCommand;
import application.cards.get.CardGetResult;
import application.cards.update.CardUpdateCommand;
import domain.models.cards.CardText;
import domain.models.cards.ICardFactory;
import domain.models.cards.ICardRepository;
import domain.models.decks.FactoryException;
import domain.models.decks.RepositoryException;

public final class CardApplicationService {

	private final ICardFactory cardFactory;
	private final ICardRepository cardRepository;
	
	public CardApplicationService(ICardFactory cardFactory, ICardRepository cardRepository) {
		this.cardFactory = cardFactory;
		this.cardRepository = cardRepository;
	}
	
	public final Optional<CardGetResult> get(CardGetCommand command) throws ApplicationException {
		try {
			var id = command.getId();
			var optional = cardRepository.findById(id);
			
			if (optional.isEmpty())
				return Optional.empty();
			
			var card = optional.get();
			var data = new CardData(card);
			var result = new CardGetResult(data);
			
			return Optional.of(result);
					
		} catch (RepositoryException e) {
			throw new ApplicationException(e);
		}
	}
	
	public final CardGetAllResult getAll() throws ApplicationException {
		try {
			var cards = cardRepository.findAll();
			var data = cards.stream()
					.map(card -> new CardData(card))
					.collect(Collectors.toList());
						
			return new CardGetAllResult(data);
					
		} catch (RepositoryException e) {
			throw new ApplicationException(e);
		}
	}
	
	public final CardCreateResult create(CardCreateCommand command) throws ApplicationException {
		try {
			var frontText = new CardText(command.getFrontText());
			var backText = new CardText(command.getBackText());
			
			var card = cardFactory.create(frontText, backText);
			
			cardRepository.save(card);
			
			return new CardCreateResult(card.getId());
			
		} catch (FactoryException | RepositoryException e) {
			throw new ApplicationException(e);
		} 
	}
	
	public final void update(CardUpdateCommand command) throws ApplicationException, CardNotFoundException {
		try {
			var id = command.getId();
			var optional = cardRepository.findById(id);
			
			if (optional.isEmpty())
				throw new CardNotFoundException();
			
			var card = optional.get();
			
			var frontText = new CardText(command.getFrontText());
			var backText = new CardText(command.getBackText());
			
			card.changeFrontText(frontText);
			card.changeBackText(backText);
			
			cardRepository.save(card);
			
		} catch (RepositoryException e) {
			throw new ApplicationException(e);
		}
	}
	
	public final void delete(CardDeleteCommand command) throws ApplicationException, CardNotFoundException {
		try {
			var id = command.getId();
			var optional = cardRepository.findById(id);
			
			if (optional.isEmpty())
				throw new CardNotFoundException();
			
			cardRepository.deleteById(id);
		
		} catch (RepositoryException e) {
			throw new ApplicationException(e);
		}
	}
}
