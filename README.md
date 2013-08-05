cPlugin
=======

API plugin to use by other plugins

This plugin doesn't do anything ot it's own. It is designed to work as API for other plugins.

To use this plugin, you need to inherit main class from cPlugin instead of JavaPlugin.
To use commands, you need to pass object of GenericCommandMgr by getSettings() method.

If you need some examples, see my other plugins.
