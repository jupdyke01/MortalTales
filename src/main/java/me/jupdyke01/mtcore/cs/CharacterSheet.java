package me.jupdyke01.mtcore.cs;

import me.jupdyke01.mtcore.enums.Race;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class CharacterSheet {

	private int id;
	private String name;
	private String description;
	private String introduction;
	private int age;
	private Race race;
	private boolean event;
	int currentMortalWounds;
	int mortalWoundsAdd;
	int magicLevelAdd;
	int rangeAdd;

	public CharacterSheet(int id) {
		this.id = id;
		name = "JonJane Doeman" + id;
		description = "This is a default character sheet";
		introduction = "This is a default character sheet";
		age = 18;
		race = Race.HUMAN;
		currentMortalWounds = race.getDefaultMortalWounds();
		mortalWoundsAdd = 0;
		magicLevelAdd = 0;
		rangeAdd = 0;
	}

	public CharacterSheet(int id, String name, String description, String introduction, int age, Race race, boolean event, int currentMortalWounds, int mortalWoundsAdd, int magicLevelAdd, int rangeAdd) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.introduction = introduction;
		this.age = age;
		this.race = race;
		this.event = event;
		this.currentMortalWounds = currentMortalWounds;
		this.mortalWoundsAdd = mortalWoundsAdd;
		this.magicLevelAdd = magicLevelAdd;
		this.rangeAdd = rangeAdd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public boolean isEvent() {
		return event;
	}

	public void setEvent(boolean event) {
		this.event = event;
	}

	public int getCurrentMortalWounds() {
		return currentMortalWounds;
	}

	public void setCurrentMortalWounds(int currentMortalWounds) {
		this.currentMortalWounds = currentMortalWounds;
	}

	public int getMortalWoundsAdd() {
		return mortalWoundsAdd;
	}

	public void setMortalWoundsAdd(int mortalWoundsAdd) {
		this.mortalWoundsAdd = mortalWoundsAdd;
	}

	public int getMagicLevelAdd() {
		return magicLevelAdd;
	}

	public void setMagicLevelAdd(int magicLevelAdd) {
		this.magicLevelAdd = magicLevelAdd;
	}

	public int getRangeAdd() {
		return rangeAdd;
	}

	public void setRangeAdd(int rangeAdd) {
		this.rangeAdd = rangeAdd;
	}

	public int getFinalMortalWounds() {
		return race.getDefaultMortalWounds() + mortalWoundsAdd;
	}

	public int getFinalMagicLevel() {
		return race.getDefaultMagicLevel() + magicLevelAdd;
	}

	public int getFinalRange() {
		return race.getDefaultRange() + rangeAdd;
	}

	public ItemStack getCharacterItem(OfflinePlayer p) {
		ItemStack charItem = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta charItemMeta = (SkullMeta) charItem.getItemMeta();
		charItemMeta.setOwningPlayer(p);
		charItemMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Name: " + ChatColor.YELLOW + "" + ChatColor.BOLD + name);
		ArrayList<String> charItemLore = new ArrayList<>();
		charItemLore.add(ChatColor.GRAY + "Race: " + ChatColor.YELLOW + race.getName());
		charItemLore.add(" ");
		charItemLore.add(ChatColor.GRAY + "Age: " + ChatColor.YELLOW + age);
		charItemLore.add(" ");
		charItemLore.add(ChatColor.GRAY + "Intro: " + ChatColor.YELLOW + introduction);
		charItemLore.add(" ");
		charItemLore.add(ChatColor.GRAY + "Desc: " + ChatColor.YELLOW + description);
		charItemLore.add(" ");
		charItemLore.add(ChatColor.GRAY + "Mortal Wounds: " + ChatColor.YELLOW + currentMortalWounds + "/" + getFinalMortalWounds());
		charItemLore.add(ChatColor.GRAY + "Magic Level: " + ChatColor.YELLOW + getFinalMagicLevel());
		charItemLore.add(ChatColor.GRAY + "Range: " + ChatColor.YELLOW + getFinalRange());
		charItemMeta.setLore(charItemLore);
		charItem.setItemMeta(charItemMeta);
		return charItem;
	}

	public List<String> getCharacterText() {
		List<String> text = new ArrayList<>();
		text.add(ChatColor.GRAY + "" + ChatColor.BOLD + "Name: " + ChatColor.YELLOW + "" + ChatColor.BOLD + name);
		text.add(ChatColor.GRAY + "Race: " + ChatColor.YELLOW + race.getName());
		text.add(" ");
		text.add(ChatColor.GRAY + "Age: " + ChatColor.YELLOW + age);
		text.add(" ");
		text.add(ChatColor.GRAY + "Intro: " + ChatColor.YELLOW + introduction);
		text.add(" ");
		text.add(ChatColor.GRAY + "Desc: " + ChatColor.YELLOW + description);
		text.add(" ");
		text.add(ChatColor.GRAY + "Mortal Wounds: " + ChatColor.YELLOW + currentMortalWounds + "/" + getFinalMortalWounds());
		text.add(ChatColor.GRAY + "Magic Level: " + ChatColor.YELLOW + getFinalMagicLevel());
		text.add(ChatColor.GRAY + "Range: " + ChatColor.YELLOW + getFinalRange());
		return text;
	}

}
