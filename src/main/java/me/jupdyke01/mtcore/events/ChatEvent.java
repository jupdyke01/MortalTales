package me.jupdyke01.mtcore.events;

import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
        if (mp.getSettings().isDefaultGlobal()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(p, "gl " + e.getMessage());
                }
            }.runTask(main);
            return;
        }
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().equals(target.getWorld()))
                if (p.getLocation().distance(target.getLocation()) <= 20) {
                    String formatMessage = e.getMessage();
                    formatMessage = formatMessage.replaceAll("\\*", mp.getSettings().getEmoteColor() + "");
                    formatMessage = formatMessage.replaceAll("\"", ChatColor.WHITE + "\"");
                    TextComponent start = new TextComponent("");
                    TextComponent name = new TextComponent(ChatColor.AQUA + mp.getActiveChar().getName() + ChatColor.RESET + ": " + ChatColor.GRAY);
                    name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(p.getName()).create()));
                    TextComponent message = new TextComponent(ChatColor.GRAY + formatMessage);
                    message.setHoverEvent(null);
                    start.addExtra(name);
                    start.addExtra(message);
                    target.spigot().sendMessage(start);
                }
        }
    }
}
