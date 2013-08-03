package castro.base.plugin;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CMessenger extends CBasePlugin
{
	private Logger logger;
	
	public CMessenger()
	{
		logger = Logger.getLogger("Minecraft");
	}
	
	
	private String prepareMsg(String msg, boolean pdf)
	{
		if(pdf)
			msg = getPdf() + msg;
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	
	public boolean broadcast(String msg)
	{ return broadcast(msg, true); }
	public boolean broadcast(String msg, boolean pdf)
	{
		getServer().broadcastMessage(prepareMsg(msg, pdf));
		return true;
	}
	
	
	public boolean sendMessage(Player target, String msg)			{ return sendMessage(target, msg, true); }
	public boolean sendMessage(String target, String msg)			{ return sendMessage(target, msg, true); }
	public boolean sendMessage(CommandSender sender, String msg)	{ return sendMessage(sender, msg, true); }
	public boolean sendMessage(String target, String msg, boolean pdf)
	{
		CommandSender sender = target.equalsIgnoreCase("CONSOLE") ? getServer().getConsoleSender() : getServer().getPlayerExact(target);
		return sendMessage(sender, msg, pdf);
	}
	public boolean sendMessage(Player target, String msg, boolean pdf)
	{ return sendMessage((CommandSender)target, msg, pdf); }
	public boolean sendMessage(CommandSender sender, String msg, boolean pdf)
	{
		if (sender != null)
			sender.sendMessage(prepareMsg(msg, pdf));
		return true;
	}
	
	
	public boolean log(String msg)
	{ return baseinstance.log(msg, true); }
	public boolean log(String msg, boolean pdf)
	{
		logger.info(prepareMsg(msg, pdf));
		return true;
	}
}