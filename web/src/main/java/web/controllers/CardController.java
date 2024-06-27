package web.controllers;

import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import java.util.stream.Collectors;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import application.cards.CardApplicationService;
import application.cards.CardNotFoundException;
import application.cards.create.CardCreateCommand;
import application.cards.delete.CardDeleteCommand;
import application.cards.get.CardGetCommand;
import application.cards.update.CardUpdateCommand;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import web.models.cards.CardResponseModel;
import web.models.cards.get.CardGetAllResponseModel;
import web.models.cards.get.CardGetResponseModel;
import web.models.cards.post.CardPostResponseModel;

@Path("/api/cards")
public final class CardController {

	private final CardApplicationService cardApplicationService;
	
	@Inject
	public CardController(CardApplicationService cardApplicationService) {
		this.cardApplicationService = cardApplicationService;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public final Response getAllCards() {
		try {
			var result = cardApplicationService.getAll();
			var cards = result.getCards().stream()
					.map(card -> new CardResponseModel(card))
					.collect(Collectors.toList());

			var body = new CardGetAllResponseModel(cards);
			var json = new ObjectMapper().writeValueAsString(body);

			return Response.ok(json).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(SC_INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public final Response createCard(@FormDataParam("frontText") String frontText,
			@FormDataParam("backText") String backText) {

		try {
			var command = new CardCreateCommand(frontText, backText);
			var result = cardApplicationService.create(command);
			var body = new CardPostResponseModel(result.getId());
			var json = new ObjectMapper().writeValueAsString(body);

			return Response.status(SC_CREATED).entity(json).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(SC_INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public final Response getCard(@PathParam("id") Long id) {
		try {
			var command = new CardGetCommand(id);
			var optional = cardApplicationService.get(command);

			if (optional.isEmpty()) {
				return Response.status(SC_NOT_FOUND).build();
			}

			var result = optional.get();
			var card = new CardResponseModel(result.getCard());
			var body = new CardGetResponseModel(card);
			var json = new ObjectMapper().writeValueAsString(body);

			return Response.ok(json).build();
		
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(SC_INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public final Response updateCard(@PathParam("id") Long id, @FormDataParam("frontText") String frontText,
			@FormDataParam("backText") String backText) {

		try {
			var command = new CardUpdateCommand(id, frontText, backText);
			cardApplicationService.update(command);

			return Response.ok().build();

		} catch (CardNotFoundException e) {	
			return Response.status(SC_NOT_FOUND).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(SC_INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public final Response deleteCard(@PathParam("id") Long id) {
		try {
			var command = new CardDeleteCommand(id);
			cardApplicationService.delete(command);

			return Response.ok().build();

		} catch (CardNotFoundException e) {	
			return Response.status(SC_NOT_FOUND).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(SC_INTERNAL_SERVER_ERROR).build();
		}
	}
}
