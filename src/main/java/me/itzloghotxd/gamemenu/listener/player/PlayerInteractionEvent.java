package me.itzloghotxd.gamemenu.listener.player;

import me.itzloghotxd.gamemenu.utility.item.ItemHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractionEvent implements Listener {

    public PlayerInteractionEvent(){
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();

        if (ItemHandler.isCustomItem(itemInMainHand, "hotbar_item", "server_menu_item")) {
            event.getPlayer().performCommand("gm menu");
            event.setCancelled(true);
        }
    }
}
