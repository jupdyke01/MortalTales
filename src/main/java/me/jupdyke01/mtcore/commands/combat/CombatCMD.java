package me.jupdyke01.mtcore.commands.combat;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.combat.Combat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CombatCMD implements CommandExecutor {

    private MTCore main;

    public CombatCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You must be a player to use this command!");
            return true;
        }

        Player p = (Player) sender;

        switch(args.length) {
            case 1: {
                if (args[0].equalsIgnoreCase("exit") || args[0].equalsIgnoreCase("leave")) {
                    Combat combat = main.getCombatManager().getPlayerCombat(p);
                    if (combat == null) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You are not in combat!");
                        return true;
                    }
                    combat.removeParticipant(p);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully left combat.");
                } if (args[0].equalsIgnoreCase("start")) {
                    if (main.getCombatManager().getPlayerCombat(p) != null) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You are already engaged in combat!");
                        return true;
                    }
                    main.getCombatManager().createCombat(p);
                    Combat combat = main.getCombatManager().getPlayerCombat(p);
                    for (Player nearbyPlayer : Bukkit.getOnlinePlayers()) {
                        if (!(nearbyPlayer.getWorld() == p.getWorld()))
                            continue;
                        if (nearbyPlayer == p)
                            continue;
                        if (p.getLocation().distance(nearbyPlayer.getLocation()) <= 20) {
                            combat.invitePlayer(nearbyPlayer, p);
                        }
                    }
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "You have began combat! Everyone nearby has been invited.");
                    break;
                }
                break;
            }
            case 2: {
                Player target = main.getServer().getPlayer(args[1]);
                if (target == null) {
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + args[1] + ChatColor.RED + " is not a valid player!");
                    return true;
                }


                if (args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("add")) {
                    Combat combat = main.getCombatManager().getPlayerCombat(p);
                    if (combat == null) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You are not in combat!");
                        return true;
                    }
                    if (main.getCombatManager().getPlayerCombat(target) != null) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "That player is already in combat!");
                        return false;
                    }
                    combat.invitePlayer(target, p);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Player has been invited.");
                }
                if (args[0].equalsIgnoreCase("join")) {
                    Combat combat = main.getCombatManager().getPlayerCombat(target);
                    if (combat == null) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "That player is no longer in combat!");
                        return true;
                    }
                    if (!combat.isInvited(p)) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You have not been invited to this combat!");
                        return true;
                    }
                    combat.addParticipant(p);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully joined combat.");
                }
                break;
            }
            default: {
                // list combat commands
                break;
            }
        }

        return true;
    }
}
