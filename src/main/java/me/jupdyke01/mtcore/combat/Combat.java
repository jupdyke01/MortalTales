package me.jupdyke01.mtcore.combat;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.players.MortalPlayer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Combat {

    private MTCore main;

    private final Location origin;
    private final long battleStart;

    private ArrayList<Player> participants = new ArrayList<>();
    private ArrayList<Player> invited = new ArrayList<>();

    public Combat(Player initiator, MTCore main) {
        this.origin = initiator.getLocation();
        this.battleStart = System.currentTimeMillis();
        this.main = main;
        participants.add(initiator);
    }

    public void addParticipant(Player p) {
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        for (Player target : participants) {
            MortalPlayer tp = main.getMortalPlayerManager().getPlayer(target.getUniqueId());
            tp.addFocused(p);
            mp.addFocused(target);
            target.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Player joined combat: " + ChatColor.YELLOW + p.getName());
        }
        participants.add(p);
        invited.remove(p);
    }

    public void removeParticipant(Player p) {
        participants.remove(p);
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        for (Player target : participants) {
            MortalPlayer tp = main.getMortalPlayerManager().getPlayer(target.getUniqueId());
            tp.remFocused(p);
            mp.remFocused(target);
            target.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Player left combat: " + ChatColor.YELLOW + p.getName());
        }
    }

    public ArrayList<Player> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Player> participants) {
        this.participants = participants;
    }

    public boolean isInCombat(Player p) {
        return participants.contains(p);
    }

    public Location getOrigin() {
        return origin;
    }

    public long getBattleStart() {
        return battleStart;
    }

    public ArrayList<Player> getInvited() {
        return invited;
    }

    public boolean isInvited(Player p) {
        return invited.contains(p);
    }

    public void invitePlayer(Player invitedPlayer, Player invitingPlayer) {
        invited.add(invitedPlayer);
        invitedPlayer.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "You have been invited to combat by: " + ChatColor.YELLOW + invitingPlayer.getName());
        TextComponent join = new TextComponent(ChatColor.DARK_GRAY + "        [" + ChatColor.GREEN + "" + ChatColor.BOLD + "JOIN" + ChatColor.DARK_GRAY + "]");
        join.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.DARK_GRAY + "Click to join combat!").create()));
        join.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/combat join " + invitingPlayer.getName()));
        invitedPlayer.spigot().sendMessage(join);
    }
}
