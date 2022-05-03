package me.jupdyke01.mtcore.commands.general;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SitCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "You must be a player to use this command!");
			return true;
		}

		Player p = (Player) sender;
		if (p.isOnGround()) {
			ArmorStand stand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation().subtract(0, 1.7, 0), EntityType.ARMOR_STAND);
			stand.setInvulnerable(true);
			stand.setInvisible(true);
			stand.setSilent(true);
			stand.setGravity(false);
			stand.setCustomName("Armor Seat: " + p.getName());
			stand.addPassenger(p);

			return true;
		}
		return true;
	}
}
