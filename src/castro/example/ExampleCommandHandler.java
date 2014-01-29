package castro.example;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import castro.base.BaseCCommand;
import castro.base.CCommandMgr;
import castro.base.plugin.CPlugin;
import castro.base.plugin.CUtils;


class ExampleCommand extends BaseCCommand
{
	private final CPlugin plugin;
	private String joinedArgs;
	
	public ExampleCommand(CPlugin plugin)
    {
	    this.plugin = plugin;
    }

	@Override
    protected boolean prepare()
    {
		// joinArgs is static method that joins array into one string.
		// Array ["a", "b", "c"] will be proccessed into "a b c"
		joinedArgs = CUtils.joinArgs(args);
	    return false;
    }

	@Override
    protected boolean execute()
    {
		// Log the command to console
		plugin.log(senderName + " typed " + command.getName() + " " + joinedArgs);
		plugin.sendMessage(sender, "You typed " + command.getName() + " " + joinedArgs);
	    return true;
    }

	@Override
    protected boolean onlyPlayer()
    {
	    return false;
    }

	@Override
    protected int minArgs()
    {
	    return 0;
    }

	@Override
    protected String[] neededPermissions()
    {
	    // return null; // No permissions needed
		return permissions("example", "command", "example.command"); // sender needs any of these permissions
    }
	
}

// All commands caught by Plugin will go here
public class ExampleCommandHandler extends CCommandMgr
{
	CPlugin plugin;
	
	public ExampleCommandHandler(CPlugin plugin)
	{
		super(plugin);
		this.plugin = plugin;
	}
	

	@Override
    protected BaseCCommand getCommand(CommandSender sender, Command command, String[] args)
    {
		if(command.getName().equals("mirror"))
			return new ExampleCommand(plugin);
	    // TODO Auto-generated method stub
	    return null;
    }
}