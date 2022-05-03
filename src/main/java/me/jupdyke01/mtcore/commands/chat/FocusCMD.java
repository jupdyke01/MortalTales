package me.jupdyke01.mtcore.commands.chat;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FocusCMD implements CommandExecutor {

    MTCore main;

    public FocusCMD(MTCore main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can not use this command from console.");
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You must enter a player name!");
            return true;
        }

        Player target = main.getServer().getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + args[0] + ChatColor.RED + " is not a valid target!");
            return true;
        }
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());

        if (mp.isFocused(target)) {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Player: " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " has been taken out of focus!");
            mp.remFocused(target);
            mp.focusScoreBoard(p);
        } else {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Player: " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " has been put in focus!");
            mp.addFocused(target);
            mp.focusScoreBoard(p);
        }



        return true;
    }
}
