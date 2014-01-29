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

package castro.base.plugin;

public class TellRaw
{
}

/*
import org.bukkit.entity.Player;

import castro.base.Plugin;

public class TellRaw
{
	
	private String message;
	
	// Hover
	private String showText;
	private String showItem;
	private String showEntity;
	private String showAchievement;
	
	// Click
	private String runCommand;
	private String suggestCommand;
	private String openURL;
	
	
	public TellRaw()
	{
	}
	
	
	public TellRaw broadcast()
	{ return send("@a"); }
	public TellRaw send(Player player)
	{ return send(player.getName()); }
	public TellRaw send(String player)
	{
		Plugin.dispatchConsoleCommand("tellraw " + player + getJson());
		return this;
	}
	
	
	public TellRaw suggestCommand(String command)
	{
		this.suggestCommand = command;
		this.runCommand     = null;
		return this;
	}
	
	
	private String getJson()
	{
		return null;
	}
}
*/