package me.itzloghotxd.gamemenu;

import me.itzloghotxd.gamemenu.commands.CommandHandler;
import me.itzloghotxd.gamemenu.commands.CommandManager;
import me.itzloghotxd.gamemenu.config.ConfigManager;
import me.itzloghotxd.gamemenu.inventory.InventoryListener;
import me.itzloghotxd.gamemenu.inventory.InventoryPlayer;
import me.itzloghotxd.gamemenu.listener.hotbar.HotbarClickEvent;
import me.itzloghotxd.gamemenu.listener.hotbar.HotbarItem;
import me.itzloghotxd.gamemenu.listener.player.PlayerInteractionEvent;
import me.itzloghotxd.gamemenu.listener.player.PlayerItemDropEvent;
import me.itzloghotxd.gamemenu.listener.player.PlayerOffHandSwapEvent;
import me.itzloghotxd.gamemenu.utility.item.ItemManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Level;

public final class GamemenuPlugin extends JavaPlugin{

    private static final int BSTATS_ID = 24501;

    private static GamemenuPlugin plugin;
    private ConfigManager configManager;
    private CommandManager commandManager;
    private ItemManager itemManager;

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

        if (this.getServer().getPluginManager().isPluginEnabled(this)) {
            new Metrics(this, BSTATS_ID);

            configManager = new ConfigManager();
            configManager.loadFiles(this);

            commandManager = new CommandManager();
            new CommandHandler(this);

            itemManager = new ItemManager();
            itemManager.loadItems();

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
        itemManager.reloadItems();
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

    public ItemManager getItemManager() {
        return itemManager;
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
