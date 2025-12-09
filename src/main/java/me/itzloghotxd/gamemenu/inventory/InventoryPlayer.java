package me.itzloghotxd.gamemenu.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class InventoryPlayer {

    private final UUID uuid;

    public InventoryPlayer(Player player) {
        uuid = player.getUniqueId();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
