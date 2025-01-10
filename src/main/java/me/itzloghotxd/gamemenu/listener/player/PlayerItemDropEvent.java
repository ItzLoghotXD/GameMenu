package me.itzloghotxd.gamemenu.listener.player;

import me.itzloghotxd.gamemenu.utility.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemDropEvent implements Listener {

    public PlayerItemDropEvent() {
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack droppedItem = event.getItemDrop().getItemStack();

        if (CustomItem.isCustomItem(droppedItem, "hotbar_item", "server_menu_item")) {
            event.setCancelled(true);
        }
    }
}
