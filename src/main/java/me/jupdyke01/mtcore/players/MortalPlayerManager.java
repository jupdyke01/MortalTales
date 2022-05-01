package me.jupdyke01.mtcore.players;

import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.cs.CharacterSheet;
import me.jupdyke01.mtcore.enums.Race;
import me.jupdyke01.mtcore.settings.Settings;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class MortalPlayerManager {

	private MTCore main;

	private ArrayList<MortalPlayer> players = new ArrayList<>();

	public MortalPlayerManager(MTCore main) {
		this.main = main;
	}

	public void loadPlayers() {
		File playerFiles = new File(main.getDataFolder(), "/players");
		if (!playerFiles.exists())
			playerFiles.mkdir();
		if (playerFiles.listFiles() == null)
			return;
		for (File playerFile : playerFiles.listFiles()) {
			FileConfiguration player = YamlConfiguration.loadConfiguration(playerFile);
			
			String username = player.getString("name");
			String stringUuid = player.getString("uuid");
			long nextChange = player.getLong("nextChange");
			int activeCharId = player.getInt("activeChar");
			int emoteUnlocks = player.getInt("emoteUnlocks");
			ArrayList<ChatColor> unlockedEmotes = player.getStringList("unlockedEmotes").stream().map(ChatColor::valueOf).collect(Collectors.toCollection(ArrayList::new));

			ArrayList<CharacterSheet> characters = new ArrayList<>();

			for (String i : player.getConfigurationSection("character").getKeys(false)) {
				int cid = player.getInt("character." + i + ".id");
				String cname = player.getString("character." + i + ".name");
				String description = player.getString("character." + i + ".description");
				String introduction = player.getString("character." + i + ".introduction");
				int age = player.getInt("character." + i + ".age");
				String stringRace = player.getString("character." + i + ".race");
				Race race = Race.valueOf(stringRace.toUpperCase());
				Boolean event = player.getBoolean("character." + i + ".event");
				int currentMortalWounds = player.getInt("character." + i + ".currentMortalWounds");
				int mortalWoundsAdd = player.getInt("character." + i + ".mortalWoundsAdd");
				int magicLevelAdd = player.getInt("character." + i + ".magicLevelAdd");
				int rangeAdd = player.getInt("character." + i + ".rangeAdd");


				CharacterSheet loadChar = new CharacterSheet(cid, cname, description, introduction, age, race, event, currentMortalWounds, mortalWoundsAdd, magicLevelAdd, rangeAdd);
				characters.add(loadChar);
			}
			
			UUID uuid = UUID.fromString(stringUuid);


			String stringEmote = player.getString("emote");

			boolean seeGlobal = player.getBoolean("seeGlobal");
			boolean seeStaff = player.getBoolean("seeStaff");
			boolean textDisplay = player.getBoolean("textDisplay");
			ChatColor emote = ChatColor.valueOf(stringEmote);
			Settings settings = new Settings(seeGlobal, seeStaff, textDisplay, emote);

			players.add(new MortalPlayer(main, username, uuid, activeCharId, characters, nextChange, unlockedEmotes, emoteUnlocks, settings));
		}
	}

	public void savePlayers() {
		if (!main.getDataFolder().exists()) {
			main.getDataFolder().mkdir();
		}

		for (MortalPlayer player : players) {
			File playerFile = new File(main.getDataFolder(), "/players/" + player.getUuid().toString() + ".yml");
			if (!playerFile.exists()) {
				playerFile.getParentFile().mkdirs();
			}
			FileConfiguration playerConfig = new YamlConfiguration();
			try {
				playerConfig.load(playerFile);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			
			playerConfig.set("name", player.getName());
			playerConfig.set("uuid", player.getUuid().toString());
			playerConfig.set("activeChar", player.getActiveChar().getId());
			playerConfig.set("nextChange", player.getNextChange());
			playerConfig.set("emoteUnlocks", player.getEmoteUnlocks());
			playerConfig.set("unlockedEmotes", player.getUnlockedEmotesStringList());
			playerConfig.set("character", null);

			playerConfig.set("emote", player.getSettings().getEmoteColor().name());
			playerConfig.set("seeGlobal", player.getSettings().isGlobalChat());
			playerConfig.set("seeStaff", player.getSettings().isStaffChat());
			playerConfig.set("textDisplay", player.getSettings().isTextDisplay());

			int i = 1;
			for (CharacterSheet sheet : player.getCharacters()) {
				playerConfig.set("character." + i + ".id", sheet.getId());
				playerConfig.set("character." + i + ".name", sheet.getName());
				playerConfig.set("character." + i + ".description", sheet.getDescription());
				playerConfig.set("character." + i + ".introduction", sheet.getIntroduction());
				playerConfig.set("character." + i + ".age", sheet.getAge());
				playerConfig.set("character." + i + ".race", sheet.getRace().name());
				playerConfig.set("character." + i + ".event", sheet.isEvent());
				playerConfig.set("character." + i + ".currentMortalWounds", sheet.getCurrentMortalWounds());
				playerConfig.set("character." + i + ".mortalWoundsAdd", sheet.getMortalWoundsAdd());
				playerConfig.set("character." + i + ".magicLevelAdd", sheet.getMagicLevelAdd());
				playerConfig.set("character." + i + ".rangeAdd", sheet.getRangeAdd());
				i++;
			}
			try {
				playerConfig.save(playerFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public MortalPlayer getPlayer(UUID uuid) {
		for (MortalPlayer player : players) {
			if (player.getUuid().equals(uuid)) {
				return player;
			}
		}
		return null;
	}

	public MortalPlayer getPlayer(String username) {
		for (MortalPlayer player : players) {
			if (player.getName().equals(username)) {
				return player;
			}
		}
		return null;
	}

	public void createPlayer(String username, UUID uuid) {
		players.add(new MortalPlayer(main, username, uuid));
	}
}
