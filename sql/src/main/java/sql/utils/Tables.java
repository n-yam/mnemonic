package sql.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import sql.providers.DatabaseConnectionProvider;

public final class Tables {
	
	private final DatabaseConnectionProvider provider = new DatabaseConnectionProvider();

	public final void createTables() throws TableUtilException {
		String[] sqls = {
				"""
				CREATE TABLE IF NOT EXISTS decks (
					id INTEGER PRIMARY KEY AUTOINCREMENT, 
					name TEXT
				);
				""",
				"""
				CREATE TABLE IF NOT EXISTS cards (
					id INTEGER PRIMARY KEY AUTOINCREMENT, 
					frontText TEXT, 
					backText TEXT
				);
				""",
		};
		executeSqls(sqls);
	}
	
	public final void dropTables() throws TableUtilException {
		String[] sqls = { 
				"DROP TABLE decks", 
				"DROP TABLE cards" 
		};
		executeSqls(sqls);
	}
	
	private final void executeSqls(String[] sqls) throws TableUtilException {
		try (Connection con = provider.getConnection(); Statement stmt = con.createStatement()) {
			for (String sql : sqls) {
				stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			throw new TableUtilException(e);
		}
	}
}
