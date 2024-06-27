package web.utils;

public final class HttpClientException extends Exception {

	private static final long serialVersionUID = 1L;

	public HttpClientException(Exception cause) {
		super("Caused by", cause);
	}
}
