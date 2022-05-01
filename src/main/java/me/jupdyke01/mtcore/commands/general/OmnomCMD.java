package me.jupdyke01.mtcore.commands.general;

import me.jupdyke01.mtcore.Lang;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OmnomCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            p.setFoodLevel(p.getFoodLevel() - 2);
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "You feel slightly hungry.");
        }
        return true;
    }
}
