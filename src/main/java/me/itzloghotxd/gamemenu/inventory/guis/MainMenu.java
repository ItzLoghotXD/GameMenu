package me.itzloghotxd.gamemenu.inventory.guis;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import me.itzloghotxd.gamemenu.inventory.AbstractInventory;
import me.itzloghotxd.gamemenu.inventory.InventoryPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class MainMenu extends AbstractInventory {
    public MainMenu(InventoryPlayer inventoryPlayer) {
        super(inventoryPlayer);
    }

    FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigType.SETTINGS);

    @Override
    public String getInventoryName() {
        String title =  config.getString("menu.title", "Main Menu");
        return ChatColor.translateAlternateColorCodes('&', title);
    }

    @Override
    public int getSlots() {
        int slots = config.getInt("menu.slots", 54);

        if (slots > 54) {
            return 54;
        } else if (slots < 9) {
            return 9;
        }

        if (slots % 9 != 0) {
            int lowerBound = (slots / 9) * 9;
            int upperBound = lowerBound + 9;
            slots = (slots - lowerBound < upperBound - slots) ? lowerBound : upperBound;
        }

        return slots;
    }
    @Override
    public void handleInventory(InventoryClickEvent event) {

    }

    @Override
    public void setItems() {
        for (String entry : Objects.requireNonNull(config.getConfigurationSection("menu.items")).getKeys(false)){
            int slot = config.getInt("menu.items." + entry + ".slot");
            Material material = Material.matchMaterial(config.getString("menu.items." + entry + ".material", "BARRIER"));
            String name = config.getString("menu.items." + entry + ".display_name");
            List<String> lore = config.getStringList("menu.items." + entry + ".lore");

            if (slot >= 0 && slot < getSlots() && material != null) {
                ItemStack item = new ItemStack(material);
                ItemMeta meta = item.getItemMeta();

                if (meta != null) {
                    if (name != null) {
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    }

                    lore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate));
                    meta.setLore(lore);

                    item.setItemMeta(meta);
                }

                inventory.setItem(slot, item);
            }
        }
    }
}
