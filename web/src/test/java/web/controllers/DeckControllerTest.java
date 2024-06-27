package web.controllers;

import static org.junit.Assert.assertEquals;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sql.utils.Tables;
import web.Startup;
import web.utils.FormData;
import web.utils.Http;

public final class DeckControllerTest {

	private final String baseUri = "http://localhost:8080/api/decks";
	private static Server server = Startup.createServer();
	private final Tables tables = new Tables();
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		server.start();
	}
	
	@AfterClass
	public static void afterClass() throws Exception {
		server.stop();
	}
	
	@Before
	public void before() throws Exception {
		tables.createTables();
	}
	
	@After
	public void after() throws Exception {
		tables.dropTables();
	}
	
	@Test
	public void postAndGetDeck() throws Exception {
		// Post
		var formData = new FormData();
		formData.add("name", "deck");
		
		var respPost = Http.post(baseUri, formData);
				
		assertEquals(201, respPost.statusCode());
		
		// Get
		var id = 1;
		var respGet = Http.get(baseUri + "/" + id);
		
		assertEquals(200, respGet.statusCode());
	}
	
	@Test
	public void getAllDecks() throws Exception {
		var response = Http.get(baseUri);
				
		assertEquals(200, response.statusCode());
	}
	
	@Test
	public void getDeckNotFound() throws Exception {
		var unknownId = 0;
		var response = Http.get(baseUri + "/" + unknownId);
		
		assertEquals(404, response.statusCode());
	}
	
	@Test
	public void postAndPutDeck() throws Exception {
		// Post
		var formData = new FormData();
		formData.add("name", "before");
		
		Http.post(baseUri, formData);
		
		// Put
		formData.add("name", "after");

		var id = 1;
		var response = Http.put(baseUri + "/" + id, formData);
				
		assertEquals(200, response.statusCode());
	}
	
	@Test
	public void putDeckNotFound() throws Exception {
		var formData = new FormData();
		var unknownId = 0;
		var response = Http.put(baseUri + "/" + unknownId, formData);
				
		assertEquals(404, response.statusCode());
	}
	
	@Test
	public void postAndDeleteDeck() throws Exception {
		// Post
		var formData = new FormData();
		formData.add("name", "deck");
		
		Http.post(baseUri, formData);
		
		// Delete
		var id = 1;
		var response = Http.delete(baseUri + "/" + id);
				
		assertEquals(200, response.statusCode());
	}
	
	@Test
	public void deleteDeckNotFound() throws Exception {
		var unknownId = 1;
		var response = Http.delete(baseUri + "/" + unknownId);
				
		assertEquals(404, response.statusCode());
	}
}
