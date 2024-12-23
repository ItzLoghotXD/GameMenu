package me.itzloghotxd.gamemenu.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface Command {
    void execute(CommandSender sender, String[] args);

    List<String> onTabComplete(CommandSender sender, String[] args);

    String getPermission();
}
