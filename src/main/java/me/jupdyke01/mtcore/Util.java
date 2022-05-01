package me.jupdyke01.mtcore;

import me.jupdyke01.mtcore.enums.Race;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Util {

    public static ChatColor chatColorFromString(String str) {
        String chatColor = ChatColor.stripColor(str);
        chatColor = chatColor.replaceAll(" ", "_");
        try {
            return ChatColor.valueOf(chatColor.toUpperCase());
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Race raceFromString(String str) {
        String race = ChatColor.stripColor(str);
        race = race.replaceAll(" ", "_");
        try {
            return Race.valueOf(race.toUpperCase());
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Material woolColorFromChatColor(ChatColor color) {
        return switch (color) {
            case DARK_RED -> Material.RED_WOOL;
            case GOLD -> Material.ORANGE_WOOL;
            case YELLOW -> Material.YELLOW_WOOL;
            case DARK_GREEN -> Material.GREEN_WOOL;
            case GREEN -> Material.LIME_WOOL;
            case DARK_BLUE -> Material.BLUE_WOOL;
            case DARK_AQUA -> Material.CYAN_WOOL;
            case LIGHT_PURPLE -> Material.MAGENTA_WOOL;
            case DARK_PURPLE -> Material.PURPLE_WOOL;
            case DARK_GRAY -> Material.GRAY_WOOL;
            default -> Material.WHITE_WOOL;
        };
    }

}
