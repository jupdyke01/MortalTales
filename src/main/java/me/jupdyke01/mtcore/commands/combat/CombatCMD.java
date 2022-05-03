package me.jupdyke01.mtcore.commands.combat;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.combat.Combat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
                } else if (args[0].equalsIgnoreCase("start")) {
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
                        if (main.getCombatManager().getPlayerCombat(nearbyPlayer) != null)
                            continue;
                        if (p.getLocation().distance(nearbyPlayer.getLocation()) <= 20) {
                            combat.invitePlayer(nearbyPlayer, p);
                        }
                    }
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "You have began combat! Everyone nearby has been invited.");
                } else if (args[0].equalsIgnoreCase("turn") || args[0].equalsIgnoreCase("end")) {
                    Combat combat = main.getCombatManager().getPlayerCombat(p);
                    if (combat == null) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You are not in combat!");
                        return true;
                    }
                    if (!combat.getPlayerTurn().equals(p)) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "It is not your turn!");
                        return true;
                    }
                    combat.nextTurn();
                } else if (args[0].equalsIgnoreCase("list")) {
                    Combat combat = main.getCombatManager().getPlayerCombat(p);
                    if (combat == null) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You are not in combat!");
                        return true;
                    }
                    StringBuilder sb = new StringBuilder(ChatColor.GRAY + "Combatants: " + ChatColor.YELLOW);
                    for (Player target : combat.getParticipants()) {
                        sb.append(target.getName() + ", ");
                    }
                    sb.delete(sb.length() - 2, sb.length() - 1);
                    p.sendMessage(sb.toString());
                } else if (args[0].equalsIgnoreCase("order")) {
                    Combat combat = main.getCombatManager().getPlayerCombat(p);
                    if (combat == null) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You are not in combat!");
                        return true;
                    }
                    for (int i = 0; i < combat.getParticipants().size(); i++) {
                        int y = i+1;
                        p.sendMessage(ChatColor.GRAY + "" + y + ": " + ChatColor.YELLOW + combat.getParticipants().get(i).getName());
                    }
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
                } else if (args[0].equalsIgnoreCase("join")) {
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
                } else if (args[0].equalsIgnoreCase("decline")) {
                    Combat combat = main.getCombatManager().getPlayerCombat(target);
                    if (combat == null) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "That player is no longer in combat!");
                        return true;
                    }
                    if (!combat.isInvited(p)) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You have not been invited to this combat!");
                        return true;
                    }
                    combat.unInvite(p);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully declined combat.");
                }

                break;
            }
            default: {
                p.sendMessage(ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=" + ChatColor.YELLOW + "/combat" + ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.GRAY + "/combat start");
                p.sendMessage(ChatColor.AQUA + "This will begin combat and invite players in a 20 block radius to join.");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.GRAY + "/combat leave");
                p.sendMessage(ChatColor.AQUA + "This will leave your current combat.");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.GRAY + "/combat turn ");
                p.sendMessage(ChatColor.AQUA + "This will end your turn.");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.GRAY + "/combat list ");
                p.sendMessage(ChatColor.AQUA + "This will list all players in combaat..");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.GRAY + "/combat order ");
                p.sendMessage(ChatColor.AQUA + "This will display the turn order of combat.");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.GRAY + "/combat invite " + ChatColor.YELLOW + "(player)");
                p.sendMessage(ChatColor.AQUA + "This will invite the specified player to your combat.");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.GRAY + "/combat join " + ChatColor.YELLOW + "(player)");
                p.sendMessage(ChatColor.AQUA + "This will join the specified player's combat.");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.GRAY + "/combat decline " + ChatColor.YELLOW + "(player)");
                p.sendMessage(ChatColor.AQUA + "This will decline the specified player's combat.");
                p.sendMessage(" ");
                p.sendMessage(ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=");
                break;
            }
        }

        return true;
    }
}
