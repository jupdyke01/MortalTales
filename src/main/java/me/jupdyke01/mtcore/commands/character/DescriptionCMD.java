package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DescriptionCMD implements CommandExecutor {

	private MTCore main;
	
	
	public DescriptionCMD(MTCore main) {
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
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter a description to set it to!");
			return true;
		}
		String desc = "";
		
		for (String descPart : args) {
			desc = desc + descPart + " ";
		}
		desc = desc.substring(0, desc.length() - 1);
		
		main.getMortalPlayerManager().getPlayer(p.getUniqueId()).getActiveChar().setDescription(desc);
		p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Desc: " + ChatColor.YELLOW + desc + ChatColor.GRAY + "!");
		return true;
	}
}