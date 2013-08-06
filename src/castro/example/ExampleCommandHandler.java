package castro.example;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import castro.base.GenericCommandMgr;


// All commands caught by Plugin will go here
public class ExampleCommandHandler implements GenericCommandMgr
{
	ExamplePlugin plugin;
	
	public ExampleCommandHandler(ExamplePlugin plugin)
	{
		this.plugin = plugin;
	}
	
	// To be able to receive command, we have to implement onCommand method
	@Override
	public boolean onCommand(CommandSender sender, Command command, String[] args)
	{
		// Here is simple logging for commands
		String playername = sender.getName();
		String commandname = command.getName();
		// joinArgs is static method that joins array into one string.
		// Array ["a", "b", "c"] will be proccessed into "a b c"
		String joinedargs = ExamplePlugin.joinArgs(args);
		
		// Log the command to console
		plugin.log(playername + " typed " + commandname + " " + joinedargs);
		
		// Further command handling
		// ...
		
		return true;
	}
}