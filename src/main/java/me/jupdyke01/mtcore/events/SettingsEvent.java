package me.jupdyke01.mtcore.events;


import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SettingsEvent implements Listener {

    private MTCore main;

    public SettingsEvent(MTCore main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p))
            return;
        if (!e.getView().getTitle().contains("Settings"))
            return;
        if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName())
            return;
        e.setCancelled(true);
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        ItemStack clicked = e.getCurrentItem();

        if (clicked.getItemMeta().getDisplayName().contains("Global Chat")) {
                mp.getSettings().setGlobalChat(!mp.getSettings().isGlobalChat());
                p.openInventory(main.getInventories().getSettingsInventory(p));
        } else if (clicked.getItemMeta().getDisplayName().contains("Text Display")) {
            mp.getSettings().setTextDisplay(!mp.getSettings().isTextDisplay());
            p.openInventory(main.getInventories().getSettingsInventory(p));
        } else if (clicked.getItemMeta().getDisplayName().contains("Staff Chat")) {
            mp.getSettings().setStaffChat(!mp.getSettings().isStaffChat());
            p.openInventory(main.getInventories().getSettingsInventory(p));
        } else if (clicked.getItemMeta().getDisplayName().contains("Emote Color")) {
            p.openInventory(main.getInventories().getEmoteColorInventory(p));
        }
    }

}
