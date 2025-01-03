package me.itzloghotxd.gamemenu.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * ConfigHandler is responsible for handling the configuration files of the plugin.
 * It provides methods to save, reload, and get configurations.
 */
public class ConfigHandler {
    private final JavaPlugin plugin;
    private final String name;
    private final File file;
    private FileConfiguration configuration;

    /**
     * Constructs a new ConfigHandler.
     *
     * @param plugin the instance of the plugin
     * @param name   the name of the configuration file
     */
    public ConfigHandler(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name + ".yml"; // Append .yml extension to the name
        this.file = new File(plugin.getDataFolder(), this.name); // Locate the file in the plugin's data folder
        this.configuration = new YamlConfiguration(); // Initialize the configuration object
    }

    /**
     * Saves the default configuration file if it does not exist.
     * Loads the configuration file into memory.
     */
    public void saveDefaultConfig() {
        if (!this.file.exists()) { // Check if the file does not exist
            this.plugin.saveResource(this.name, false); // Save the default resource from the plugin's jar
        }

        try {
            this.configuration.load(this.file); // Load the configuration from the file
        } catch (IOException | InvalidConfigurationException var1) {
            var1.printStackTrace(); // Print the stack trace for debugging
            // Log configuration errors and disable the plugin
            this.plugin.getLogger().severe("============= CONFIGURATION ERROR =============");
            this.plugin.getLogger().severe("There was an error loading " + this.name);
            this.plugin.getLogger().severe("Please check for any obvious configuration mistakes");
            this.plugin.getLogger().severe("such as using tabs for spaces or forgetting to end quotes");
            this.plugin.getLogger().severe("before reporting to the developer. The plugin will now disable..");
            this.plugin.getLogger().severe("============= CONFIGURATION ERROR =============");
            this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
        }
    }

    /**
     * Saves the current configuration to the file.
     */
    public void save() {
        if (this.configuration != null && this.file != null) { // Check if configuration and file are not null
            try {
                this.getConfig().save(this.file); // Save the configuration to the file
            } catch (IOException var2) {
                var2.printStackTrace(); // Print the stack trace for debugging
            }
        }
    }

    /**
     * Reloads the configuration from the file.
     */
    public void reload() {
        this.configuration = YamlConfiguration.loadConfiguration(this.file); // Reload the configuration from the file
    }

    /**
     * Gets the current configuration.
     *
     * @return the configuration
     */
    public FileConfiguration getConfig() {
        return this.configuration; // Return the current configuration
    }
}
