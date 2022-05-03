package me.jupdyke01.mtcore.commands.chat;

import me.jupdyke01.mtcore.MTCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagCMD implements CommandExecutor {

    MTCore main;

    public TagCMD(MTCore main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p))
            return true;
        p.openInventory(main.getInventories().getTagInventory(p));
        return true;
    }
}
