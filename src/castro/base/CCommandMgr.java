/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.base;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import castro.base.plugin.CPlugin;

public abstract class CCommandMgr implements GenericCommandMgr
{
	private final CPlugin plugin;
	protected abstract BaseCCommand getCommand(CommandSender sender, Command command, String[] args);
	
	
	public CCommandMgr(CPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String[] args)
	{
		BaseCCommand ccommand = getCommand(sender, command, args);
		return onCommand(ccommand, sender, command, args);
	}
	
	public boolean onCommand(BaseCCommand ccommand, CommandSender sender, Command command, String[] args)
	{
		if(ccommand != null)
		{
			ccommand.baseInit(plugin, sender, command, args);
			return ccommand.run();
		}
		return false;
	}
}