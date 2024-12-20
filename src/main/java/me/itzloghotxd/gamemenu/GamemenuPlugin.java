package me.itzloghotxd.gamemenu;

import me.itzloghotxd.gamemenu.config.ConfigManager;
import me.itzloghotxd.gamemenu.listener.inventory.ItemClickedEvent;
import me.itzloghotxd.gamemenu.listener.player.PlayerItemDropEvent;
import me.itzloghotxd.gamemenu.listener.player.PlayerOffHandSwapEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public final class GamemenuPlugin extends JavaPlugin {

    private static GamemenuPlugin plugin;
    private ConfigManager configManager;

    public GamemenuPlugin() {
    }

    public void onEnable() {
        long start = System.currentTimeMillis();
        plugin = this;
        this.getLogger().log(Level.INFO, "");
        this.getLogger().log(Level.INFO, "GameMenu");
        this.getLogger().log(Level.INFO, "Version " + this.getDescription().getVersion());
        this.getLogger().log(Level.INFO, "Made with ❤ by ItzLoghotXD");
        this.getLogger().log(Level.INFO, "");

        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
        } catch (ClassNotFoundException var4) {
            this.getLogger().severe("============= PAPER NOT DETECTED =============");
            this.getLogger().severe("Gamemenu requires Paper to run, you can download");
            this.getLogger().severe("Paper here: https://papermc.io/downloads.");
            this.getLogger().severe("The plugin will now disable.");
            this.getLogger().severe("============= PAPER NOT DETECTED =============");
            this.getPluginLoader().disablePlugin(this);
            return;
        }

        if (this.getServer().getPluginManager().isPluginEnabled(this)) {
            this.configManager = new ConfigManager();
            this.configManager.loadFiles(this);
            this.registerEvents();
            this.getLogger().log(Level.INFO, "");
            this.getLogger().log(Level.INFO, "Successfully loaded in " + (System.currentTimeMillis() - start) + "ms!");
        }
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        this.configManager.saveFiles();
    }

    public void onReload() {
        long start = System.currentTimeMillis();
        this.configManager.reloadFiles();
        this.getLogger().log(Level.INFO, "Successfully reloaded in " + (System.currentTimeMillis() - start) + "ms!");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerItemDropEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerOffHandSwapEvent(), this);
        getServer().getPluginManager().registerEvents(new ItemClickedEvent(), this);
    }

    public static GamemenuPlugin getPlugin() {
        return plugin;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }
}
