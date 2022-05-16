package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.enums.Race;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateCMD implements CommandExecutor {

    private MTCore main;

    public CreateCMD(MTCore main) {
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
        if (args.length == 0) {
            if (!p.isOp()) {
                if (mp.getAvailableSlots(p) <= 0) {
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You have too many characters. Upgrade to VIP for: 3");
                    return true;
                }
            }
            main.getEditCharacter().newCharacter(p, Race.HUMAN, false);
        } else {
            if (args[0].equalsIgnoreCase("event")) {
                if (!p.hasPermission("mortalplayer.event")) {
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You are not allowed to make an event character!");
                    return true;
                }
                main.getEditCharacter().newCharacter(p, Race.HUMAN, true);
            }
        }
        return true;
    }
}

// Have circle
// Get point at radius for every 10 degrees
// Get points between two points
