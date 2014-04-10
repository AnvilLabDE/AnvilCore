package anvil.Minefabser.API.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.configuration.file.FileConfiguration;

import anvil.Minefabser.AnvilCore.AnvilMain;

public class MySQL {
	
	public static	String		SUBJECT_TABLE	= "subject-table";
	public static	String		COMMAND_TABLE	= "command-table";
	
	private static	Connection	connection		= null; 
	
	/**
	 * Setzt die MySQL-Verbindung auf
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void setup() throws ClassNotFoundException, SQLException {
		String host = "localhost", port = "3306", database = "yourDatabase", user = "yourUser", password = "yourPassword";
		FileConfiguration conf = AnvilMain.getPlugin().getConfig();

		// host
		if (!conf.contains("mysql.host"))
			conf.set("mysql.host", host);
		else
			host = conf.getString("mysql.host");
		// port
		if (!conf.contains("mysql.port"))
			conf.set("mysql.port", port);
		else
			port = conf.getString("mysql.port");
		// database
		if (!conf.contains("mysql.database"))
			conf.set("mysql.database", database);
		else
			database = conf.getString("mysql.database");
		// user
		if (!conf.contains("mysql.user"))
			conf.set("mysql.user", user);
		else
			user = conf.getString("mysql.user");
		// password
		if (!conf.contains("mysql.password"))
			conf.set("mysql.password", password);
		else
			password = conf.getString("mysql.password");
		// tables.subject
		if (!conf.contains("mysql.tables.subject"))
			conf.set("mysql.tables.subject", SUBJECT_TABLE);
		else
			SUBJECT_TABLE = conf.getString("mysql.tables.subject");
		// tables.command
		if (!conf.contains("mysql.tables.command"))
			conf.set("mysql.tables.command", COMMAND_TABLE);
		else
			COMMAND_TABLE = conf.getString("mysql.tables.command");
		
		AnvilMain.getPlugin().saveConfig();
		
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
	}
	
	/**
	 * Gibt die Verbindung ({@link Connection}) zur MySQL-Datenbank zurück
	 * @return Verbindung ({@link Connection}) zur MySQL-Datenbank
	 */
	public static Connection getConnection() {
		return connection;
	}

	/**
	 * Führt einen SQL-Befehl aus und gibt ein {@link ResultSet} zurück
	 * 
	 * @param SQL-Befehl
	 * @return {@link ResultSet}
	 * @throws SQLException
	 */
	public static ResultSet querySQL(String sql) throws SQLException {
		Statement	statement	= connection.createStatement();
		ResultSet	resultSet	= statement.executeQuery(sql);
		
		return resultSet;
	}
	
	/**
	 * Führt einen SQL-Befehl aus (Update)
	 * 
	 * @param SQL-Befehl (Update)
	 * @throws SQLException
	 */
	public static void updateSQL(String sql) throws SQLException {
		PreparedStatement	preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.executeUpdate();
	}

}
