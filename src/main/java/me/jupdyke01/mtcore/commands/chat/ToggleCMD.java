package me.jupdyke01.mtcore.commands.chat;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleCMD implements CommandExecutor {

    private MTCore main;

    public ToggleCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can not use this command from console!");
            return true;
        }
        Player p = (Player) sender;
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        if (mp.getSettings().isGlobalChat()) {
            mp.getSettings().setGlobalChat(false);
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Global Chat: " + ChatColor.RED + "DISABLED");
        } else {
            mp.getSettings().setGlobalChat(true);
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Global Chat: " + ChatColor.GREEN + "ENABLED");
        }
        return true;
    }
}
