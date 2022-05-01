package me.jupdyke01.mtcore.commands.character;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.cs.CharacterSheet;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WoundsCMD implements CommandExecutor {

    private MTCore main;

    public WoundsCMD(MTCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
            CharacterSheet character = mp.getActiveChar();
            if (args.length == 0) {
                p.sendMessage(ChatColor.GRAY + "Current wounds: " + ChatColor.YELLOW + character.getCurrentMortalWounds() + "/" + character.getFinalMortalWounds());
            }
            switch(args.length) {
                case 1:
                    if (args[0].equalsIgnoreCase("+") || args[0].equalsIgnoreCase("add")) {
                        if (character.getCurrentMortalWounds() >= character.getFinalMortalWounds()) {
                            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You are at your max allowed mortal wounds!");
                        } else {
                            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Mortal wounds added.");
                            character.setCurrentMortalWounds(character.getCurrentMortalWounds() + 1);
                        }
                        return true;
                    } else if (args[0].equalsIgnoreCase("-") || args[0].equalsIgnoreCase("remove")) {
                        if (character.getCurrentMortalWounds() <= 0) {
                            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You are at 0 mortal wounds!");
                        } else {
                            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Mortal wounds taken.");
                            character.setCurrentMortalWounds(character.getCurrentMortalWounds() - 1);
                        }
                    } else if (args[0].equalsIgnoreCase("reset")) {
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Mortal wounds reset.");
                        character.setCurrentMortalWounds(character.getFinalMortalWounds());
                    }
                    break;
            }
        }
        return true;
    }
}
