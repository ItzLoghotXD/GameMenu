package me.itzloghotxd.gamemenu.utility;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceholderUtil {

    public static boolean PAPI;

    public static String setPlaceholders(String text, Player player) {

        if (text.contains("%player%") && player != null) text = text.replace("%player%", player.getName());

        if (text.contains("%online%"))
            text = text.replace("%online%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));

        if (text.contains("%online_max%"))
            text = text.replace("%online_max%", String.valueOf(Bukkit.getServer().getMaxPlayers()));

        if (PAPI && player != null) text = PlaceholderAPI.setPlaceholders(player, text);

        return text;

    }

}
