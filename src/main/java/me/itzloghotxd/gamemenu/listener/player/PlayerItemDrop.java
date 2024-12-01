package me.itzloghotxd.gamemenu.listener.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemDrop implements Listener {
    public PlayerItemDrop() {
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        Player player = event.getPlayer();
        if (itemStack.getType() == Material.NETHER_STAR) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You can't drop this item");
        }
    }
}
