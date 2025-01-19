package me.itzloghotxd.gamemenu.utility.item;

import me.itzloghotxd.gamemenu.utility.item.items.ServerMenuItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private final Map<String, Item> items = new HashMap<>();

    /**
     * Registers all custom items.
     */
    public void loadItems() {
        items.clear();

        // Register custom items here
        register(new ServerMenuItem());
        // Add other custom items here (e.g., register(new AnotherCustomItem());)

    }

    /**
     * Registers a single custom item.
     *
     * @param item The custom item to register.
     */
    private void register(Item item) {
        items.put(item.getId(), item);
    }

    /**
     * Retrieves a custom item by ID.
     *
     * @param id The ID of the custom item.
     * @return The ItemStack, or null if not found.
     */
    public ItemStack getItem(String id) {
        Item customItem = items.get(id);
        return customItem != null ? customItem.create() : null;
    }

    /**
     * Reloads all custom items.
     */
    public void reloadItems() {
        loadItems();
    }
}
