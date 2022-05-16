package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.cs.CharacterSheet;
import me.jupdyke01.mtcore.enums.Race;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CharsCMD implements CommandExecutor, Listener{

	private MTCore main;

	public CharsCMD(MTCore main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		ArrayList<String> cmdArgs = new ArrayList<>();

		int i = 0;
		for (String arg : args) {
			if (i != 0) {
				cmdArgs.add(arg);
			}
			i++;
		}


		if (!(sender instanceof Player)) {
			sender.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can not use this command from console!");
			return true;
		}
		Player p = (Player) sender;
		MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());

		if (args.length > 0 && args[0].equals("help")) {
			p.sendMessage(ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=" + ChatColor.YELLOW + "/chars" + ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=");
			p.sendMessage(" ");
			p.sendMessage(ChatColor.GRAY + "/chars create " + ChatColor.YELLOW + "(race) [event]");
			p.sendMessage(ChatColor.AQUA + "This will create a character with the specified race.");
			p.sendMessage(" ");
			p.sendMessage(ChatColor.GRAY + "/chars active " + ChatColor.YELLOW + "(character name)");
			p.sendMessage(ChatColor.AQUA + "This will make the specified character your active character.");
			p.sendMessage(" ");
			p.sendMessage(ChatColor.GRAY + "/chars delete " + ChatColor.YELLOW + "(character name)");
			p.sendMessage(ChatColor.AQUA + "This will delete the specified character.");
			p.sendMessage(" ");
			p.sendMessage(ChatColor.GRAY + "=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=-==-=-=-=-=-=");
		} else if (args.length > 0 && args[0].equals("create")) {
			boolean isVip = p.hasPermission("mortalplayer.vip");
			boolean isEventChar = false;
			if (cmdArgs.isEmpty()) {
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please specify a character race! Usage: /chars create {race}, or use /create to access the GUI creator.");
				return true;
			}
			if (p.hasPermission("mortalplayer.event")) {
				if (cmdArgs.get(cmdArgs.size() - 1).equals("event")) {
					isEventChar = true;
				}
			}

			if (!isEventChar) {
				if (mp.getAvailableSlots(p) <= 0) {
					p.sendMessage(Lang.PREFIX.getLang() + net.md_5.bungee.api.ChatColor.RED + "You have too many characters. Upgrade to VIP for: 3");
					return true;
				}
			}

			String race = "";

			for (String racePart : cmdArgs) {
				if (!racePart.equals("event"))
					race = race + StringUtils.capitalize(racePart) + " ";
			}

			race = race.substring(0, race.length() - 1);
			race = race.replace(" ", "_");

			if (!Race.raceExists(race)) {
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GOLD + race + ChatColor.RED + " is not a race, Please use /races to list them!");
				return true;
			}
			Race r = Race.valueOf(race.toUpperCase());

			CharacterSheet newChar = new CharacterSheet(mp.getFirstAvailableId());
			newChar.setRace(r);
			newChar.setCurrentMortalWounds(r.getDefaultMortalWounds());
			newChar.setName("NewChar" + (mp.getCharacters().size() + 1));
			newChar.setEvent(isEventChar);
			mp.addCharacter(newChar);
			p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "New character has been created!");
			return true;
		} else if (args.length > 0 && args[0].equals("active")) {
			if (cmdArgs.size() == 0) {
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter a character name to change to your active character!");
				return true;
			}
			if (mp.getNextChange() > 0 && System.currentTimeMillis() < mp.getNextChange() && !p.hasPermission("mortalplayer.event")) {
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can't do this yet. You must wait 7 days between character changes.");
				long seconds = (mp.getNextChange() - System.currentTimeMillis()) / 1000;
				long minutes = seconds / 60;
				long hours = minutes / 60;
				long days = hours / 24;
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Time Remaining: " + ChatColor.YELLOW + days + " days, " + hours % 24 + " hours and " + minutes % 60 + " minutes.");
				return true;
			}

			String name = "";

			for (String namePart : cmdArgs) {
				name = name + namePart + " ";
			}
			name = name.substring(0, name.length() - 1);
			if (mp.getCharacter(name) != null) {
				if (!mp.getActiveChar().getName().equals(name)) {
					mp.setActiveChar(mp.getCharacter(name));
					mp.setNextChange(System.currentTimeMillis() + 604800000);
					p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully set " + ChatColor.YELLOW + name + ChatColor.GRAY + " as your active character!");
				} else {
					p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "This is already your active character!");
				}
			} else {
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You do not have a character by that name!");
			}
			return true;
		} else if (args.length > 0 && args[0].equals("delete")) {
			if (cmdArgs.size() == 0) {
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "Please enter a character name you would like to delete!");
				return true;
			}
			String name = "";

			for (String namePart : cmdArgs) {
				name = name + namePart + " ";
			}
			name = name.substring(0, name.length() - 1);
			if (mp.getCharacters().size() == 1) {
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can not delete your only character! Please create a new one with /char create");
				return true;
			}
			if (mp.getCharacter(name) != null) {
				if (mp.getActiveChar().getName().equals(name)) {
					mp.setActiveChar(mp.getOtherChar(name));
				}
				mp.remCharacter(name);
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Successfully deleted character: " + ChatColor.YELLOW + name + ChatColor.GRAY + "!");
			} else {
				p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You do not have a character by that name!");
			}
			return true;
		} else {
			OfflinePlayer target = null;
			if (args.length > 0) {
				String t = args[0];
				for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
					if (op.getName().equals(t)) {
						target = op;
						break;
					}
				}
			}
			if (target == null) {
				target = p;
			}
			if (p.hasPermission("mortalplayer.staff")) {
				p.openInventory(main.getInventories().getCharsInventory(target));
			} else {
				if (target == p) {
					p.openInventory(main.getInventories().getCharsInventory(target));
				} else {
					p.openInventory(main.getInventories().getCharInventory(target));
				}
			}
		}
		return true;
	}

	@EventHandler
	public void charsInventoryHandler(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player p) {
			if (e.getCurrentItem() != null) {
				ItemStack item = e.getCurrentItem();
				if (e.getView().getTitle().contains(ChatColor.DARK_GRAY + "Characters")) {
					e.setCancelled(true);
					if (!item.hasItemMeta())
						return;
					if (!item.getItemMeta().hasDisplayName())
						return;
					if (!item.getItemMeta().getDisplayName().contains("Name: ")) {
						return;
					}
					String charName = ChatColor.stripColor(item.getItemMeta().getDisplayName().split("Name: ")[1]);
					CharacterSheet character = main.getMortalPlayerManager().getPlayer(p.getUniqueId()).getCharacter(charName);
					if (character == null)
						return;
					main.getEditCharacter().editCharacter(p, character);
				}
			}
		}
	}
}
