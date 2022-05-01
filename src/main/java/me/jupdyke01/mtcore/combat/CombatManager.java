package me.jupdyke01.mtcore.combat;

import me.jupdyke01.mtcore.MTCore;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CombatManager {

    private MTCore main;

    private ArrayList<Combat> activeCombats = new ArrayList<>();

    public CombatManager(MTCore main) {
        this.main = main;
    }

    public void createCombat(Player initiator) {
        activeCombats.add(new Combat(initiator, main));
    }

    public Combat getPlayerCombat(Player p) {
        for (Combat combat : activeCombats)
            if (combat.isInCombat(p))
                return combat;
        return null;
    }

    public void joinPlayerCombat(Player joiningCombat, Player inCombat) {
        if (getPlayerCombat(inCombat) == null)
            return;
        getPlayerCombat(inCombat).addParticipant(joiningCombat);
    }

    public void leaveCombat(Player p) {
        if (getPlayerCombat(p) == null)
            return;
        Combat combat = getPlayerCombat(p);
        combat.removeParticipant(p);
        if (combat.getParticipants().isEmpty())
            activeCombats.remove(combat);
    }


}
