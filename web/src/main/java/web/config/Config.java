package web.config;

import org.glassfish.jersey.server.ResourceConfig;

import web.Properties;
import web.di.cards.CardApplicationServiceBinder;
import web.di.decks.DeckApplicationServiceBinder;

public final class Config extends ResourceConfig {

	private final Properties props = new Properties("web");
	
	public Config() {
		var controllers = props.getString("jersey.package.controllers");
		packages(controllers);
		
		register(new CardApplicationServiceBinder());
		register(new DeckApplicationServiceBinder());
	}
}
