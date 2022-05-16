package me.jupdyke01.mtcore.players;

import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.cs.CharacterSheet;
import me.jupdyke01.mtcore.enums.Tag;
import me.jupdyke01.mtcore.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

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
	private Tag tag;
	private ArrayList<CharacterSheet> characters;
	private ArrayList<ChatColor> unlockedEmotes;
	private ArrayList<Player> focused = new ArrayList<>();
	private ArrayList<UUID> ignored = new ArrayList<>();

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
		this.emoteUnlocks = 0;
		this.unlockedEmotes = new ArrayList<>();
		unlockedEmotes.add(ChatColor.YELLOW);
		this.characters = charSheets;
		this.settings = new Settings();
		this.tag = Tag.NONE;
	}

	//Loading existing character
	public MortalPlayer(MTCore main, String name, UUID uuid, int activeCharId, ArrayList<CharacterSheet> characters, long nextChange, ArrayList<ChatColor> unlockedEmotes, ArrayList<UUID> ignored, int emoteUnlocks, Settings settings, Tag tag) {
		this.main = main;
		this.name = name;
		this.uuid = uuid;
		this.activeChar = activeCharId;
		this.characters = characters;
		this.nextChange = nextChange;
		this.unlockedEmotes = unlockedEmotes;
		this.ignored = ignored;
		this.emoteUnlocks = emoteUnlocks;
		this.settings = settings;
		this.tag = tag;
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

	public List<String> getIgnoredStringList() {
		return ignored.stream().map(UUID::toString).collect(Collectors.toList());
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
		if (characters != null) {
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
		}
		return id;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public void focusScoreBoard(Player p) {
		if (getFocused().size() > 0) {
			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("Focus", "dummy", ChatColor.GRAY + "" + "Focus");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			for (int i = 0; i < getFocused().size(); i++) {
				Score focus = obj.getScore(ChatColor.YELLOW + "" + getFocused().get(i).getName());
				focus.setScore(15+i);
			}
			p.setScoreboard(board);
		} else {
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
	}

	public void addIgnored(Player p) {
		ignored.add(p.getUniqueId());
	}

	public void remIgnored(Player p) {
		ignored.remove(p.getUniqueId());
	}

	public ArrayList<UUID> getIgnored() {
		return ignored;
	}

	public boolean isIgnored(Player p) {
		return ignored.contains(p.getUniqueId());
	}
	public int getTotalNonEventCharacters() {
		int nonEvent = 0;
		for (CharacterSheet character : characters) {
			if (!character.isEvent()) {
				System.out.println("Non Event Character: " + character.getName());
				nonEvent++;
			}
		}
		return nonEvent;
	}

	public int getMaxCharacterSlots(Player p) {
		int slots = 2;
		if (p.hasPermission("mortalplayer.vip"))
			slots = 3;
		return slots;
	}

	public int getAvailableSlots(Player p) {
		return getMaxCharacterSlots(p) - getTotalNonEventCharacters();
	}

	/*//Returns essentials muted boolean
	public boolean isMuted() {
		return main.getEssentials().getUser(uuid).isMuted();
	}*/
}
