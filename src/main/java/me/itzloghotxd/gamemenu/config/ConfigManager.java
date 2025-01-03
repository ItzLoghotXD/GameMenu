package me.itzloghotxd.gamemenu.config;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ConfigManager is responsible for managing multiple configuration files.
 * It provides methods to load, save, reload, and get configurations.
 */
public class ConfigManager {
    private final Map<ConfigType, ConfigHandler> configurations = new HashMap<>();

    /**
     * Constructs a new ConfigManager.
     */
    public ConfigManager() {
    }

    /**
     * Loads the configuration files for the plugin.
     *
     * @param plugin the instance of the plugin
     */
    public void loadFiles(GamemenuPlugin plugin) {
        // Register and load the settings configuration file
        this.registerFile(ConfigType.SETTINGS, new ConfigHandler(plugin, "config"));
        // Register and load the data configuration files
        this.registerFile(ConfigType.DATA, new ConfigHandler(plugin, "data"));
        // Save default configurations for all registered files
        this.configurations.values().forEach(ConfigHandler::saveDefaultConfig);
    }

    /**
     * Saves all configuration files.
     */
    public void saveFiles() {
        // Save the data and settings configuration files
        this.getFile(ConfigType.DATA).save();
        this.getFile(ConfigType.SETTINGS).save();
    }

    /**
     * Reloads all configuration files.
     */
    public void reloadFiles() {
        // Reload all registered configuration files
        this.configurations.values().forEach(ConfigHandler::reload);
    }

    /**
     * Gets the ConfigHandler for the specified ConfigType.
     *
     * @param type the type of the configuration
     * @return the ConfigHandler for the specified type
     */
    public ConfigHandler getFile(ConfigType type) {
        return (ConfigHandler)this.configurations.get(type); // Return the ConfigHandler for the specified type
    }

    /**
     * Registers a configuration file with the specified type and handler.
     *
     * @param type   the type of the configuration
     * @param config the handler for the configuration
     */
    public void registerFile(ConfigType type, ConfigHandler config) {
        this.configurations.put(type, config); // Register the configuration handler for the specified type
    }

    /**
     * Gets the FileConfiguration for the specified file.
     *
     * @param file the file to load the configuration from
     * @return the FileConfiguration
     */
    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file); // Load and return the configuration from the file
    }

    /**
     * Gets the FileConfiguration for the specified ConfigType.
     *
     * @param type the type of the configuration
     * @return the FileConfiguration
     */
    public FileConfiguration getConfig(ConfigType type) {
        return GamemenuPlugin.getPlugin().getConfigManager().getFile(type).getConfig(); // Get and return the configuration for the specified type
    }

//    FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
}
