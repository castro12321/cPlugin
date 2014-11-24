/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.base.plugin;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;


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
}
