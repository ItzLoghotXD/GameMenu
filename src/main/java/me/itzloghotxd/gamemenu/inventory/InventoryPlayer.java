package me.itzloghotxd.gamemenu.inventory;

import org.bukkit.entity.Player;

public class InventoryPlayer {

    private Player player;

    public InventoryPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
