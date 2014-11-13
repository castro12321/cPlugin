package castro.base.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CBukkit
{
	
	@SuppressWarnings("deprecation")
    public static Player[] getOnlinePlayers()
	{
		return Bukkit.getOnlinePlayers();
	}
	
	public static Player getPlayer(String name)
	{
		return Bukkit.getPlayer(name);
	}
}
