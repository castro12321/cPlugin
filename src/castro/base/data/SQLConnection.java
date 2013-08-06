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
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

import castro.base.Plugin;
import castro.base.plugin.CPlugin;

/**
 * SQL connection holder written by DooBLER
 */
public class SQLConnection
{
	private static final int VALID_TIMEOUT  = 2; // Jak d³ugo czekaæ podczas sprawdzania czy po³¹czenie jest poprawne (default 2 sec)
	private static final int MIN_CHECK_TIME = 5; // Minimalny czas co jaki sprawdzane jest po³¹czenie (w sekundach).
	private long lastcheck = 0;
	
	private String user = ""; 
	private String database = ""; 
	private String password = ""; 
	private String port = ""; 
	private String hostname = "";
	
	private Connection c = null; // Obiekt po³¹czenia z baz¹.
	CPlugin cplugin;
	
	public SQLConnection(CPlugin plugin, boolean loadFromCPlugin)
	{
		init(plugin, loadFromCPlugin);
	}
	
	private void init(CPlugin plugin, boolean loadFromCPlugin)
	{
		cplugin = plugin;
		
		FileConfiguration con = plugin.getConfig();
		if(loadFromCPlugin)
			con = Plugin.instance.getConfig();
		
		this.hostname	= con.getString("mysql.hostname"); 
		this.port		= con.getString("mysql.port");
		this.database	= con.getString("mysql.database");
		this.user		= con.getString("mysql.user");
		this.password	= con.getString("mysql.pass");
		
		this.open();
	}
	
    /**
     * Tworzy po³¹czenie z baz¹ danych.
     * 
     * @return Connection - obiekt po³¹czenia z baz¹ danych.
     */
    protected Connection open() { 
        try { 
            Class.forName("com.mysql.jdbc.Driver"); 
            this.c = DriverManager.getConnection("jdbc:mysql://" + this.hostname +
            									 ":" + this.port + "/" + this.database + 
            									 "?autoReconnect=false", 
            									 this.user, this.password); 
            return c; 
        } catch (SQLException e) { 
            System.out.println("Could not connect to MySQL server! because: " + e.getMessage()); 
        } catch (ClassNotFoundException e) { 
            System.out.println("JDBC Driver not found!"); 
        } 
        return this.c; 
    }
    
	
    /**
     * Zwraca obiekt po³¹czenia z baz¹.
     * 
     * Jeœli po³¹czenie pad³o lub nie istnieje to próbuje je naprawiæ.
     * Inspired by McMMO
     * 
     * @return Connection
     */
    public Connection getConn()
    {
    	boolean isClosed = true;
        boolean isValid = false;
        boolean exists = (this.c != null);
        
        if (exists)
        {
        	if (this.lastcheck+(MIN_CHECK_TIME*1000) > System.currentTimeMillis())
        		return this.c;
        	else
        		this.lastcheck = System.currentTimeMillis();
        	
            try {
                isClosed = this.c.isClosed();
            }
            catch (SQLException e) {
                isClosed = true;
                e.printStackTrace();
                printErrors(e);
            }

            if (!isClosed) {
                try {
                    isValid = this.c.isValid(VALID_TIMEOUT);
                }
                catch (SQLException e) {
                    // Don't print stack trace because it's valid to lose idle connections to the server and have to restart them.
                    isValid = false;
                }
            }
        }
        
        // Jeœli wszystko ok to wszystko jest poprawnie.
        if (exists && !isClosed && isValid)
            return this.c;

        // Cleanup after ourselves for GC and MySQL's sake
        if (exists && !isClosed) {
            try {
                this.c.close();
            }
            catch (SQLException ex) {
                // This is a housekeeping exercise, ignore errors
            }
        }
        
        // Ponowne ³¹czenie.
     	this.open();
        
        return this.c; 
    }
    
    
	protected void printErrors(SQLException ex)
	{
		cplugin.log("SQLException: " + ex.getMessage(), true);
		cplugin.log("SQLState: " + ex.getSQLState(), true);
		cplugin.log("VendorError: " + ex.getErrorCode(), true);
		ex.printStackTrace();
	}
}
