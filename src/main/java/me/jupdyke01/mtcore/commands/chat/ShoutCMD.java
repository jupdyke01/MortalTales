package me.jupdyke01.mtcore.commands.chat;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShoutCMD implements CommandExecutor {

    MTCore main;

    public ShoutCMD(MTCore main) {
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
                if (p.getLocation().distance(target.getLocation()) <= 60) {
                        String formatMessage = message.toString();
                        formatMessage = formatMessage.replaceAll("\\*", mp.getSettings().getEmoteColor() + "");
                        formatMessage = formatMessage.replaceAll("\"", ChatColor.WHITE + "\"");

                        TextComponent start = new TextComponent("");
                    TextComponent name = new TextComponent(ChatColor.GRAY + "" + ChatColor.BOLD + "<" + ChatColor.RED + "S" + ChatColor.GRAY + ChatColor.BOLD + "> " + ChatColor.RESET + ChatColor.AQUA + mp.getActiveChar().getName() + ChatColor.RESET + ": ");
                    name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(p.getName()).create()));
                    TextComponent messageRaw = new TextComponent(ChatColor.GRAY + formatMessage);
                    messageRaw.setHoverEvent(null);
                    start.addExtra(name);
                    start.addExtra(messageRaw);
                    target.spigot().sendMessage(start);



                    return true;
                }
            }
        }
        return true;
    }
}
