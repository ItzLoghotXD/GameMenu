package me.itzloghotxd.gamemenu.inventory.guis;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigType;
import me.itzloghotxd.gamemenu.inventory.AbstractInventory;
import me.itzloghotxd.gamemenu.inventory.InventoryPlayer;
import me.itzloghotxd.gamemenu.utility.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class MainMenu extends AbstractInventory {
    private Player player;

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

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || !clickedItem.hasItemMeta()) return;

        ItemMeta meta = clickedItem.getItemMeta();
        if (meta == null) return;

        String displayName = meta.getDisplayName();
        Material material = clickedItem.getType();

        String item = null;

        for (String entry : Objects.requireNonNull(config.getConfigurationSection("menu.items")).getKeys(false)) {
            String itemDisplayName = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("menu.items." + entry + ".display_name")));
            Material itemMaterial = Material.matchMaterial(Objects.requireNonNull(config.getString("menu.items." + entry + ".material")));
            int slot = config.getInt("menu.items." + entry + ".slot");

            if (itemMaterial != null && itemMaterial == material && itemDisplayName.equals(displayName) && slot == event.getRawSlot()) {
                item = "menu.items." + entry;
                break;
            }
        }

        if (item != null) {
            player = (Player) event.getWhoClicked();
            List<String> commands = config.getStringList(item + ".commands");
            for (String command : commands) {
                command = PlaceholderUtil.setPlaceholders(command, player);
                if (command.equalsIgnoreCase("close")) {
                    player.closeInventory();
                } else {
                    if (command.toUpperCase().startsWith("CONSOLE:")) {
                        String consoleCommand = command.substring("CONSOLE:".length()).trim();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consoleCommand);
                    } else {
                        Bukkit.dispatchCommand(player, command);
                    }
                }
            }
        }
    }

    @Override
    public void setItems() {
        for (String entry : Objects.requireNonNull(config.getConfigurationSection("menu.items")).getKeys(false)){
            int slot = config.getInt("menu.items." + entry + ".slot");
            int amount = config.getInt("menu.items." + entry + ".amount", 1);
            boolean glow = config.getBoolean("menu.items." + entry + ".glow", false);
            Material material = Material.matchMaterial(config.getString("menu.items." + entry + ".material", "BARRIER"));
            String name = config.getString("menu.items." + entry + ".display_name");
            List<String> lore = config.getStringList("menu.items." + entry + ".lore");

            if (slot >= 0 && slot < getSlots() && material != null) {
                ItemStack item = new ItemStack(material);
                ItemMeta meta = item.getItemMeta();

                if (meta != null) {
                    if (name != null) {
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', PlaceholderUtil.setPlaceholders(name, player)));
                    }

                    lore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', PlaceholderUtil.setPlaceholders(textToTranslate, player)));
                    meta.setLore(lore);
                    if (glow) {
                        meta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    }

                    item.setItemMeta(meta);
                    if (amount >= 64) {
                        item.setAmount(64);
                    } else {
                        item.setAmount(amount);
                    }
                }

                inventory.setItem(slot, item);
            }
        }
    }
}
