package me.jupdyke01.mtcore.tickets;

import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.enums.Status;

import java.util.ArrayList;

public class TicketManager {

	MTCore main;
	public String highestID = "0";
	private ArrayList<Ticket> tickets = new ArrayList<>();

	public TicketManager(MTCore main) {
		this.main = main;
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

	public Ticket getTicket(int ticketID) {
		for (Ticket ticket : tickets) {
			if (ticket.getTicketID() == ticketID)
				return ticket;
		}
		return null;
	}

	public int ticketNumber() {
		return Integer.valueOf(highestID) + 1;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void saveTickets() {
		for (Ticket ticket : tickets) {
			main.getConfig().set("tickets." + ticket.getTicketID() + ".creator", ticket.getCreatorUUID());
			main.getConfig().set("tickets." + ticket.getTicketID() + ".information", ticket.getInformation());
			main.getConfig().set("tickets." + ticket.getTicketID() + ".staff", ticket.getStaffUUID());
			main.getConfig().set("tickets." + ticket.getTicketID() + ".status", ticket.getStatus().toString());
		}
		main.saveConfig();
	}

	public void loadTickets() {
		String highestID = "0";
		if (main.getConfig().contains("tickets")) {
			for (String str : main.getConfig().getConfigurationSection("tickets").getKeys(false)) {
				String creatorUUID = main.getConfig().getString("tickets." + str + ".creator");
				String information = main.getConfig().getString("tickets." + str + ".information");
				String staffUUID = main.getConfig().getString("tickets." + str + ".staff");
				Status status = Status.valueOf(main.getConfig().getString("tickets." + str + ".status"));
				if (status.equals(Status.OPEN))
					addTicket(new Ticket(str, creatorUUID, information, staffUUID, status));
				highestID = str;
				System.out.println(highestID);
			}
		}
		this.highestID = highestID;
	}
}
