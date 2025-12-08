package me.itzloghotxd.gamemenu.utility.item.items;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.config.ConfigManager;
import me.itzloghotxd.gamemenu.utility.item.Item;
import me.itzloghotxd.gamemenu.utility.item.ItemHandler;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class ServerMenuItem implements Item {

    @Override
    public ItemStack create() {
        FileConfiguration config = GamemenuPlugin.getPlugin().getConfigManager().getConfig(ConfigManager.ConfigType.SETTINGS);
        return ItemHandler.createCustomItem(
                Material.matchMaterial(config.getString("server_menu_item.material", "NETHER_STAR")),
                config.getString("server_menu_item.display_name", "&aServer Menu &7(Right Click)"),
                config.getStringList("server_menu_item.lore"),
                "hotbar_item",
                "server_menu_item"
        );
    }

    @Override
    public String getId() {
        return "SERVER_MENU_ITEM";
    }
}
