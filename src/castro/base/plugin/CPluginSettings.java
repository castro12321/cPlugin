/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.base.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;

import castro.base.CCommandMgr;

public class CPluginSettings
{
	public boolean useConfig        = false;
	public List<Listener> listeners = new ArrayList<>();
	public CCommandMgr commandMgr   = null;
}
