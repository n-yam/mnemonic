package web.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public final class Http {
	
	public final static HttpResponse<String> post(String uri) throws HttpClientException {
		try {
			HttpClient client = HttpClient.newHttpClient();
			BodyPublisher body = BodyPublishers.noBody();

			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(uri)).POST(body).build();

			return client.send(request, BodyHandlers.ofString());			
		
		} catch (IOException | URISyntaxException | InterruptedException e) {
			throw new HttpClientException(e);
		} 
	}
	
	public final static HttpResponse<String> post(String uri, FormData formData) throws HttpClientException {
		try {
			HttpClient client = HttpClient.newHttpClient();
			BodyPublisher body = BodyPublishers.ofString(formData.toBodyString());

			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(uri)).setHeader("Content-Type", FormData.contentType).POST(body).build();
			
			return client.send(request, BodyHandlers.ofString());
		
		} catch (IOException | URISyntaxException | InterruptedException e) {
			throw new HttpClientException(e);
		} 
	}
	
	public final static HttpResponse<String> get(String uri) throws HttpClientException {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).GET().build();

			return client.send(request, BodyHandlers.ofString());
			
		} catch (IOException | URISyntaxException | InterruptedException e) {
			throw new HttpClientException(e);
		}
	}
	
	public final static HttpResponse<String> delete(String uri) throws HttpClientException {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).DELETE().build();

			return client.send(request, BodyHandlers.ofString());
			
		} catch (IOException | URISyntaxException | InterruptedException e) {
			throw new HttpClientException(e);
		}
	}
	
	public final static HttpResponse<String> put(String uri, FormData formData) throws HttpClientException {
		try {
			HttpClient client = HttpClient.newHttpClient();
			BodyPublisher body = BodyPublishers.ofString(formData.toBodyString());

			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(uri)).setHeader("Content-Type", FormData.contentType).PUT(body).build();

			return client.send(request, BodyHandlers.ofString());

		} catch (IOException | URISyntaxException | InterruptedException e) {
			throw new HttpClientException(e);
		}
	}
}
