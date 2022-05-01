package me.jupdyke01.mtcore.events;

import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    private MTCore main;

    public ChatEvent(MTCore main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player p = e.getPlayer();
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (p.getLocation().distance(target.getLocation()) <= 20) {
                String formatMessage = e.getMessage();
                formatMessage = formatMessage.replaceAll("\\*", mp.getSettings().getEmoteColor() + "");
                formatMessage = formatMessage.replaceAll("\"", ChatColor.WHITE + "\"");
                target.sendMessage(ChatColor.AQUA + mp.getActiveChar().getName() + ChatColor.RESET + ": " + ChatColor.GRAY + formatMessage);
            }
        }
    }
}
