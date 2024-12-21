package me.itzloghotxd.gamemenu.listener.inventory;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemClickedEvent implements Listener {

    private String item;

    public ItemClickedEvent() {
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
        var clickedItem = event.getCurrentItem();
        var clickedInventory = event.getClickedInventory();
        item = config.getString("server_menu.item");
        if (event.getWhoClicked() instanceof Player player) {
            if (clickedItem.getType() == Material.getMaterial(item)){
                event.setCancelled(true);
                Bukkit.dispatchCommand(player,"gm menu");
            }
        }
    }
}
