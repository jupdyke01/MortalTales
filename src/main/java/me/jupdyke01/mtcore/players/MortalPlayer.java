package me.jupdyke01.mtcore.players;

import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.cs.CharacterSheet;
import me.jupdyke01.mtcore.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MortalPlayer {


	private MTCore main;
	private String name;
	private UUID uuid;
	private int activeChar;
	private long nextChange;
	private int emoteUnlocks;
	private Settings settings;

	private ArrayList<CharacterSheet> characters;
	private ArrayList<ChatColor> unlockedEmotes;
	private ArrayList<Player> focused = new ArrayList<>();

	//Making new character
	public MortalPlayer(MTCore main, String name, UUID uuid) {
		this.main = main;
		this.name = name;
		this.uuid = uuid;

		ArrayList<CharacterSheet> charSheets = new ArrayList<>();
		CharacterSheet newChar = new CharacterSheet(getFirstAvailableId());
		charSheets.add(newChar);
		this.activeChar = newChar.getId();
		this.nextChange = 0;
		this.emoteUnlocks = 1;
		this.unlockedEmotes = new ArrayList<>();
		unlockedEmotes.add(ChatColor.YELLOW);
		this.characters = charSheets;
		this.settings = new Settings();
	}

	//Loading existing character
	public MortalPlayer(MTCore main, String name, UUID uuid, int activeCharId, ArrayList<CharacterSheet> characters, long nextChange, ArrayList<ChatColor> unlockedEmotes, int emoteUnlocks, Settings settings) {
		this.main = main;
		this.name = name;
		this.uuid = uuid;
		this.activeChar = activeCharId;
		this.characters = characters;
		this.nextChange = nextChange;
		this.unlockedEmotes = unlockedEmotes;
		this.emoteUnlocks = emoteUnlocks;
		this.settings = settings;
	}

	//Getter for username
	public String getName() {
		return name;
	}

	//Setter for username
	public void setName(String name) {
		this.name = name;
	}

	//Getter for UUID
	public UUID getUuid() {
		return uuid;
	}

	//Setter for UUID
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	//Getter for character sheets
	public ArrayList<CharacterSheet> getCharacters() {
		return characters;
	}

	//Setter for character sheets
	public void setCharacters(ArrayList<CharacterSheet> characters) {
		this.characters = characters;
	}

	public CharacterSheet getActiveChar() {
		return getCharacterById(activeChar);
	}

	public CharacterSheet getOtherChar(String name) {
		for (CharacterSheet character : getCharacters()) {
			if (!character.getName().equals(name))
				return character;
		}
		return null;
	}

	public void addCharacter(CharacterSheet character) {
		characters.add(character);
	}

	public void remCharacter(String name) {
		characters.remove(getCharacter(name));
	}

	public void setActiveChar(CharacterSheet activeChar) {
		this.activeChar = activeChar.getId();
	}

	public CharacterSheet getCharacter(String name) {
		for (CharacterSheet character : getCharacters()) {
			if (character.getName().equalsIgnoreCase(name))
				return character;
		}
		return null;
	}

	public ArrayList<Player> getFocused() {
		return focused;
	}

	public boolean isFocused(Player p) {
		return focused.contains(p);
	}

	public void addFocused(Player p) {
		focused.add(p);
	}

	public void remFocused(Player p) {
		focused.remove(p);
	}

	public boolean canSeeStaff() {
		return Bukkit.getPlayer(uuid).hasPermission("mortaltales.staff") && settings.isStaffChat();
	}

	public long getNextChange() {
		return nextChange;
	}

	public void setNextChange(long nextChange) {
		this.nextChange = nextChange;
	}

	public int getEmoteUnlocks() {
		return emoteUnlocks;
	}

	public void setEmoteUnlocks(int emoteUnlocks) {
		this.emoteUnlocks = emoteUnlocks;
	}

	public ArrayList<ChatColor> getUnlockedEmotes() {
		return unlockedEmotes;
	}

	public void setUnlockedEmotes(ArrayList<ChatColor> unlockedEmotes) {
		this.unlockedEmotes = unlockedEmotes;
	}

	public boolean hasUnlockedEmote(ChatColor color) {
		return unlockedEmotes.contains(color);
	}

	public List<String> getUnlockedEmotesStringList() {
		return unlockedEmotes.stream().map(ChatColor::name).collect(Collectors.toList());
	}

	public void unlockEmote(ChatColor color) {
		unlockedEmotes.add(color);
	}

	public CharacterSheet getCharacterById(int id) {
		for (CharacterSheet character : characters)
			if (character.getId() == id)
				return character;
		return null;
	}

	public int getFirstAvailableId() {
		int id = 1;
		if (!characters.isEmpty()) {
			idLoop:
			for (int i = 1; i < 50; i++) {
				for (CharacterSheet character : characters) {
					if (i == character.getId())
						continue idLoop;
				}
				id = i;
				break;
			}
		}
		return id;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	/*//Returns essentials muted boolean
	public boolean isMuted() {
		return main.getEssentials().getUser(uuid).isMuted();
	}*/
}
