package com.freedomservers.RubyFreedom;

import com.freedomservers.RubyFreedom.Listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RFTraining extends JavaPlugin {

    public static RFTraining plugin;
    public Server server;

    @Override
    public void onLoad() {
        RFTraining.plugin = this;
    }

    @Override
    public void onEnable() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListener(), plugin);
        RFLogger.info("RFTraining has been enabled!");
    }

    @Override
    public void onDisable() {
        RFLogger.info("RFTraining has been disabled!");
    }
}
