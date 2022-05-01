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

public class StaffCMD implements CommandExecutor {

    private MTCore main;

    public StaffCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("mortalplayer.staff")) {
            sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You must put a message!");
            return true;
        }
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < args.length; i++)
            message.append(args[i] + " ");
        message.delete(message.length() - 1, message.length());

        for (Player target : Bukkit.getOnlinePlayers()) {
            MortalPlayer tp = main.getMortalPlayerManager().getPlayer(target.getUniqueId());
            if (tp.canSeeStaff()) {
                target.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "<" + ChatColor.RED + "STAFF" + ChatColor.GRAY + ChatColor.BOLD + "> " + ChatColor.RESET + ChatColor.AQUA + sender.getName() + ChatColor.RESET + ": " + message);
            }
        }
        return true;
    }
}
