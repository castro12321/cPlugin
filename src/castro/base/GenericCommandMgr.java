package castro.base;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface GenericCommandMgr
{
	public boolean onCommand(CommandSender sender, Command command, String[] args);
}