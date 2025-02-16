package me.itzloghotxd.gamemenu.commands.subCommands;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import me.itzloghotxd.gamemenu.commands.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadCommand implements Command {

    @Override
    public void execute(CommandSender sender, String[] args) {
        GamemenuPlugin.getPlugin().onReload();
        sender.sendMessage(ChatColor.DARK_GREEN + "GameMenu " + ChatColor.GRAY + "|" + ChatColor.GREEN + " Reloaded!");
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
