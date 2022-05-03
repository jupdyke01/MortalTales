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
    private int turnIndex;

    public Combat(Player initiator, MTCore main) {
        this.origin = initiator.getLocation();
        this.battleStart = System.currentTimeMillis();
        this.main = main;
        participants.add(initiator);
        turnIndex = 0;
    }

    public void addParticipant(Player p) {
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        for (Player target : participants) {
            MortalPlayer tp = main.getMortalPlayerManager().getPlayer(target.getUniqueId());
            tp.addFocused(p);
            mp.addFocused(target);
            tp.focusScoreBoard(target);
            mp.focusScoreBoard(p);
            target.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Player joined combat: " + ChatColor.YELLOW + p.getName());
        }
        participants.add(p);
        invited.remove(p);
    }

    public void removeParticipant(Player p) {
        if (getPlayerTurn().equals(p)) {
            nextTurn();
        }
        participants.remove(p);
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        for (Player target : participants) {
            MortalPlayer tp = main.getMortalPlayerManager().getPlayer(target.getUniqueId());
            tp.remFocused(p);
            mp.remFocused(target);
            tp.focusScoreBoard(target);
            mp.focusScoreBoard(p);
            target.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Player left combat: " + ChatColor.YELLOW + p.getName());
        }
    }

    public void nextTurn() {
        for (Player target : participants) {
            target.sendMessage(ChatColor.GRAY + "Turn Ended");
            if (getPlayerTurn() != null) {
                MortalPlayer mp = main.getMortalPlayerManager().getPlayer(getPlayerTurn().getUniqueId());
                target.sendMessage(ChatColor.GRAY + getPlayerTurn().getName() + " MW: " + ChatColor.YELLOW + mp.getActiveChar().getCurrentMortalWounds() + "/" + mp.getActiveChar().getFinalMortalWounds());
            }
        }

        if (participants.size() > turnIndex+1) {
            turnIndex++;
        } else {
            turnIndex = 0;
        }

        for (Player target : participants) {
            if (getPlayerTurn() != null)
                target.sendMessage(ChatColor.GRAY + "Next player: " + ChatColor.YELLOW + getPlayerTurn().getName());
        }


    }

    public void unInvite(Player p) {
        if (invited.contains(p))
            invited.remove(p);
        for (Player target : participants) {
            target.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Player declined combat: " + ChatColor.YELLOW + p.getName());
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
        TextComponent base = new TextComponent("");
        TextComponent join = new TextComponent(ChatColor.DARK_GRAY + "        [" + ChatColor.GREEN + "" + ChatColor.BOLD + "JOIN" + ChatColor.DARK_GRAY + "]");
        join.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.DARK_GRAY + "Click to join combat!").create()));
        join.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/combat join " + invitingPlayer.getName()));

        TextComponent decline = new TextComponent(ChatColor.DARK_GRAY + "      [" + ChatColor.RED + "" + ChatColor.BOLD + "DECLINE" + ChatColor.DARK_GRAY + "]");
        decline.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.DARK_GRAY + "Click to decline combat!").create()));
        decline.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/combat decline " + invitingPlayer.getName()));

        base.addExtra(join);
        base.addExtra(decline);
        invitedPlayer.spigot().sendMessage(base);
    }

    public int getTurnIndex() {
        return turnIndex;
    }

    public void setTurnIndex(int turnIndex) {
        this.turnIndex = turnIndex;
    }

    public Player getPlayerTurn() {
        if (participants.size()-1 >= turnIndex)
            return participants.get(turnIndex);
        return null;
    }
}
