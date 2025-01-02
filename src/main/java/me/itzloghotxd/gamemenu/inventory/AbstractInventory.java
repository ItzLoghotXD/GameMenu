package me.itzloghotxd.gamemenu.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractInventory implements InventoryHolder {

    protected Inventory inventory;

    protected InventoryPlayer inventoryPlayer;

    public AbstractInventory(InventoryPlayer inventoryPlayer) {
        this.inventoryPlayer = inventoryPlayer;
    }

    public abstract String getInventoryName();

    public abstract int getSlots();

    public abstract void handleInventory(InventoryClickEvent event);

    public abstract void setItems();

    public void open(){

        inventory = Bukkit.createInventory(this, getSlots(), getInventoryName());

        this.setItems();

        inventoryPlayer.getPlayer().openInventory(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
