package me.itzloghotxd.gamemenu.listener.player;

import me.itzloghotxd.gamemenu.utility.item.ItemHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerOffHandSwapEvent implements Listener {

    public PlayerOffHandSwapEvent() {
    }

    @EventHandler
    public void onPlayerOffHandSwap(PlayerSwapHandItemsEvent event) {
        ItemStack swappedItem = event.getOffHandItem();

        if (ItemHandler.isCustomItem(swappedItem, "hotbar_item", "server_menu_item")) {
            event.setCancelled(true);
        }
    }
}
