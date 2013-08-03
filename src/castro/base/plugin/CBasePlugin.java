package castro.base.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import castro.base.GenericCommandMgr;

public abstract class CBasePlugin extends JavaPlugin
{
	public static CPlugin baseinstance;
	protected GenericCommandMgr commandMgr;
	
	
	@Override
	public final boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		return commandMgr.onCommand(sender, command, args);
	}
	
	
	public String getPdf()
	{
		return "&f[&9" + getDescription().getName() + "&f] ";
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
