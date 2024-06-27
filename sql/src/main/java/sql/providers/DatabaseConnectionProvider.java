package sql.providers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DatabaseConnectionProvider {
	
	private static String url;
	
	{
		var config = ResourceBundle.getBundle("sql");
		url = config.getString("database.url");
	}

	public final Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(url);
		return con;
	}
}
