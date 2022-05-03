package me.jupdyke01.mtcore.commands.character;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RacesCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        sender.sendMessage(ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=" + ChatColor.YELLOW + "Races" + ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=");
        sender.sendMessage(" ");
        sender.sendMessage(ChatColor.YELLOW + "Dragonborn");
        sender.sendMessage(ChatColor.YELLOW + "Dwarf");
        sender.sendMessage(ChatColor.YELLOW + "Human");
        sender.sendMessage(ChatColor.YELLOW + "Wood Elf");
        sender.sendMessage(ChatColor.YELLOW + "Dark Elf");
        sender.sendMessage(ChatColor.YELLOW + "Snow Elf");
        sender.sendMessage(" ");
        sender.sendMessage(ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=");


        return true;
    }
}
