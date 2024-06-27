package web.controllers;

import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import java.util.stream.Collectors;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import application.decks.DeckApplicationService;
import application.decks.DeckNotFoundException;
import application.decks.create.DeckCreateCommand;
import application.decks.delete.DeckDeleteCommand;
import application.decks.get.DeckGetCommand;
import application.decks.update.DeckUpdateCommand;
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
import web.models.decks.DeckResponseModel;
import web.models.decks.get.DeckGetAllResponseModel;
import web.models.decks.get.DeckGetResponseModel;
import web.models.decks.post.DeckPostResponseModel;

@Path("/api/decks")
public final class DeckController {
	
	private final DeckApplicationService deckApplicationService;
	
	@Inject
	public DeckController(DeckApplicationService deckApplicationService) {
		this.deckApplicationService = deckApplicationService;
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public final Response createDeck(@FormDataParam("name") String name) {
		try {
			var command = new DeckCreateCommand(name);
			var result = deckApplicationService.create(command);
			var body = new DeckPostResponseModel(result.getId());
			var json = new ObjectMapper().writeValueAsString(body);

			return Response.status(SC_CREATED).entity(json).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(SC_INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public final Response getAllDecks() {
		try {
			var result = deckApplicationService.getAll();
			var decks = result.getDecks().stream()
					.map(deck -> new DeckResponseModel(deck))
					.collect(Collectors.toList());

			var body = new DeckGetAllResponseModel(decks);
			var json = new ObjectMapper().writeValueAsString(body);

			return Response.ok(json).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(SC_INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public final Response getDeck(@PathParam("id") Long id) {
		try {
			var command = new DeckGetCommand(id);
			var optional = deckApplicationService.get(command);
			
			if (optional.isEmpty()) {
				return Response.status(SC_NOT_FOUND).build();
			}
			
			var result = optional.get();
			var deck = new DeckResponseModel(result.getDeck());
			var body = new DeckGetResponseModel(deck);
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
	public final Response updateDeck(@PathParam("id") Long id, @FormDataParam("name") String name) {
		try {
			var command = new DeckUpdateCommand(id, name);
			deckApplicationService.update(command);
			
			return Response.ok().build();
		
		} catch (DeckNotFoundException e) {	
			return Response.status(SC_NOT_FOUND).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(SC_INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public final Response deleteDeck(@PathParam("id") Long id) {
		try {
			var command = new DeckDeleteCommand(id);
			deckApplicationService.delete(command);
			
			return Response.ok().build();
			
		} catch (DeckNotFoundException e) {	
			return Response.status(SC_NOT_FOUND).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(SC_INTERNAL_SERVER_ERROR).build();
		}
	}
}
