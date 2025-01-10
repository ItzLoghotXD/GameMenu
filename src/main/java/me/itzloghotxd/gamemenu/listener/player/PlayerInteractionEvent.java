package me.itzloghotxd.gamemenu.listener.player;

import me.itzloghotxd.gamemenu.utility.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractionEvent implements Listener {

    public PlayerInteractionEvent(){
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;

        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();

        if (CustomItem.isCustomItem(itemInMainHand, "hotbar_item", "server_menu_item")) {
            event.getPlayer().performCommand("gm menu");
            event.setCancelled(true);
        }
    }
}
