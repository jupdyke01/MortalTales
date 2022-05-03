package me.jupdyke01.mtcore.enums;

import org.bukkit.Material;

public enum Race {

	DRAGONBORN("Dragonborn", "Dragon like outcasts with great ambition.", Material.DRAGON_HEAD, 3, 1, 0),
	DWARF("Dwarf", "Stout, stubborn, and strong-willed unifiers.", Material.IRON_PICKAXE, 2, 2, 0),
	HUMAN("Human", "The race of two creators.", Material.APPLE, 2, 1, 0),
	WOOD_ELF("Wood Elf", "Harmonious and watchful guardians of the forests.", Material.KELP, 3, 0, 0),
	DARK_ELF("Dark Elf", "Dwellers of the deep caverns.", Material.ENDERMAN_SPAWN_EGG, 4, 0, 0),
	SNOW_ELF("Snow Elf", "Hardy and isolated people of the far north.", Material.SNOWBALL, 4, 0, 0);
	
	Race(String name, String desc, Material display, int defaultMortalWounds, int defaultRange, int defaultMagicLevel) {
		this.name = name;
		this.desc = desc;
		this.display = display;
		this.defaultMortalWounds = defaultMortalWounds;
		this.defaultMagicLevel = defaultMagicLevel;
		this.defaultRange = defaultRange;
	}
	
	private final String name;
	private final String desc;
	private final Material display;
	private final int defaultMortalWounds;
	private final int defaultRange;
	private final int defaultMagicLevel;


	public static boolean raceExists(String str) {
		for (Race race : Race.values()) {
			if (race.toString().equalsIgnoreCase(str) || race.name.equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public Material getDisplay() {
		return display;
	}
	public int getDefaultMortalWounds() {
		return defaultMortalWounds;
	}
	public int getDefaultRange() {
		return defaultRange;
	}
	public int getDefaultMagicLevel() {
		return defaultMagicLevel;
	}
}
