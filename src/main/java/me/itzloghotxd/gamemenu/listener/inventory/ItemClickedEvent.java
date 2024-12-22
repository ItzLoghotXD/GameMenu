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

    public ItemClickedEvent() {
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
        String item = config.getString("server_menu.item");

        var clickedItem = event.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        Material material = Material.getMaterial(item);
        if (material == null) {
            return;
        }

        if (clickedItem.getType() == material) {
            if (event.getWhoClicked() instanceof Player player) {
                boolean itemInInventory = false;
                for (var inventoryItem : player.getInventory().getContents()) {
                    if (inventoryItem != null && inventoryItem.getType() == material) {
                        itemInInventory = true;
                        break;
                    }
                }

                if (itemInInventory) {
                    event.setCancelled(true);
                    Bukkit.dispatchCommand(player, "gm menu");
                }
            }
        }
    }
}
