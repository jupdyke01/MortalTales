package me.jupdyke01.mtcore.commands.combat;

import me.jupdyke01.mtcore.Lang;
import net.md_5.bungee.api.ChatColor;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.Scanner;

public class RollCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {

        if (!(sender instanceof Player p))
            return true;

        if (args.length == 0) {
            Random r = new Random();
            int result = r.nextInt(20) + 1;
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (p.getWorld().equals(target.getWorld())) {
                    if (p.getLocation().distance(target.getLocation()) < 20) {
                        target.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + p.getName() + ChatColor.GRAY + " has rolled: " + ChatColor.YELLOW + "20" + ChatColor.GRAY + "!");
                        target.sendMessage(ChatColor.GRAY + "Result: " + ChatColor.YELLOW + result);
                    }
                }
            }
            return false;
        }
        Random r = new Random();

        int baseRoll = new Scanner(args[0]).useDelimiter("\\D+").nextInt();
        int result = r.nextInt(baseRoll) + 1;

        int expression = (int) new ExpressionBuilder(args[0]).build().evaluate();
        expression += result;
        expression -= baseRoll;


        for (Player target : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().equals(target.getWorld())) {
                if (p.getLocation().distance(target.getLocation()) < 20) {
                    target.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + p.getName() + ChatColor.GRAY + " has rolled: " + ChatColor.YELLOW + args[0] + ChatColor.GRAY + "!");
                    target.sendMessage(ChatColor.GRAY + "Result: " + ChatColor.YELLOW + expression);
                }
            }
        }
        return false;
    }
}
