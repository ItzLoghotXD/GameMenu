package me.itzloghotxd.gamemenu.listener.hotbar;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.utility.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class HotbarItem implements Listener {

    public HotbarItem() {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack serverMenuItem = GamemenuPlugin.getServerMenuItem();

        if (serverMenuItem != null) {
            player.getInventory().setItem(8, serverMenuItem);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        ItemStack serverMenuItem = GamemenuPlugin.getServerMenuItem();

        if (serverMenuItem != null) {
            event.getDrops().removeIf(drop -> CustomItem.isCustomItem(drop, "hotbar_item", "server_menu_item"));
            GamemenuPlugin.getPlugin().getServer().getScheduler().runTaskLater(
                    GamemenuPlugin.getPlugin(),
                    () -> player.getInventory().setItem(8, serverMenuItem),
                    0L
            );
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ItemStack serverMenuItem = GamemenuPlugin.getServerMenuItem();

        if (serverMenuItem != null) {
            player.getInventory().remove(serverMenuItem);
        }
    }
}
