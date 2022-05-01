package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AgeCMD implements CommandExecutor {

	private MTCore main;
	
	
	public AgeCMD(MTCore main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can not use this command from console!");
			return true;
		}
		Player p = (Player) sender;
		
		if (args.length == 0) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter an age to set it to!");
			return true;
		}
		
		if (args.length > 1) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter an age to set it to!");
		}
		
		int age = 0;
		
		try {
			age = Integer.valueOf(args[0]);
		} catch(NumberFormatException e) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Age must be set to a number!");
			return true;
		}
		if (age <= 0) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You must set it to a valid age!");
			return true;
		}


		main.getMortalPlayerManager().getPlayer(p.getUniqueId()).getActiveChar().setAge(age);
		p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Character age set to: " + ChatColor.YELLOW + age + ChatColor.GRAY + "!");
		return true;
	}
}

