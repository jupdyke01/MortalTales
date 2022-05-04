package me.jupdyke01.mtcore.events;

import me.jupdyke01.mtcore.Lang;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null)
            return;
        if (e.getClickedBlock().getType() != Material.CAULDRON)
            return;

        if (!(e.getClickedBlock().getWorld().getBlockAt(e.getClickedBlock().getLocation().subtract(0, 1,0)).getType() == Material.FIRE))
            return;
        e.setCancelled(true);
        // It's an alchemy station!
        e.getPlayer().sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY +"Alchemy is still being worked on! Check back soon.");

    }


}
