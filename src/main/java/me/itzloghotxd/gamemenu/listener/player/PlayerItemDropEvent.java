package me.itzloghotxd.gamemenu.listener.player;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemDropEvent implements Listener {

    private String item;

    public PlayerItemDropEvent() {
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
        ItemStack itemStack = event.getItemDrop().getItemStack();
        Player player = event.getPlayer();
        item = config.getString("server_selector.item");
        if (itemStack.getType() == Material.getMaterial(item)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You can't drop this item");
        }
    }
}
