package web;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.CrossOriginHandler;
import org.glassfish.jersey.servlet.ServletContainer;

import sql.utils.Tables;

public final class Startup {

	private final static Properties props = new Properties("web");
	
	public final static void main(String[] args) throws Exception {
		var tables = new Tables();
		tables.createTables();
		
		var server = createServer();
		server.start();
	}
		
	public static final Server createServer() {
		var port = props.getInt("jetty.port");
		var server = new Server(port);

		var crossOriginHandler = createCrossOriginHeader();
		server.setHandler(crossOriginHandler);

		var contextHandler = createServletContextHandler();
		crossOriginHandler.setHandler(contextHandler);
		
		var connector = new ServerConnector(server);
		server.addConnector(connector);

		return server;
	}

	private final static CrossOriginHandler createCrossOriginHeader() {
		var crossOriginHandler = new CrossOriginHandler();
		
		var allowedOriginPatterns = props.getSetOfStrings("cors.allowed.patterns");
		crossOriginHandler.setAllowedOriginPatterns(allowedOriginPatterns);
		
		var allowedMethods = props.getSetOfStrings("cors.allowed.methods");
		crossOriginHandler.setAllowedMethods(allowedMethods);
		
		return crossOriginHandler;
	}

	private final static ServletContextHandler createServletContextHandler() {
		var contextHandler = new ServletContextHandler();
		var jerseyServletHolder = createJerseyServletHolder();
		
		contextHandler.addServlet(jerseyServletHolder, "/*");
		
		return contextHandler;
	}
	
	private final static ServletHolder createJerseyServletHolder() {
		var servletContainer = new ServletContainer();
		var servletHolder = new ServletHolder(servletContainer);
				
		var resourceConfig = props.getString("jersey.class.config");
		servletHolder.setInitParameter("jakarta.ws.rs.Application", resourceConfig);

		// 次の警告を抑制: JAX-B API not found. WADL feature is disabled.
		servletHolder.setInitParameter("jersey.config.server.wadl.disableWadl", "true");

		// 次の警告を抑制: A class jakarta.activation.DataSource was not found.
		servletHolder.setInitParameter("jersey.config.disableDefaultProvider", "DATASOURCE");

		return servletHolder;
	}
}
