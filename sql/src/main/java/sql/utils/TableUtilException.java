package sql.utils;

public final class TableUtilException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TableUtilException(Exception e) {
		super("Caused by", e);
	}
}
