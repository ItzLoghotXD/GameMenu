package me.itzloghotxd.gamemenu.config;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private final Map<ConfigType, ConfigHandler> configurations = new HashMap<>();

    public ConfigManager() {
    }

    public void loadFiles(GamemenuPlugin plugin) {
        registerFile(ConfigType.SETTINGS, new ConfigHandler(plugin, "config.yml"));
        configurations.values().forEach(ConfigHandler::saveDefaultConfig);
    }

    public void saveFiles() {
        getFile(ConfigType.SETTINGS).save();
    }

    public void reloadFiles() {
        configurations.values().forEach(ConfigHandler::reload);
    }

    public ConfigHandler getFile(ConfigType type) {
        return configurations.get(type);
    }

    public void registerFile(ConfigType type, ConfigHandler config) {
        configurations.put(type, config);
    }

    public FileConfiguration getConfig(ConfigType type) {
        return GamemenuPlugin.getPlugin().getConfigManager().getFile(type).getConfig();
    }

//    FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);

    public enum ConfigType {
        SETTINGS;

        ConfigType() {
        }
    }
}
