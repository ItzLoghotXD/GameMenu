package me.itzloghotxd.gamemenu.listener.player;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerOffHandSwapEvent implements Listener {

    public PlayerOffHandSwapEvent() {
    }

    @EventHandler
    public void onPlayerOffHandSwap(PlayerSwapHandItemsEvent event) {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
        String item = config.getString("server_menu_item.material", "NETHER_STAR");
        boolean offHandSwap = config.getBoolean("disable_off_hand_swap", false);

        if (offHandSwap) {
            event.setCancelled(true);
        }

        ItemStack itemStack = event.getOffHandItem();

        Material material = Material.getMaterial(item);
        if (material == null) {
            return;
        }

        if (itemStack != null && itemStack.getType() == material) {
            event.setCancelled(true);
        }
    }
}
