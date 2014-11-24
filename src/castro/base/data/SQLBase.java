/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.base.data;

import castro.base.plugin.CPlugin;




public class SQLBase extends SQLStatements
{	
	public SQLBase(CPlugin plugin)
	{
		super(plugin, true);
	}
	
	
	public SQLBase(CPlugin plugin, boolean loadFromCPlugin)	
	{
		super(plugin, loadFromCPlugin);
	}
	
	
	/*
	// Returns SQL string ("'string'")
	public String str(String msg)
	{
		return "'" + msg + "'";
	}
	
	
	protected int get(String table, String column, String condition)
	{
		String sql = "SELECT " + column + " FROM " + table;
		sql += " WHERE " + condition;
		
		int ret = 0;
		try
		{
			ResultSet rs = getConn().createStatement().executeQuery(sql);
			if(rs.next())
				ret = rs.getInt(column);
		}
		catch(SQLException e) { printErrors(e); }
		
		return ret;
	}
	
	
	protected void increment(String table, String column, String condition)
	{
		update(table, column, column + " + 1", condition); // UPDATE table SET column = column + 1 WHERE condition
	}
	
	
	protected void update(String table, String column, String value)
	{ update(table, column, value, null); }
	protected void update(String table, String column, String value, String condition)
	{ update(table, column, value, condition, null); }
	protected void update(String table, String column, String value, String condition, String limit)
	{
		String sql = "UPDATE " + table + " SET " + column + " = " + value;
		if(condition != null)
			sql += " WHERE " + condition;
		if(limit != null)
			sql += " LIMIT " + limit;
		try
		{
			PreparedStatement ps = getConn().prepareStatement(sql);
			ps.execute();
			ps.close();
		}
		catch(SQLException e) { printErrors(e); }
	}
	
	
	protected ResultSet select(String what, String table)
	{ return select(what, table, null, null); }
	protected ResultSet select(String what, String table, String condition)
	{ return select(what, table, condition, null); }
	protected ResultSet select(String what, String table, String condition, String limit)
	{
		String sql = "SELECT " + what + " FROM " + table;
		if(condition != null)
			sql += " WHERE " + condition;
		if(limit != null)
			sql += " LIMIT " + limit;
		
		try
		{
			ResultSet rs = getConn().createStatement().executeQuery(sql);
			return rs;
		}
		catch(SQLException e) { printErrors(e); }
		
		return null;
	}
	
	
	protected String getString(String table, String column, String condition)
	{
		String ret = null;
		try {
			ResultSet rs = select(column, table, condition, "1");
			if(rs.next())
				ret = rs.getString(column);
			rs.close();
		} catch(SQLException e) { printErrors(e); }		
		return ret;
	}
	protected int getInt(String table, String column, String condition)
	{		
		int ret = 0;
		try {
			ResultSet rs = select(column, table, condition, "1");
			if(rs.next())
				ret = rs.getInt(column);
			rs.close();
		} catch(SQLException e) { printErrors(e); }		
		return ret;
	}
	protected boolean getBoolean(String table, String column, String condition)
	{
		boolean ret = false;
		try {
			ResultSet rs = select(column, table, condition, "1");
			if(rs.next())
				ret = rs.getBoolean(column);
			rs.close();
		} catch(SQLException e) { printErrors(e); }		
		return ret;
	}
	protected float getFloat(String table, String column, String condition)
	{
		float ret = 0;
		try {
			ResultSet rs = select(column, table, condition, "1");
			if(rs.next())
				ret = rs.getFloat(column);
			rs.close();
		} catch(SQLException e) { printErrors(e); }		
		return ret;
	}
	
	
	
	// i.e insert("Persons", "name, age", "John, 25");
	protected void insert(String table, String columns, String values)
	{
		String sql = "INSERT INTO " + table + "(" + columns + ") VALUES (" + values + ")";
		
		try
		{
			PreparedStatement ps = getConn().prepareStatement(sql);
			ps.executeUpdate(sql);
			ps.close();
		}
		catch(SQLException e) { printErrors(e); }
	}
	
	
	protected void delete(String table, String condition)
	{
		String
		sql  = "DELETE FROM " + table;
		sql += " WHERE " + condition;
		
		try
		{
			PreparedStatement ps = getConn().prepareStatement(sql);
			ps.executeUpdate(sql);
			ps.close();
		}
		catch(SQLException e) { printErrors(e); }
	}
	*/
}
