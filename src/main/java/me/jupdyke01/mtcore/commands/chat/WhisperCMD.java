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

public class WhisperCMD implements CommandExecutor {

    MTCore main;

    public WhisperCMD(MTCore main) {
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
            sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can not use this command from console!");
            return true;
        } else {
            Player p = (Player) sender;
            MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (p.getLocation().distance(target.getLocation()) <= 2) {
                    String formatMessage = message.toString();
                    formatMessage = formatMessage.replaceAll("\\*", mp.getSettings().getEmoteColor() + "");
                    formatMessage = formatMessage.replaceAll("\"", ChatColor.WHITE + "\"");
                    target.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "<" + ChatColor.BLUE + "W" + ChatColor.GRAY + ChatColor.BOLD + "> " + ChatColor.RESET + ChatColor.AQUA + mp.getActiveChar().getName() + ChatColor.RESET + ": " + ChatColor.GRAY + formatMessage);
                    return true;
                }
            }
        }
        return true;
    }

}
