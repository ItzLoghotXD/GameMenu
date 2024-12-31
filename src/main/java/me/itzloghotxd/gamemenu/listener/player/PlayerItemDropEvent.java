package me.itzloghotxd.gamemenu.listener.player;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemDropEvent implements Listener {

    public PlayerItemDropEvent() {
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
        String item = config.getString("server_menu_item.material", "NETHER_STAR");

        ItemStack droppedItem = event.getItemDrop().getItemStack();

        Material material = Material.getMaterial(item);
        if (material == null) {
            return;
        }

        if (droppedItem.getType() == material) {
            Player player = event.getPlayer();
            event.setCancelled(true);
            Bukkit.dispatchCommand(player, "gm menu");
        }
    }
}
