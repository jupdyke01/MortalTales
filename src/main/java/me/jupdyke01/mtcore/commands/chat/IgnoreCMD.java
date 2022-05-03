package me.jupdyke01.mtcore.commands.chat;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IgnoreCMD implements CommandExecutor {

    MTCore main;

    public IgnoreCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p))
            return true;

        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        if (args.length == 0) {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You must enter a target to ignore or unignore!");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + args[0] + ChatColor.RED + " is not a valid target!");
            return true;
        }
        if (mp.isIgnored(target)) {
            mp.remIgnored(target);
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + args[0] + ChatColor.GRAY + " removed from your ignored list.");
        } else {
            mp.addIgnored(target);
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + args[0] + ChatColor.GRAY + " added to your ignored list.");
        }
        return true;
    }
}
