package me.jupdyke01.mtcore.events;

import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.Util;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EmoteSelect implements Listener {

    private MTCore main;

    public EmoteSelect(MTCore main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        if (e.getView().getTitle().contains("Emote Color")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName())
                return;
            ChatColor clickedColor = Util.chatColorFromString(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
            if (clickedColor == null)
                return;
            if (clickedColor == mp.getSettings().getEmoteColor())
                return;

            if (mp.hasUnlockedEmote(clickedColor)) {
                mp.getSettings().setEmoteColor(clickedColor);
                p.openInventory(main.getInventories().getEmoteColorInventory(p));
            } else {
                if (mp.getEmoteUnlocks() >= 1) {
                    mp.setEmoteUnlocks(mp.getEmoteUnlocks() - 1);
                    mp.unlockEmote(clickedColor);
                    mp.getSettings().setEmoteColor(clickedColor);
                    p.openInventory(main.getInventories().getEmoteColorInventory(p));
                }
            }
        }
    }
}
