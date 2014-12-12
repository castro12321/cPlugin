/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.base.plugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;


public abstract class CPlugin extends CPluginMsg
{
	public static CPlugin baseinstance;
	public static Economy economy;
	public static Permission permissions;
	
	protected abstract void init();
	
	
	@Override
	protected void initBase()
	{
		baseinstance = this;
		super.initBase();
		registerVault();
	}
	
	
	private void registerVault()
	{
		try
		{
			ServicesManager services = getServer().getServicesManager();
			RegisteredServiceProvider<Economy> economyProvider = services.getRegistration(net.milkbowl.vault.economy.Economy.class);
			if (economyProvider != null && economy == null)
				economy = economyProvider.getProvider();
			RegisteredServiceProvider<Permission> permissionProvider = services.getRegistration(net.milkbowl.vault.permission.Permission.class);
			if (permissionProvider != null && permissions == null)
				permissions = permissionProvider.getProvider();
		}
		catch(Exception e)
		{
			log("Vault not present or something strange happened.");
		}
	}
	
	
	public final void onEnable()
	{
		initBase();
		init();
	}
	
	@SuppressWarnings("deprecation")
    public static OfflinePlayer getOfflinePlayer(String playername)
	{
		return Bukkit.getOfflinePlayer(playername);
	}
	
	@SuppressWarnings("deprecation")
    public static boolean hasPermission(OfflinePlayer player, String permission)
	{
		return permissions.has((World)null, player.getName(), permission);
	}
	
    public static boolean hasPermission(String playername, String permission)
	{
		return hasPermission(getOfflinePlayer(playername), permission);
	}
    
    @SuppressWarnings("deprecation")
    public static boolean addPermission(String playername, String permission)
    {
    	return permissions.playerAdd((String)null, playername, permission);
    }
    
    @SuppressWarnings("deprecation")
    public static boolean removePermission(String playername, String permission)
    {
    	return permissions.playerRemove((String)null, playername, permission);
    }
    
    @SuppressWarnings("deprecation")
    public static EconomyResponse withdrawPlayer(String playername, double price)
    {
    	return economy.withdrawPlayer(playername, price);
    }
    
    @SuppressWarnings("deprecation")
    public static boolean removeFromGroup(String playername, String group)
    {
    	return permissions.playerRemoveGroup((String)null, playername, group);
    }
}
