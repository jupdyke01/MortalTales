package me.jupdyke01.mtcore.commands.chat;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EmoteAddCMD implements CommandExecutor {

    private final MTCore main;

    public EmoteAddCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.isOp()) {
            sender.sendMessage(Lang.PREFIX.getLang() + org.bukkit.ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You must enter a player's name!");
            return true;
        }
        Player target = main.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + args[0] + ChatColor.RED + " is not a valid target!");
            return true;
        }

        MortalPlayer tp = main.getMortalPlayerManager().getPlayer(target.getUniqueId());
        tp.setEmoteUnlocks(tp.getEmoteUnlocks() + 1);
        sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully added an emote change.");
        return true;
    }
}
