package me.itzloghotxd.gamemenu.listener.hotbar;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class HotbarClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
        String item = config.getString("server_menu_item.material", "NETHER_STAR");

        Material material = Material.getMaterial(item);
        int slot = config.getInt("server_menu_item.slot", 8);

        if (event.getView().getType() == InventoryType.CRAFTING) {
            if (event.getSlot() == slot) {
                if (event.getCurrentItem() != null && event.getCurrentItem().getType() == material) {
                    Player player = (Player) event.getWhoClicked();
                    player.performCommand("gm menu");
                    event.setCancelled(true);
                }
            }
        }
    }
}
