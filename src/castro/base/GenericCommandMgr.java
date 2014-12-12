/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.base;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface GenericCommandMgr
{
	public boolean onCommand(CommandSender sender, Command command, String[] args);
}