package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.cs.CharacterSheet;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameCMD implements CommandExecutor {

	private MTCore main;
	
	
	public NameCMD(MTCore main) {
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
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter a name to set it to!");
			return true;
		}
		String name = "";
		
		for (String namePart : args) {
			name = name + namePart + " ";
		}
		name = name.substring(0, name.length() - 1);

		MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());

		for (CharacterSheet character : mp.getCharacters()) {
			if (character.getName().equalsIgnoreCase(name))	{
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can not have two characters of the same name!");
				return true;
			}
		}

		mp.getActiveChar().setName(name);
		p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Character name set to: " + ChatColor.YELLOW + name + ChatColor.GRAY + "!");
		return true;
	}
}
