package me.itzloghotxd.gamemenu.commands;

import me.itzloghotxd.gamemenu.commands.subCommands.*;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {

    private final Map<String, Command> subCommands = new HashMap<>();

    public CommandManager() {
        registerCommand("help", new HelpCommand());
        registerCommand("reload", new ReloadCommand());
        registerCommand("menu", new MenuCommand());
    }

    public void registerCommand(String name, Command command) {
        subCommands.put(name.toLowerCase(), command);
    }

    public boolean executeCommand(CommandSender sender, String[] args) {
        Command subCommand = subCommands.get(args[0].toLowerCase());
        if (subCommand == null) {
            sender.sendMessage("Unknown subcommand. Use /gamemenu help for a list of commands.");
            return true;
        }

        if (!sender.hasPermission(subCommand.getPermission())) {
            sender.sendMessage("You don't have permission to use this command.");
            return true;
        }

        subCommand.execute(sender, args);
        return true;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return subCommands.keySet().stream().filter(subCommand -> subCommand.startsWith(args[0].toLowerCase())).toList();
        }

        if (args.length > 1) {
            Command subCommand = subCommands.get(args[0].toLowerCase());
            if (subCommand != null) {
                return subCommand.onTabComplete(sender, args);
            }
        }

        return List.of();
    }
}
