/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
