package me.jupdyke01.mtcore.events;

import me.jupdyke01.mtcore.Lang;
import me.jupdyke01.mtcore.MTCore;
import me.jupdyke01.mtcore.Util;
import me.jupdyke01.mtcore.cs.CharacterSheet;
import me.jupdyke01.mtcore.enums.Race;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class EditCharacter implements Listener {

    private MTCore main;
    private HashMap<Player, CharacterSheet> editingCharacters = new HashMap<>();
    private HashMap<Player, String> editingMode = new HashMap<>();

    public EditCharacter(MTCore main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().contains(ChatColor.DARK_GRAY + "Character: ")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType().equals(Material.NAME_TAG)) {
                    p.closeInventory();
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Type 'cancel' to cancel edit.");
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Please enter your new character name:");
                    editingMode.put(p, "name");
                } else if (e.getCurrentItem().getType().equals(Material.OAK_SIGN)) {
                    p.closeInventory();
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Type 'cancel' to cancel edit.");
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Please enter your new character description:");
                    editingMode.put(p, "description");
                } else if (e.getCurrentItem().getType().equals(Material.CLOCK)) {
                    p.closeInventory();
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Type 'cancel' to cancel edit.");
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Please enter your new character age:");
                    editingMode.put(p, "age");
                } else if (e.getCurrentItem().getType().equals(Material.WRITABLE_BOOK)) {
                    p.closeInventory();
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Type 'cancel' to cancel edit.");
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Please enter your new character introduction link:");
                    editingMode.put(p, "intro");
                } else if (e.getCurrentItem().getType().equals(Material.GREEN_CONCRETE)) {
                    p.closeInventory();
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Character editing: " + ChatColor.GREEN + "FINISHED" + ChatColor.GRAY + "!");
                    if (editingCharacters.containsKey(p)) {
                        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
                        editingMode.remove(p);
                        if (mp.getCharacterById(editingCharacters.get(p).getId()) != null) {
                            CharacterSheet changed = editingCharacters.get(p);
                            mp.getCharacterById(editingCharacters.get(p).getId()).setName(changed.getName());
                            mp.getCharacterById(editingCharacters.get(p).getId()).setAge(changed.getAge());
                            mp.getCharacterById(editingCharacters.get(p).getId()).setIntroduction(changed.getIntroduction());
                            mp.getCharacterById(editingCharacters.get(p).getId()).setDescription(changed.getDescription());
                        } else {
                            mp.addCharacter(editingCharacters.get(p));
                        }
                        editingCharacters.remove(p);
                        p.openInventory(main.getInventories().getCharsInventory(p));
                    }
                } else if (e.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                    p.closeInventory();
                    editingMode.remove(p);
                    editingCharacters.remove(p);
                    p.openInventory(main.getInventories().getCharsInventory(p));
                }
            }
        } else if (e.getView().getTitle().contains("Select a race.")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName())
                return;
            Race race = Util.raceFromString(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
            if (race == null)
                return;
            if (!editingCharacters.containsKey(p))
                return;
            editingCharacters.get(p).setRace(race);
            editingCharacters.get(p).setCurrentMortalWounds(race.getDefaultMortalWounds());
            p.openInventory(main.getInventories().changeCharacter(p, editingCharacters.get(p)));
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());

        if (editingCharacters.containsKey(p)) {
            if (editingMode.containsKey(p)) {
                e.setCancelled(true);
                if (e.getMessage().equalsIgnoreCase("cancel")) {
                    editingMode.remove(p);
                    p.sendMessage(Lang.PREFIX.getLang() + ChatColor.GRAY + "Edit cancelled!");
                    return;
                }
                if (editingMode.get(p).equalsIgnoreCase("name")) {
                    for (CharacterSheet character : mp.getCharacters()) {
                        if (character.getName().equalsIgnoreCase(e.getMessage()))	{
                            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You can not have two characters of the same name!");
                            return;
                        }
                    }
                    editingCharacters.get(p).setName(e.getMessage());
                    editingMode.remove(p);
                } else if (editingMode.get(p).equalsIgnoreCase("description")) {
                    editingCharacters.get(p).setDescription(e.getMessage());
                    editingMode.remove(p);
                } else if (editingMode.get(p).equalsIgnoreCase("intro")) {
                    editingCharacters.get(p).setIntroduction(e.getMessage());
                    editingMode.remove(p);
                } else if (editingMode.get(p).equalsIgnoreCase("age")) {
                    try {
                        int age = Integer.parseInt(e.getMessage());
                        if (age <= 0) {
                            p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You must enter a valid age!");
                        } else {
                            editingCharacters.get(p).setAge(age);
                            editingMode.remove(p);
                        }
                    } catch (NumberFormatException nfe) {
                        editingMode.remove(p);
                        p.sendMessage(Lang.PREFIX.getLang() + ChatColor.RED + "You must enter a valid age!");
                    }
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.openInventory(main.getInventories().changeCharacter(p, editingCharacters.get(p)));
                    }
                }.runTask(main);
            }
        }
    }

    public void newCharacter(Player p, Race race, boolean event) {
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        CharacterSheet character;
        if (editingCharacters.containsKey(p)) {
            character = editingCharacters.get(p);
        } else {
            character = new CharacterSheet(mp.getFirstAvailableId());
        }
        character.setEvent(event);
        editingCharacters.put(p, character);
        p.openInventory(main.getInventories().raceInventory());
    }

    public void editCharacter(Player p, CharacterSheet character) {
        p.openInventory(main.getInventories().changeCharacter(p, character));
        editingCharacters.put(p, character);
    }
}
