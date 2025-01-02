package me.itzloghotxd.gamemenu.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.Objects;

public class InventoryListener implements Listener {

    public InventoryListener() {
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryHolder holder = Objects.requireNonNull(event.getClickedInventory()).getHolder();

        if (holder instanceof AbstractInventory inventory) {
            event.setCancelled(true);
            inventory.handleInventory(event);
        }

    }
}
