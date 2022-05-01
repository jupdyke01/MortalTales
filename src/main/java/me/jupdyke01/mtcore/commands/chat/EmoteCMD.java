package me.jupdyke01.mtcore.commands.chat;

import me.jupdyke01.mtcore.MTCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EmoteCMD implements CommandExecutor {

    private MTCore main;

    public EmoteCMD(MTCore main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player))
            return true;

        Player p = (Player) sender;
        p.openInventory(main.getInventories().getEmoteColorInventory(p));
        return true;
    }
}
