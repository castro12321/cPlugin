package castro.base;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import castro.base.plugin.CPlugin;

public abstract class BaseCCommand
{
	private   CPlugin       plugin;
	protected CommandSender sender;
	protected Player        senderPlayer;
	protected String        senderName;
	protected Command       command;
	protected String[]      args;
	
	protected abstract boolean  prepare();
	protected abstract boolean  execute();
	protected abstract boolean  onlyPlayer();
	protected abstract int      minArgs();
	protected abstract String[] neededPermissions(); // sender need any one of these permissions to run command
	
	
	public void baseInit(CPlugin plugin, CommandSender sender, Command command, String[] args)
	{
		this.plugin       = plugin;
		this.sender       = sender;
		this.senderName   = sender.getName();
		this.senderPlayer = sender instanceof Player ? (Player)sender : null;
		this.command      = command;
		this.args         = args;
	}
	
	
	public boolean run()
	{
		if(!senderHasPermissions())
			return plugin.sendMessage(sender, "&cYou don't have access to this command");
		
		if(args.length < minArgs())
			return plugin.sendMessage(sender, "&cYou have to provide more arguments");
		
		if(prepare())
			execute();
		return true;
	}
	
	
	protected String[] permissions(String... args)
	{
		return args;
	}
	
	
	private boolean senderHasPermissions()
	{
		return hasPermissions(sender, neededPermissions());
	}
	
	
	/**
	 * Returns whether player has permission to any of provided permissions
	 */
	protected boolean hasPermissions(CommandSender player, String... permissions)
	{
		if(permissions == null)
			return true;
		
		for(String permission : permissions)
			if(player.hasPermission(permission))
				return true;
		return false;
	}
}