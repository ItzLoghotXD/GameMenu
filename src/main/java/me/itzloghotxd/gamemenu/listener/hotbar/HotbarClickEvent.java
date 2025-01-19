package me.itzloghotxd.gamemenu.listener.hotbar;

import me.itzloghotxd.gamemenu.utility.item.ItemHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class HotbarClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getSlot() == 8) {
            ItemStack currentItem = event.getCurrentItem();

            if (ItemHandler.isCustomItem(currentItem, "hotbar_item", "server_menu_item")) {
                player.performCommand("gm menu");
                event.setCancelled(true);
            }
        }
    }
}
