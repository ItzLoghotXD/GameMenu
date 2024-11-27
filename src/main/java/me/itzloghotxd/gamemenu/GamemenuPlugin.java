package me.itzloghotxd.gamemenu;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class GamemenuPlugin extends JavaPlugin {

    public GamemenuPlugin() {
    }

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        this.getLogger().log(Level.INFO, "");
        this.getLogger().log(Level.INFO, "GameManu");
        this.getLogger().log(Level.INFO, "Loading" + this.getDescription().getVersion());
        this.getLogger().log(Level.INFO, "Made with  by ItzLoghotXD");
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
            this.getLogger().log(Level.INFO, "Successfully loaded in" + (System.currentTimeMillis() - start) + "ms!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
