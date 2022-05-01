package me.jupdyke01.mtcore.commands.combat;

import me.jupdyke01.mtcore.Lang;
import net.md_5.bungee.api.ChatColor;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Random;
import java.util.Scanner;

public class RollCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You must enter a number to roll out of!");
            return false;
        }
        Random r = new Random();

        int baseRoll = new Scanner(args[0]).useDelimiter("\\D+").nextInt();
        int result = r.nextInt(baseRoll) + 1;

        int expression = (int) new ExpressionBuilder(args[0]).build().evaluate();
        expression += result;
        expression -= baseRoll;
        sender.sendMessage("Result: " + expression);
        return false;
    }
}
