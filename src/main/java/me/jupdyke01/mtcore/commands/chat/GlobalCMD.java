package me.jupdyke01.mtcore.commands.chat;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalCMD implements CommandExecutor {

    private MTCore main;

    public GlobalCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Lang.PREFIX.getLang() + org.bukkit.ChatColor.RED + "You must put a message!");
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < args.length; i++)
            message.append(args[i] + " ");
        message.delete(message.length() - 1, message.length());

        if (!(sender instanceof Player)) {
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (main.getMortalPlayerManager().getPlayer(target.getUniqueId()).getSettings().isGlobalChat())
                    target.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "<" + ChatColor.YELLOW + "Global" + ChatColor.GRAY + ChatColor.BOLD + "> " + ChatColor.WHITE + "CONSOLE: " + message.toString());
            }
        } else {
            Player p = (Player) sender;
            MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
            if (mp.getSettings().isGlobalChat()) {
                for (Player target : Bukkit.getOnlinePlayers()) {
                    MortalPlayer tp = main.getMortalPlayerManager().getPlayer(target.getUniqueId());
                    if (main.getMortalPlayerManager().getPlayer(target.getUniqueId()).getSettings().isGlobalChat()) {
                        if (!tp.isIgnored(p)) {
                            target.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "<" + ChatColor.YELLOW + "Global" + ChatColor.GRAY + ChatColor.BOLD + "> " + p.getDisplayName() + mp.getTag().getSuffix() + ChatColor.WHITE + ": " + message.toString());
                        }
                    }
                }
            } else {
                p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You need to enable global before sending messages in it! /toggle.");
                return true;
            }
        }
        return true;
    }
}
