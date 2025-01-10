package me.itzloghotxd.gamemenu.utility;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.stream.Collectors;

import me.itzloghotxd.gamemenu.GamemenuPlugin;

public class CustomItem {

    private static final GamemenuPlugin PLUGIN = GamemenuPlugin.getPlugin();

    /**
     * Creates a custom item with metadata, display name, and lore.
     *
     * @param material   The material of the item.
     * @param displayName The display name of the item (supports & color codes).
     * @param lore        The lore of the item (supports & color codes).
     * @param metadataKey The key for persistent metadata.
     * @param metadataValue The value for persistent metadata.
     * @return The customized ItemStack.
     */
    public static ItemStack createCustomItem(Material material, String displayName, List<String> lore, String metadataKey, String metadataValue) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            // Set the display name with color codes
            if (displayName != null && !displayName.isEmpty()) {
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
            }

            // Set the lore with color codes
            if (lore != null && !lore.isEmpty()) {
                meta.setLore(lore.stream()
                        .map(line -> ChatColor.translateAlternateColorCodes('&', line))
                        .collect(Collectors.toList()));
            }

            // Set persistent metadata
            if (metadataKey != null && metadataValue != null) {
                NamespacedKey key = new NamespacedKey(PLUGIN, metadataKey);
                PersistentDataContainer container = meta.getPersistentDataContainer();
                container.set(key, PersistentDataType.STRING, metadataValue);
            }

            item.setItemMeta(meta);
        }
        return item;
    }

    /**
     * Checks if an item matches specific metadata.
     *
     * @param item          The ItemStack to check.
     * @param metadataKey   The key for persistent metadata.
     * @param metadataValue The expected value of the metadata.
     * @return True if the item matches, false otherwise.
     */
    public static boolean isCustomItem(ItemStack item, String metadataKey, String metadataValue) {
        if (item == null || !item.hasItemMeta()) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return false;
        }
        PersistentDataContainer container = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(PLUGIN, metadataKey);
        return metadataValue.equals(container.get(key, PersistentDataType.STRING));
    }
}
