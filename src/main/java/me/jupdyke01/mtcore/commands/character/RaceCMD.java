package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.enums.Race;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RaceCMD implements CommandExecutor {

	private MTCore main;


	public RaceCMD(MTCore main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can not use this command from console!");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("mortalplayer.staff")) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please contact staff to do this for you!");
			return true;
		}

		if (args.length == 0) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter a user, and the race to set them to!");
			return true;
		}
		if (args.length == 1) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter a race to set the user to!");
			return true;
		}

		String tString = args[0];
		Player target = main.getServer().getPlayer(args[0]);
		if (target == null) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.YELLOW + tString + ChatColor.RED + " is not a valid target!");
			return true;
		}

		String race = "";

		for (String racePart : args) {
			if (!racePart.equals(tString))
				race = race + StringUtils.capitalize(racePart) + " ";
		}

		race = race.substring(0, race.length() - 1);
		race = race.replace(" ", "_");
		if (!Race.raceExists(race)) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.LIGHT_PURPLE + race + ChatColor.RED + " is not a race, Please use /races to list them!");
			return true;
		}
		Race r = Race.valueOf(race);

		main.getMortalPlayerManager().getPlayer(p.getUniqueId()).getActiveChar().setRace(r);
		p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Character race set to: " + ChatColor.YELLOW + race + ChatColor.GRAY + "!");
		return true;
	}
}
