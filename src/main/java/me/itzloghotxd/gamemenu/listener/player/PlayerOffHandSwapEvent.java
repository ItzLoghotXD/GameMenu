package me.itzloghotxd.gamemenu.listener.player;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import org.bukkit.Material;
//import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerOffHandSwapEvent implements Listener {

    private String item;

    public PlayerOffHandSwapEvent() {
    }

    @EventHandler
    public void onPlayerOffHandSwap(PlayerSwapHandItemsEvent event) {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
        ItemStack itemStack = event.getOffHandItem();
        item = config.getString("server_menu.item");
        if (itemStack.getType() == Material.getMaterial(item)) {
            event.setCancelled(true);
        }
    }
}
