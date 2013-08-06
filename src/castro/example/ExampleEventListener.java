package castro.example;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;


//We just add regular Bukkit event listener here
public class ExampleEventListener implements Listener
{
	ExamplePlugin plugin;
	
	public ExampleEventListener(ExamplePlugin plugin)
	{
		this.plugin = plugin;
	}
	
	// Let's listen every player move
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		// With this method, player will receive red "We are watching you".
		// If you don't pass third parameter, true will be used as default value
		plugin.sendMessage(player, "&cWe are watching you!", false);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		// With this method, player will receive "[Your_plugin_name] Hello".
		plugin.sendMessage(player, "Hello");
		// Line above is equal to
		// plugin.sendMessage(player, "Hello", true);
		
		// We can also log player joining to console
		plugin.log("Somebody joined the server", false);
		// And broadcast it
		plugin.broadcast("Somebody joined the server", false);
		
		// In both lines above, setting second value to true (default) will add [Your_plugin_name] to message
	}
}