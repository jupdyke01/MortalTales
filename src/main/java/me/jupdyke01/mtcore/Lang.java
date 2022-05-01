package me.jupdyke01.mtcore;

import net.md_5.bungee.api.ChatColor;

public enum Lang {

	PREFIX(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[" + ChatColor.AQUA + "MT" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET);
	
	private String lang;
	
	Lang(String lang) {
		this.lang = lang;
	}
	
	public String getLang() {
		return lang;
	}
}
