package me.itzloghotxd.gamemenu.listener.hotbar;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class HotbarItem implements Listener {

    private final ItemStack serverMenuItem;
    private final int slot;

    public HotbarItem() {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);
        Material material = Material.matchMaterial(config.getString("server_menu_item.material", "NETHER_STAR"));
        String name = config.getString("server_menu_item.display_name", "&aServer Menu &7(Right Click)");
        List<String> lore = config.getStringList("server_menu_item.lore");

        if (material != null) {
            serverMenuItem = new ItemStack(material, 1);
            ItemMeta meta = serverMenuItem.getItemMeta();

            if (meta != null) {
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                lore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate));
                meta.setLore(lore);
                serverMenuItem.setItemMeta(meta);
            }
        } else {
            serverMenuItem = null;
        }

        int configuredSlot = config.getInt("server_menu_item.slot", 8);
        slot = Math.min(Math.max(configuredSlot, 0), 8);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (serverMenuItem != null) {
            Player player = event.getPlayer();
            player.getInventory().remove(serverMenuItem);
            player.getInventory().setItem(slot, serverMenuItem);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (serverMenuItem != null) {
            Player player = event.getEntity();
            event.getDrops().removeIf(drop -> drop.isSimilar(serverMenuItem));
            GamemenuPlugin.getPlugin().getServer().getScheduler().runTaskLater(GamemenuPlugin.getPlugin(), () -> {
                player.getInventory().setItem(slot, serverMenuItem);
            }, 0L);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (serverMenuItem != null) {
            Player player = event.getPlayer();
            player.getInventory().remove(serverMenuItem);
        }
    }
}
