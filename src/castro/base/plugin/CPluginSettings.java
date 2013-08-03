package castro.base.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;

import castro.base.GenericCommandMgr;

public class CPluginSettings
{
	public boolean useConfig = false;
	public List<Listener> listeners = new ArrayList<Listener>();
	public GenericCommandMgr commandMgr = null;
}
