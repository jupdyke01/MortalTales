package me.jupdyke01.mtcore.tickets;

import me.jupdyke01.mtcore.enums.Status;
import org.bukkit.OfflinePlayer;

public class Ticket {

	private int ticketID;
	private String creatorUUID;
	private String information;
	private String staffUUID;
	private Status status;
	
	public Ticket(OfflinePlayer p) {
		this.ticketID = 00;
		this.creatorUUID = p.getUniqueId().toString();
		this.information = "null";
		this.staffUUID = "null";
		this.status = Status.OPEN;
	}

	public Ticket(String ticketID, String creatorUUID, String information, String staffUUID, Status status) {
		this.ticketID = Integer.valueOf(ticketID);
		this.creatorUUID = creatorUUID;
		this.information = information;
		this.staffUUID = staffUUID;
		this.status = status;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public String getCreatorUUID() {
		return creatorUUID;
	}

	public void setCreatorUUID(String creatorUUID) {
		this.creatorUUID = creatorUUID;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	public String getStaffUUID() {
		return staffUUID;
	}

	public void setStaffUUID(String staffUUID) {
		this.staffUUID = staffUUID;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
