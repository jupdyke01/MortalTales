package me.jupdyke01.mtcore.events;

import me.jupdyke01.mtcore.MTCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

	private MTCore main;
	
	public JoinEvent(MTCore main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		main.getMortalPlayerManager().loadPlayer(e.getPlayer().getUniqueId());
		if (main.getMortalPlayerManager().getPlayer(e.getPlayer().getUniqueId()) == null) {
			main.getMortalPlayerManager().createPlayer(e.getPlayer().getName(), e.getPlayer().getUniqueId());
		}
	}
}
