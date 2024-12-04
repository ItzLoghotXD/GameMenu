package me.itzloghotxd.gamemenu.listener.player;

import org.bukkit.Material;
//import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerOffHandSwapEvent implements Listener {
    public PlayerOffHandSwapEvent() {
    }

    @EventHandler
    public void onPlayerOffHandSwap(PlayerSwapHandItemsEvent event) {
        ItemStack itemStack = event.getOffHandItem();
//        Player player = event.getPlayer();
        if (itemStack.getType() == Material.NETHER_STAR) {
            event.setCancelled(true);
        }
    }
}
