/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

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