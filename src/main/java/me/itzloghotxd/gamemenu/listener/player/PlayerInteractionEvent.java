package me.itzloghotxd.gamemenu.listener.player;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.PlayerInventory;

public class PlayerInteractionEvent implements Listener {

    public PlayerInteractionEvent(){
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
        String item = config.getString("server_menu_item.material", "NETHER_STAR");

        Material material = Material.getMaterial(item);
        int slot = config.getInt("server_menu_item.slot", 8);

        if (event.getHand() != EquipmentSlot.HAND) return;

        PlayerInventory inventory = event.getPlayer().getInventory();
        if (inventory.getHeldItemSlot() == slot) {
            if (inventory.getItemInMainHand().getType() == material) {
                event.getPlayer().performCommand("gm menu");
                event.setCancelled(true);
            }
        }
    }
}
