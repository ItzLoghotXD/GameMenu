package me.itzloghotxd.gamemenu;

import org.bukkit.plugin.java.JavaPlugin;

public final class GameMenu extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getLogger().info("Starting");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
