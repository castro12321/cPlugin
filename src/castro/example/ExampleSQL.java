package castro.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import castro.base.data.SQLBase;
import castro.base.plugin.CPlugin;


//In order to use SQL from cPlugin, we need to inherit from SQLBase class
public class ExampleSQL extends SQLBase
{
	public ExampleSQL(CPlugin plugin)
	{
		// To access SQL database, we need URL, user and password.
		
		// We can have password stored in config.yml
		// To read from this file, pass false as second argument
		super(plugin, false);
		
		// But database account may be shared for some plugins.
		// If we pass true as second argument, database access
		// will be loaded from cPlugin config.yml, not our one.
		// super(plugin, true);
		
		// After initializing database, we can instantly start using it
		// Connection opening is done by getConn()
		Connection conn = getConn();
		// With connection, we can break something
		try
		{
			conn.createStatement().executeUpdate("DROP TABLE bans");
		}
		catch (SQLException e)
		{
			// This method prints some info about exception
			// to help problem localizing
			printErrors(e);
		}
		// Don't close() connection! We can reuse it later by next getConn()
		
		// We can also add SQL statements for future use (optional)
		this.addStatementSQL("updatePlayer",
			"UPDATE players SET awesomeness=? WHERE nick=?");
		// And use them here
		setAwesomeness("castro12321", 1337);
	}
	
	// In this methods we use previously added statements
	public void setAwesomeness(String playername, int awesome)
	{
		try
		{
			// getPreparedStatement will take care of setting connection up with database
			PreparedStatement statement = getPreparedStatement("updatePlayer");
			statement.setInt(1, awesome);
			statement.setString(2, playername);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			// This method prints some info about exception
			// to help problem localizing
			printErrors(e);
		}
	}
}