package me.itzloghotxd.gamemenu.commands.subCommands;

import me.itzloghotxd.gamemenu.commands.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class HelpCommand implements Command {
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.AQUA + "--- GameMenu Plugin Help ---");
        sender.sendMessage(ChatColor.GOLD + "/gm" + ChatColor.WHITE + " - Main command");
        sender.sendMessage(ChatColor.GOLD + "/gm help" + ChatColor.WHITE + " - Display this help message");
        sender.sendMessage(ChatColor.GOLD + "/gm reload" + ChatColor.WHITE + " - Reloads the plugin configuration");
        sender.sendMessage(ChatColor.GOLD + "/gm menu" + ChatColor.WHITE + " - Opens the default/main menu");
        sender.sendMessage(ChatColor.AQUA + "------------------------------");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return List.of();
    }

    @Override
    public String getPermission() {
        return "gamemenu.command.help";
    }
}
