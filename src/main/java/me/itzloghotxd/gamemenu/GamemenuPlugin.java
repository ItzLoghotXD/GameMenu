package me.itzloghotxd.gamemenu;

import me.itzloghotxd.gamemenu.commands.CommandHandler;
import me.itzloghotxd.gamemenu.commands.CommandManager;
import me.itzloghotxd.gamemenu.config.ConfigManager;
import me.itzloghotxd.gamemenu.config.ConfigType;
import me.itzloghotxd.gamemenu.inventory.InventoryListener;
import me.itzloghotxd.gamemenu.inventory.InventoryPlayer;
import me.itzloghotxd.gamemenu.listener.hotbar.HotbarClickEvent;
import me.itzloghotxd.gamemenu.listener.hotbar.HotbarItem;
import me.itzloghotxd.gamemenu.listener.player.PlayerInteractionEvent;
import me.itzloghotxd.gamemenu.listener.player.PlayerItemDropEvent;
import me.itzloghotxd.gamemenu.listener.player.PlayerOffHandSwapEvent;
import me.itzloghotxd.gamemenu.utility.CustomItem;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Level;

public final class GamemenuPlugin extends JavaPlugin{

    private static final int BSTATS_ID = 24501;

    private static GamemenuPlugin plugin;
    private ConfigManager configManager;
    private CommandManager commandManager;
    private static ItemStack serverMenuItem;

    private static final HashMap<Player, InventoryPlayer> playerInventory = new HashMap<>();

    public GamemenuPlugin() {
    }

    public void onEnable() {
        long start = System.currentTimeMillis();
        plugin = this;
        getLogger().log(Level.INFO, "");
        getLogger().log(Level.INFO, "GameMenu");
        getLogger().log(Level.INFO, "Version " + this.getDescription().getVersion());
        getLogger().log(Level.INFO, "Made with <3 by ItzLoghotXD");
        getLogger().log(Level.INFO, "https://github.com/ItzLoghotXD/GameMenu");
        getLogger().log(Level.INFO, "");

        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
        } catch (ClassNotFoundException var4) {
            getLogger().severe("============= PAPER NOT DETECTED =============");
            getLogger().severe("Gamemenu requires Paper to run, you can download");
            getLogger().severe("Paper here: https://papermc.io/downloads/all.");
            getLogger().severe("The plugin will now disable.");
            getLogger().severe("============= PAPER NOT DETECTED =============");
            getPluginLoader().disablePlugin(this);
            return;
        }

        if (this.getServer().getPluginManager().isPluginEnabled(this)) {
            new Metrics(this, BSTATS_ID);


            configManager = new ConfigManager();
            configManager.loadFiles(this);

            FileConfiguration config = configManager.getConfig(ConfigType.SETTINGS);
            serverMenuItem = CustomItem.createCustomItem(Material.matchMaterial(config.getString("server_menu_item.material",
                            "NETHER_STAR")),
                    config.getString("server_menu_item.display_name"),
                    config.getStringList("server_menu_item.lore"),
                    "hotbar_item",
                    "server_menu_item"
            );

            commandManager = new CommandManager();
            new CommandHandler(this);
            registerEvents();
            getLogger().log(Level.INFO, "");
            getLogger().log(Level.INFO, "Successfully loaded in " + (System.currentTimeMillis() - start) + "ms!");
        }
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        configManager.saveFiles();
    }

    public void onReload() {
        long start = System.currentTimeMillis();
        configManager.reloadFiles();
        getLogger().log(Level.INFO, "Successfully reloaded in " + (System.currentTimeMillis() - start) + "ms!");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new HotbarClickEvent(), this);
        getServer().getPluginManager().registerEvents(new HotbarItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemDropEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerOffHandSwapEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractionEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    public static GamemenuPlugin getPlugin() {
        return plugin;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public static ItemStack getServerMenuItem() {
        return serverMenuItem;
    }

    public static InventoryPlayer getInventoryPlayer(Player player) {
        InventoryPlayer inventoryPlayer;

        if (playerInventory.containsKey(player)) {
            return playerInventory.get(player);
        }else {
            inventoryPlayer = new InventoryPlayer(player);
            playerInventory.put(player, inventoryPlayer);

            return inventoryPlayer;
        }
    }
}
