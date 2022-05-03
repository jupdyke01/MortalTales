package me.jupdyke01.mtcore.events;


import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.enums.Tag;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TagSelect implements Listener {

    private MTCore main;

    public TagSelect(MTCore main) {
        this.main = main;
    }


    @EventHandler
    public void onCLick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p))
            return;
        if (!e.getView().getTitle().contains("Tags"))
            return;
        e.setCancelled(true);
        if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName())
            return;
        Tag tag = Tag.getTagBySuffix(e.getCurrentItem().getItemMeta().getDisplayName());
        if (tag == null)
            return;
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        if (tag == mp.getTag())
            return;
        if (!p.hasPermission(tag.getPermission()))
            return;
        mp.setTag(tag);
        p.openInventory(main.getInventories().getTagInventory(p));

    }

}
