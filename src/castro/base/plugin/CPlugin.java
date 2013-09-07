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

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;


public abstract class CPlugin extends CMessenger
{
	public FileConfiguration con; // config
	
	
	protected abstract void init();
	protected abstract CPluginSettings getSettings();
	
	
	private final void initBase(CPluginSettings settings)
	{		
		logger = getLogger();
		
		if(settings.useConfig)
		{
			saveDefaultConfig();
			con = getConfig();
		}
		
		commandMgr = settings.commandMgr;
		
		for(Listener listener : settings.listeners)
			getServer().getPluginManager().registerEvents(listener, this);
	}
	
	
	public final void onEnable()
	{
		baseinstance = this;
		
		initBase(getSettings());
		init();
	}
	
	
	public static boolean dispatchConsoleCommand(String command)
	{ return dispatchCommand(baseinstance.getServer().getConsoleSender(), command); }
	public static boolean dispatchCommand(CommandSender sender, String command)
	{
		baseinstance.getServer().dispatchCommand(sender, command);
		return true;
	}
	
	
	private static long tn = 0;
	public static void reset()
	{
		tn = System.nanoTime();
	}
	public static void timeStep(String msg)
	{
		long now = System.nanoTime();
		float diff = now-tn;
		baseinstance.sendMessage("CONSOLE", "DEBUG " + (diff/1000000.f) + "ms " + msg, false);
	}
	
	
	public static String joinArgs(String[] array)
	{ return joinArgs(array, 0); }
	public static String joinArgs(String[] array, int start)
	{
		String ret = "";
		if(array.length == 0)//<= start)
			return "";
		for(int i=start; i < array.length; ++i)
			ret += array[i] + " ";
		return ret.substring(0, ret.length()-1);
	}
}
