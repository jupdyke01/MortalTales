package me.jupdyke01.mtcore.commands.general;

import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.enums.Status;
import me.jupdyke01.mtcore.tickets.Ticket;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TicketCMD implements CommandExecutor {

	MTCore main;

	public TicketCMD(MTCore main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must be a player to use this command!");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Please use /ticket create <description>!");
			return true;
		}

		if (args[0].equalsIgnoreCase("create")) {
			if (args.length < 3) {
				p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must enter a valid description!");
				return true;
			}
			String description = "";
			for (int i = 1; i < args.length; i++) {
				description = description + args[i] + " ";
			}
			p.sendMessage(ChatColor.GRAY + "Ticket has been created with ticket ID: " + main.getTicketManager().ticketNumber());
			main.getTicketManager().addTicket(new Ticket(String.valueOf(main.getTicketManager().ticketNumber()), p.getUniqueId().toString(), description, "null", Status.OPEN));
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.hasPermission("mortalplayer.staff")) {
					player.sendMessage(ChatColor.GRAY + "--------New Ticket----------");
					player.sendMessage(ChatColor.DARK_GRAY + "ID: " + ChatColor.AQUA + ChatColor.BOLD + (main.getTicketManager().ticketNumber()));
					player.sendMessage(ChatColor.DARK_GRAY + "Creator: " + ChatColor.AQUA + ChatColor.BOLD + p.getName());
					player.sendMessage(ChatColor.DARK_GRAY + "Description: " + ChatColor.AQUA + ChatColor.BOLD + description);
					player.sendMessage(ChatColor.DARK_GRAY + "Claimed: " + ChatColor.RED + "" + ChatColor.BOLD + "NO");
					player.sendMessage(ChatColor.GRAY + "--------------------------");
					//player.sendMessage(" ");

					TextComponent start = new TextComponent(" ");

					TextComponent close = new TextComponent(ChatColor.DARK_GRAY + "    [" + ChatColor.RED + "" + ChatColor.BOLD + "CLOSE" + ChatColor.DARK_GRAY + "]");
					close.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.DARK_GRAY + "Click to close this ticket!").create()));
					close.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ticket close " + (main.getTicketManager().ticketNumber())));


					TextComponent claim = new TextComponent(ChatColor.DARK_GRAY + "        [" + ChatColor.GREEN + "" + ChatColor.BOLD + "CLAIM" + ChatColor.DARK_GRAY + "]");
					claim.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.DARK_GRAY + "Click to claim this ticket!").create()));
					claim.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ticket claim " + (main.getTicketManager().ticketNumber())));

					start.addExtra(close);
					start.addExtra(claim);
					player.spigot().sendMessage(start);


				}
			}
			main.getTicketManager().highestID = String.valueOf(Integer.valueOf(main.getTicketManager().highestID) + 1);
		} else if (args[0].equalsIgnoreCase("list")) {
			if (p.hasPermission("mortalplayer.staff")) {
				for (Ticket ticket : main.getTicketManager().getTickets()) {
					if (ticket.getStatus().equals(Status.OPEN)) {
						p.sendMessage(ChatColor.GRAY + "-----------------------------");
						p.sendMessage(ChatColor.DARK_GRAY + "ID: " + ChatColor.AQUA + ChatColor.BOLD + ticket.getTicketID());
						p.sendMessage(ChatColor.DARK_GRAY + "Creator: " + ChatColor.AQUA + ChatColor.BOLD + Bukkit.getOfflinePlayer(UUID.fromString(ticket.getCreatorUUID())).getName());
						p.sendMessage(ChatColor.DARK_GRAY + "Description: " + ChatColor.AQUA + ChatColor.BOLD + ticket.getInformation());
						if (ticket.getStaffUUID().equals("null")) {
							p.sendMessage(ChatColor.DARK_GRAY + "Claimed: " + ChatColor.RED + "" + ChatColor.BOLD + "NO");
						} else {
							p.sendMessage(ChatColor.DARK_GRAY + "Claimed: " + ChatColor.GREEN + "" + ChatColor.BOLD + "YES - " + Bukkit.getOfflinePlayer(UUID.fromString(ticket.getStaffUUID())).getName());
						}
						p.sendMessage(ChatColor.GRAY + "-----------------------------");
					}
				}
			}
		} else if (args[0].equalsIgnoreCase("close")) {
			if (p.hasPermission("mortalplayer.staff")) {
				if (args.length < 2) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must enter a ticket #");
					return true;
				}
				String ticketID;
				try {
					ticketID = args[1];
				} catch(NumberFormatException e) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must enter a ticket #");
					return true;
				}
				Ticket ticket = main.getTicketManager().getTicket(Integer.valueOf(ticketID));
				if (ticket == null) {
					p.sendMessage(ChatColor.RED + "A ticket does not exist with that ID!");
					return true;
				}
				ticket.setStatus(Status.CLOSED);
				p.sendMessage(ChatColor.GRAY + "Ticket with ID: " + ticketID + " successfuly closed!");
				Player t = Bukkit.getPlayer(UUID.fromString(ticket.getCreatorUUID()));
				if (t != null) {
					t.sendMessage(ChatColor.GRAY + p.getName() + " has closed your ticket with ID #" + ticket.getTicketID());
				}
			}
		} else if (args[0].equalsIgnoreCase("show")) {
			if (p.hasPermission("mortalplayer.staff")) {
				if (args.length < 2) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must enter a ticket #");
					return true;
				}
				String ticketID;
				try {
					ticketID = args[1];
				} catch(NumberFormatException e) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must enter a ticket #");
					return true;
				}
				Ticket ticket = main.getTicketManager().getTicket(Integer.valueOf(ticketID));
				if (ticket == null) {
					p.sendMessage(ChatColor.RED + "A ticket does not exist with that ID!");
					return true;
				}
				p.sendMessage(ChatColor.GRAY + "-----------------------------");
				p.sendMessage(ChatColor.DARK_GRAY + "ID: " + ChatColor.AQUA + ChatColor.BOLD + ticket.getTicketID());
				p.sendMessage(ChatColor.DARK_GRAY + "Creator: " + ChatColor.AQUA + ChatColor.BOLD + Bukkit.getOfflinePlayer(UUID.fromString(ticket.getCreatorUUID())).getName());
				p.sendMessage(ChatColor.DARK_GRAY + "Description: " + ChatColor.AQUA + ChatColor.BOLD + ticket.getInformation());
				if (ticket.getStaffUUID().equals("null")) {
					p.sendMessage(ChatColor.DARK_GRAY + "Claimed: " + ChatColor.RED + "" + ChatColor.BOLD + "NO");
				} else {
					p.sendMessage(ChatColor.DARK_GRAY + "Claimed: " + ChatColor.GREEN + "" + ChatColor.BOLD + "YES - " + Bukkit.getOfflinePlayer(UUID.fromString(ticket.getStaffUUID())).getName());
				}
				p.sendMessage(ChatColor.GRAY + "-----------------------------");
			}
		} else if (args[0].equalsIgnoreCase("claim")) {
			if (p.hasPermission("mortalplayer.staff")) {
				if (args.length < 2) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must enter a ticket #");
					return true;
				}
				String ticketID;
				try {
					ticketID = args[1];
				} catch(NumberFormatException e) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must enter a ticket #");
					return true;
				}
				Ticket ticket = main.getTicketManager().getTicket(Integer.valueOf(ticketID));
				if (ticket == null) {
					p.sendMessage(ChatColor.RED + "A ticket does not exist with that ID!");
					return true;
				}
				if (ticket.getStaffUUID().equals("null")) {
					ticket.setStaffUUID(p.getUniqueId().toString());
					for (Player online : Bukkit.getOnlinePlayers()) {
						if (online != p)
							if (online.hasPermission("mortalplayer.staff"))
								online.sendMessage(ChatColor.GRAY + p.getName() + " has claimed ticket #" + ticket.getTicketID());
					}
					Player t = Bukkit.getPlayer(UUID.fromString(ticket.getCreatorUUID()));
					if (t != null) {
						t.sendMessage(ChatColor.GRAY + p.getName() + " has claimed your ticket with ID #" + ticket.getTicketID());
					}

					p.sendMessage(ChatColor.GRAY + "You have now claimed ticket #" + ticket.getTicketID());
					return true;
				} else {
					p.sendMessage(ChatColor.RED + "That ticket has already been claimed!");
					return true;
				}
			}
		} else if (args[0].equalsIgnoreCase("finish")) {
			if (p.hasPermission("mortalplayer.staff")) {
				if (args.length < 2) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must enter a ticket #");
					return true;
				}
				String ticketID;
				try {
					ticketID = args[1];
				} catch(NumberFormatException e) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must enter a ticket #");
					return true;
				}
				Ticket ticket = main.getTicketManager().getTicket(Integer.valueOf(ticketID));
				if (ticket == null) {
					p.sendMessage(ChatColor.RED + "A ticket does not exist with that ID!");
					return true;
				}
				if (ticket.getStatus().equals(Status.OPEN))
					ticket.setStatus(Status.COMPLETED);
				p.sendMessage(ChatColor.GRAY + "Ticket successfully completed.");
				Player t = Bukkit.getPlayer(UUID.fromString(ticket.getCreatorUUID()));
				if (t != null) {
					t.sendMessage(ChatColor.GRAY + p.getName() + " has completed your ticket with ID #" + ticket.getTicketID());
				}
			}
		}

		return true;
	}

}
