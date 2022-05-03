package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.cs.CharacterSheet;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCMD implements CommandExecutor {

    MTCore main;

    public StatsCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player p))
            return true;

        if (!p.hasPermission("mortalplayer.staff")) {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }

        if (args.length == 0) {

            p.sendMessage(ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=" + ChatColor.YELLOW + "/stats" + ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GRAY + "/stats " + ChatColor.YELLOW + "{player} {mw,ml,r} {add/remove/+/-}");
            p.sendMessage(ChatColor.AQUA + "This will add or remove one stat point from the specified player..");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=");
            return true;
        }

        if (args.length != 3) {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Invalid usage! Please use /stats for help.");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + args[0] + ChatColor.RED + " is not a valid target!");
            return true;
        }
        MortalPlayer mt = main.getMortalPlayerManager().getPlayer(target.getUniqueId());
        CharacterSheet active = mt.getActiveChar();

        if (!args[1].equals("mw") && !args[1].equals("ml") && !args[1].equals("r") && !args[1].equals("mortalwounds") && !args[1].equals("magiclevel") && !args[1].equals("range")) {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + args[1] + " is not a valid stat! Please use /stats for help.");
            return true;
        }

        if (args[2].equals("add") || args[2].equals("+")) {
            switch (args[1]) {
                case "mw", "mortalwounds" -> {
                    active.setMortalWoundsAdd(active.getMortalWoundsAdd() + 1);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully increased: " + ChatColor.YELLOW + "MORTAL WOUNDS" + ChatColor.GRAY + " for " + ChatColor.YELLOW + args[0]);
                }
                case "ml", "magiclevel" -> {
                    active.setMagicLevelAdd(active.getMagicLevelAdd() + 1);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully increased: " + ChatColor.YELLOW + "MAGIC LEVEL" + ChatColor.GRAY + " for " + ChatColor.YELLOW + args[0]);
                }
                case "r", "range" -> {
                    active.setRangeAdd(active.getRangeAdd() + 1);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully increased: " + ChatColor.YELLOW + "RANGE" + ChatColor.GRAY + " for " + ChatColor.YELLOW + args[0]);
                }
            }
            return true;
        } else if (args[2].equals("remove") || args[2].equals("-")) {
            switch (args[1]) {
                case "mw", "mortalwounds" -> {
                    active.setMortalWoundsAdd(active.getMortalWoundsAdd() - 1);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully decreased: " + ChatColor.YELLOW + "MORTAL WOUNDS" + ChatColor.GRAY + " for " + ChatColor.YELLOW + args[0]);
                }
                case "ml", "magiclevel" -> {
                    active.setMagicLevelAdd(active.getMagicLevelAdd() - 1);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully decreased: " + ChatColor.YELLOW + "MAGIC LEVEL" + ChatColor.GRAY + " for " + ChatColor.YELLOW + args[0]);
                }
                case "r", "range" -> {
                    active.setRangeAdd(active.getRangeAdd() - 1);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully decreased: " + ChatColor.YELLOW + "RANGE" + ChatColor.GRAY + " for " + ChatColor.YELLOW + args[0]);
                }
            }
            return true;
        } else {
            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + args[2] + " is not a valid modifier! Please use /stats for help.");
            return true;
        }
    }
}
