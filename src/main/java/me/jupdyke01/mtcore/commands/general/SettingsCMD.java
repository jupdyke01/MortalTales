package me.jupdyke01.mtcore.commands.general;

import me.jupdyke01.mtcore.MTCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SettingsCMD implements CommandExecutor {

    private MTCore main;

    public SettingsCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p))
            return true;
        p.openInventory(main.getInventories().getSettingsInventory(p));
        return true;
    }

}
