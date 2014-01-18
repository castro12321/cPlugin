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


public abstract class CPlugin extends CPluginMsg
{
	protected abstract void init();
	
	
	@Override
	protected void initBase()
	{
		super.initBase();
	}
	
	
	public final void onEnable()
	{
		initBase();
		init();
	}
	
	
	@Override
	protected CPlugin getBaseInstance()
	{
		return this;
	}
	
	
	public static boolean dispatchConsoleCommand(String command)
	{ return dispatchCommand(baseinstance.getServer().getConsoleSender(), command); }
	public static boolean dispatchCommand(CommandSender sender, String command)
	{
		baseinstance.getServer().dispatchCommand(sender, command);
		return true;
	}
}
