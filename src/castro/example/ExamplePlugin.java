package castro.example;

import java.util.Random;

import castro.base.plugin.CPlugin;
import castro.base.plugin.CPluginSettings;
import castro.base.plugin.CUtils;

public class ExamplePlugin extends CPlugin 
{
	public ExampleSQL SQL;
	
	@Override
	protected CPluginSettings getSettings()
	{		
		CPluginSettings settings = new CPluginSettings();
		
		// useConfig loads default config.yml file if it doesn't exist
		// It also initializes con variable with getConfig();
		settings.useConfig = true;
		
		// EventListener will be added to Bukkit automatically
		settings.listeners.add(new ExampleEventListener(this));
		
		// Each command handled by this plugin will be moved to CommandHandler
		settings.commandMgr = new ExampleCommandHandler(this);
		
		return settings;
	}
	
	
	@Override
	protected void init()
	{
		// If we want to measure how much time does function take
		// and don't have access to profiler, we can use built-in feature
		// First, we have to reset timer
		CUtils.reset();
		
		// Then let's do some heavy task
		int a = 0;
		for(int i = 0; i < 100000; ++i)
			a = new Random().nextInt() / 1000;
		// Then let's see how much time it took
		CUtils.timeStep("Heavy task " + a);
		
		// Just initialize SQL
		SQL = new ExampleSQL(this);
		
		// We can also check time again.
		// Time will be counted since last timeStep()
		CUtils.timeStep("SQL init");
	}
}