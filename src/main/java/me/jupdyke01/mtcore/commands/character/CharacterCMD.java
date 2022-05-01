package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CharacterCMD implements CommandExecutor {

    MTCore main;

    public CharacterCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player p))
            return true;

        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());

        OfflinePlayer target = null;
        if (args.length > 0) {
            String t = args[0];
            for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
                if (op.getName().equals(t)) {
                    target = op;
                    break;
                }
            }
        }
        if (target == null) {
            target = p;
        }
        if (mp.getSettings().isTextDisplay()) {
            MortalPlayer tp = main.getMortalPlayerManager().getPlayer(target.getUniqueId());
            tp.getActiveChar().getCharacterText().forEach(p::sendMessage);
        } else {
            p.openInventory(main.getInventories().getCharInventory(target));
        }
        return true;
    }
}
