package me.itzloghotxd.gamemenu.commands.subCommands;

import me.itzloghotxd.gamemenu.commands.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class HelpCommand implements Command {
    @Override
    public void execute(CommandSender sender, String[] args) {
        // I will add this later cuz I am very lazy :)
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
