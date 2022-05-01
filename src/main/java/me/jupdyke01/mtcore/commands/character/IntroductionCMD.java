package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IntroductionCMD implements CommandExecutor {

	private MTCore main;
	
	
	public IntroductionCMD(MTCore main) {
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
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter a introduction link to set it to!");
			return true;
		}
		if (args.length > 1) {
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter a link to your introduction!");
		}
		
		String introduction = args[0];
		
		main.getMortalPlayerManager().getPlayer(p.getUniqueId()).getActiveChar().setIntroduction(introduction);
		p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Character introduction link set to: " + ChatColor.YELLOW + introduction + ChatColor.GRAY + "!");
		return true;
	}
}