package me.itzloghotxd.gamemenu.config;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private Map<ConfigType, ConfigHandler> configurations = new HashMap();

    public ConfigManager() {
    }

    public void loadFiles(GamemenuPlugin plugin) {
        this.registerFile(ConfigType.SETTINGS, new ConfigHandler(plugin, "config"));
        this.registerFile(ConfigType.DATA, new ConfigHandler(plugin, "data"));
        this.configurations.values().forEach(ConfigHandler::saveDefaultConfig);
    }

    public void saveFiles() {
        this.getFiles(ConfigType.DATA).save();
    }

    public void reloadFiles() {
        this.configurations.values().forEach(ConfigHandler::reload);
    }

    public ConfigHandler getFiles(ConfigType type) {
        return (ConfigHandler)this.configurations.get(type);
    }

    public void registerFile(ConfigType type,ConfigHandler config) {
        this.configurations.put(type, config);
    }

    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }
}
