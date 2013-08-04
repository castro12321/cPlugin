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

package castro.base;

import castro.base.plugin.CPlugin;
import castro.base.plugin.CPluginSettings;



public class Plugin extends CPlugin
{
	public static Plugin instance;
	
	@Override
	protected CPluginSettings getSettings()
	{
		instance = this;
		
		CPluginSettings settings = new CPluginSettings();
		settings.useConfig = true;
		return settings;
	}
	
	
	@Override
	protected void init()
	{
	}
}

/*
int i = 1;
boolean load = true;
boolean stop = false;
Runnable r = new Runnable() {
	@Override
	public void run() {
		if(stop)
			return;
		
		if(load)
			getServer().dispatchCommand(getServer().getConsoleSender(), "mv load w"+i);
		else
			getServer().dispatchCommand(getServer().getConsoleSender(), "mv unload w"+i);
		
		if(i++ > 20)
		{
			load = !load;
			i = 1;
		}
		
		//getServer().dispatchCommand(getServer().getConsoleSender(), "mv create w"+i+" normal");
	}
};


@Override
public void onEnable()
{
	getServer().getScheduler().scheduleSyncRepeatingTask(this, r, 20, 20);
}
*/