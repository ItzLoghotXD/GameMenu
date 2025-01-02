package me.itzloghotxd.gamemenu.commands.subCommands;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.commands.Command;
import me.itzloghotxd.gamemenu.inventory.guis.MainMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MenuCommand implements Command {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player player){
            if (args.length > 0 && args[0].equalsIgnoreCase("close")) {
                player.closeInventory();
            } else {
                MainMenu menu = new MainMenu(GamemenuPlugin.getInventoryPlayer(player));
                menu.open();
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return List.of();
    }

    @Override
    public String getPermission() {
        return "gamemenu.command.menu";
    }
}
