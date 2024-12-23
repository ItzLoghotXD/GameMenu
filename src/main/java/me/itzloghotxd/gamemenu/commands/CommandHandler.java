package me.itzloghotxd.gamemenu.commands;

import me.itzloghotxd.gamemenu.GamemenuPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CommandHandler implements CommandExecutor, TabExecutor {

    private final CommandManager commandManager;

    public CommandHandler(JavaPlugin plugin) {
        commandManager = GamemenuPlugin.getPlugin().getCommandManager();
        plugin.getCommand("gamemenu").setExecutor(this);
        plugin.getCommand("gamemenu").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /gamemenu <subcommand>");
            return true;
        }

        return commandManager.executeCommand(sender, args);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return commandManager.getTabCompletions(sender, args);
    }
}
