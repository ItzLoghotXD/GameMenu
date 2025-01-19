package me.itzloghotxd.gamemenu.utility.item;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a custom item.
 */
public interface Item {

    /**
     * Creates the ItemStack representation of the custom item.
     *
     * @return The customized ItemStack.
     */
    ItemStack create();

    /**
     * Gets the unique identifier for the custom item.
     *
     * @return The identifier.
     */
    String getId();
}
