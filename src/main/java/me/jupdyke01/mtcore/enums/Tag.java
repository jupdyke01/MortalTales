package me.jupdyke01.mtcore.enums;

import net.md_5.bungee.api.ChatColor;

public enum Tag {

	NONE(""),
	PEASENT(" &7[&ePeasent&7]&r"),
	PLEBIAN(" &7[&ePlebian&7]&r"),
	SERF(" &7[&eSerf&7]&r"),
	DRUNKARD(" &7[&eDrunkard&7]&r"),
	SWINDLER(" &7[&eSwindler&7]&r"),
	MERCENARY(" &7[&d&oMercenary&7]&r"),
	VOYAGER(" &7[&d&oVoyager&7]&r"),
	PRIEST(" &7[&d&oPriest&7]&r"),
	SQUIRE(" &7[&d&oSquire&7]&r"),
	THIEF(" &7[&d&oThief&7]&r"),
	PALADIN(" &7[&9&lPaladin&7]&r"),
	HERO(" &7[&9&lHero&7]&r"),
	KNIGHT(" &7[&9&lKnight&7]&r"),
	CHAMPION(" &7[&9&lChampion&7]&r"),
	BANDIT(" &7[&9&lBandit&7]&r"),
	LORD(" &7[&4&lLord&7]&r"),
	LADY(" &7[&4&lLady&7]&r"),
	LEGEND(" &7[&4&lLegend&7]&r"),
	WAR_CHIEF(" &7[&4&lWar Chief&7]&r"),
	TYRANT(" &7[&4&lTyrant&7]&r");

	private final String suffix;
	
	Tag(String suffix) {
		this.suffix = suffix;
	}
	
	public String getSuffix() {
		return ChatColor.translateAlternateColorCodes('&',suffix);
	}

	public static Tag getTagBySuffix(String suffix) {
		for (Tag tag : values()) {
			if (ChatColor.translateAlternateColorCodes('&', tag.getSuffix()).contains(suffix))
				return tag;
		}
		return null;
	}

	public String getPermission() {
		return "mortaltales.tag." + name().toLowerCase();
	}
	
}
