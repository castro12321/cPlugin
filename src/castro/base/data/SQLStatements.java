package castro.base.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import castro.base.plugin.CPlugin;



/**
 * SQL statements holder written by DooBLER
 */
public class SQLStatements extends SQLConnection
{
	// Zmienne do przechowywania zapytañ i gotowych PreparedStatements
	private Map<String, String> prepSQL = new HashMap<String, String>();
	private Map<String, PreparedStatement> prepStat = new HashMap<String, PreparedStatement>();
	
	
	SQLStatements(CPlugin plugin, boolean loadFromCPlugin)
	{
		super(plugin, loadFromCPlugin);
	}
	
	
	/**
	 * Zwraca prepared statement po nazwie
	 * 
	 * Zwraca wczeœniej utworzony prepared statement, jeœli nie ma to tworzy.
	 * 
	 * @param name
	 * @return
	 */
	public PreparedStatement getPreparedStatement(String name) {

		Connection conn = this.getConn();

		boolean exists = this.prepStat.containsKey(name);
		boolean isClosed = true;
		boolean isConn = false;

		PreparedStatement prest = null;

		// jeœli istnieje
		if(exists) {

			prest = this.prepStat.get(name);

			try {
                isClosed = prest.isClosed();
            }
            catch (SQLException e) {
                isClosed = true;
            }

			try {
				isConn = (prest.getConnection() == conn);
            }
            catch (SQLException e) {
            	isConn = false;
            }
		}


		if (!exists || !isConn || isClosed) {
			try {
				prest = conn.prepareStatement(this.prepSQL.get(name));
				this.prepStat.put(name, prest);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			prest.clearParameters();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prest; 
	}


	/**
	 * Dodaje SQL do listy, z której powstan¹ PreparedStatement 
	 * 
	 * @param name Nazwa pod jak¹ bêdzie dostêpny PreparedStatement
	 * @param sql SQL który zostanie u¿yty do stworzenia PreparedStatement
	 */
	public void addStatementSQL(String name, String sql) {
		this.prepSQL.put(name, sql);
	}
}
