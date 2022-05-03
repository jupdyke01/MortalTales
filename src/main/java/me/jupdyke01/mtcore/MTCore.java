package me.jupdyke01.mtcore;

import me.jupdyke01.mtcore.combat.CombatManager;
import me.jupdyke01.mtcore.commands.character.*;
import me.jupdyke01.mtcore.commands.chat.*;
import me.jupdyke01.mtcore.commands.combat.CombatCMD;
import me.jupdyke01.mtcore.commands.combat.RollCMD;
import me.jupdyke01.mtcore.commands.general.OmnomCMD;
import me.jupdyke01.mtcore.commands.general.SettingsCMD;
import me.jupdyke01.mtcore.commands.general.SitCMD;
import me.jupdyke01.mtcore.commands.general.TicketCMD;
import me.jupdyke01.mtcore.events.*;
import me.jupdyke01.mtcore.players.MortalPlayerManager;
import me.jupdyke01.mtcore.tickets.TicketManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MTCore extends JavaPlugin {
	
	//private IEssentials essentials;
	private MortalPlayerManager mpm;
	private CharsCMD chars;
	private EditCharacter editCharacter;
	private TicketManager ticketManager;
	private CombatManager combatManager;
	private MTInventories inventories;

	public void onEnable() {
		chars = new CharsCMD(this);
		editCharacter = new EditCharacter(this);
		ticketManager = new TicketManager(this);
		combatManager = new CombatManager(this);
		inventories = new MTInventories(this);
		//essentials = (IEssentials) Bukkit.getPluginManager().getPlugin("Essentials");
		mpm = new MortalPlayerManager(this);
		//mpm.loadPlayers();
		for (Player p : Bukkit.getOnlinePlayers()) {
			mpm.loadPlayer(p.getUniqueId());
			if (mpm.getPlayer(p.getUniqueId()) == null) {
				mpm.createPlayer(p.getName(), p.getUniqueId());
			}
		}
		ticketManager.loadTickets();
		initiateCommands();
		initiateEvents();
		saveDefaultConfig();
	}
	
	public void onDisable() {
		//mpm.savePlayers();
		mpm.savePlayers();
		ticketManager.saveTickets();
	}

	public void initiateCommands() {
		getCommand("chars").setExecutor(chars);
		getCommand("intro").setExecutor(new IntroductionCMD(this));
		getCommand("desc").setExecutor(new DescriptionCMD(this));
		getCommand("toggle").setExecutor(new ToggleCMD(this));
		getCommand("race").setExecutor(new RaceCMD(this));
		getCommand("name").setExecutor(new NameCMD(this));
		getCommand("age").setExecutor(new AgeCMD(this));
		getCommand("global").setExecutor(new GlobalCMD(this));
		getCommand("whisper").setExecutor(new WhisperCMD(this));
		getCommand("shout").setExecutor(new ShoutCMD(this));
		getCommand("emote").setExecutor(new EmoteCMD(this));
		getCommand("focus").setExecutor(new FocusCMD(this));
		getCommand("staff").setExecutor(new StaffCMD(this));
		getCommand("stafftoggle").setExecutor(new StaffToggleCMD(this));
		getCommand("emoteadd").setExecutor(new EmoteAddCMD(this));
		getCommand("ticket").setExecutor(new TicketCMD(this));
		getCommand("combat").setExecutor(new CombatCMD(this));
		getCommand("create").setExecutor(new CreateCMD(this));
		getCommand("wounds").setExecutor(new WoundsCMD(this));
		getCommand("settings").setExecutor(new SettingsCMD(this));
		getCommand("character").setExecutor(new CharacterCMD(this));
		getCommand("stats").setExecutor(new StatsCMD(this));
		getCommand("tag").setExecutor(new TagCMD(this));
		getCommand("ignore").setExecutor(new IgnoreCMD(this));
		getCommand("roll").setExecutor(new RollCMD());
		getCommand("omnom").setExecutor(new OmnomCMD());
		getCommand("races").setExecutor(new RacesCMD());
		getCommand("sit").setExecutor(new SitCMD());
	}
	
	public void initiateEvents() {
		getServer().getPluginManager().registerEvents(new JoinEvent(this),this);
		getServer().getPluginManager().registerEvents(new EmoteSelect(this), this);
		getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
		getServer().getPluginManager().registerEvents(new SettingsEvent(this), this);
		getServer().getPluginManager().registerEvents(new TagSelect(this), this);
		getServer().getPluginManager().registerEvents(new ExitEvent(this), this);
		getServer().getPluginManager().registerEvents(new Dismount(), this);
		getServer().getPluginManager().registerEvents(chars, this);
		getServer().getPluginManager().registerEvents(editCharacter, this);
	}
	
	/*public IEssentials getEssentials() {
		return essentials;
	}*/
	
	public MortalPlayerManager getMortalPlayerManager() {
		return mpm;
	}

	public MTInventories getInventories() {
		return inventories;
	}

	public EditCharacter getEditCharacter() {
		return editCharacter;
	}

	public CombatManager getCombatManager() {
		return combatManager;
	}

	public TicketManager getTicketManager() {
		return ticketManager;
	}
}
