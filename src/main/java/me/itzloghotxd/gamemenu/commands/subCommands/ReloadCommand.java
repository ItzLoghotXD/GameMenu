package me.itzloghotxd.gamemenu.commands.subCommands;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.commands.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadCommand implements Command {

    @Override
    public void execute(CommandSender sender, String[] args) {
        GamemenuPlugin.getPlugin().onReload();
        sender.sendMessage("&2GameMenu &8| &eReloaded!");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return List.of();
    }

    @Override
    public String getPermission() {
        return "gamemenu.command.reload";
    }
}
