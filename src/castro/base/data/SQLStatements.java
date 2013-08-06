/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package castro.base.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import castro.base.plugin.CPlugin;



/**
 * SQL statements holder written by DooBLER
 * http://github.com/DooBLER
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
