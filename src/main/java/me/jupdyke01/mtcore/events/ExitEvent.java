package me.jupdyke01.mtcore.events;

import me.jupdyke01.mtcore.MTCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ExitEvent implements Listener {

    MTCore main;

    public ExitEvent(MTCore main) {
        this.main = main;
    }

    @EventHandler
    public void onExit(PlayerQuitEvent e) {
        if (main.getCombatManager().getPlayerCombat(e.getPlayer()) == null)
            return;
        main.getCombatManager().getPlayerCombat(e.getPlayer()).removeParticipant(e.getPlayer());
        main.getMortalPlayerManager().savePlayer(main.getMortalPlayerManager().getPlayer(e.getPlayer().getUniqueId()));
    }
}
