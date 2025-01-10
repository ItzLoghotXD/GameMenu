package me.itzloghotxd.gamemenu.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class InventoryListener implements Listener {

    public InventoryListener() {
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getClickedInventory() == null) return;

        Player player = (Player) event.getWhoClicked();
        InventoryHolder holder = event.getClickedInventory().getHolder();

        if (player.getOpenInventory().getTopInventory().getHolder() instanceof AbstractInventory) {
            event.setCancelled(true);
            return;
        }

        if (holder instanceof AbstractInventory inventory) {
            inventory.handleInventory(event);
            event.setCancelled(true);
        }

    }
}
