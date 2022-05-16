package me.jupdyke01.mtcore;

import me.jupdyke01.mtcore.cs.CharacterSheet;
import me.jupdyke01.mtcore.enums.Race;
import me.jupdyke01.mtcore.enums.Tag;
import me.jupdyke01.mtcore.players.MortalPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class MTInventories {

    private MTCore main;

    public MTInventories(MTCore main) {
        this.main = main;
    }

    public Inventory getEmoteColorInventory(Player p) {
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        Inventory i = Bukkit.createInventory(null, 45, "Emote Color : Unlocks: " + mp.getEmoteUnlocks());

        ArrayList<ItemStack> emoteColors = new ArrayList<>();
        ItemStack currentColor = null;

        ItemStack red = new ItemStack(Material.RED_WOOL, 1);
        ItemMeta redMeta = red.getItemMeta();
        redMeta.setDisplayName(ChatColor.DARK_RED + "Dark Red");
        List<String> redLore = new ArrayList<>();
        redLore.add(mp.hasUnlockedEmote(ChatColor.DARK_RED) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        redMeta.setLore(redLore);
        red.setItemMeta(redMeta);

        ItemStack gold = new ItemStack(Material.ORANGE_WOOL, 1);
        ItemMeta goldMeta = gold.getItemMeta();
        goldMeta.setDisplayName(ChatColor.GOLD + "Gold");
        List<String> goldLore = new ArrayList<>();
        goldLore.add(mp.hasUnlockedEmote(ChatColor.GOLD) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        goldMeta.setLore(goldLore);
        gold.setItemMeta(goldMeta);

        ItemStack yellow = new ItemStack(Material.YELLOW_WOOL, 1);
        ItemMeta yellowMeta = gold.getItemMeta();
        yellowMeta.setDisplayName(ChatColor.YELLOW + "Yellow");
        List<String> yellowLore = new ArrayList<>();
        yellowLore.add(mp.hasUnlockedEmote(ChatColor.YELLOW) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        yellowMeta.setLore(yellowLore);
        yellow.setItemMeta(yellowMeta);

        ItemStack darkGreen = new ItemStack(Material.GREEN_WOOL, 1);
        ItemMeta darkGreenMeta = gold.getItemMeta();
        darkGreenMeta.setDisplayName(ChatColor.DARK_GREEN + "Dark Green");
        List<String> darkGreenLore = new ArrayList<>();
        darkGreenLore.add(mp.hasUnlockedEmote(ChatColor.DARK_GREEN) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        darkGreenMeta.setLore(darkGreenLore);
        darkGreen.setItemMeta(darkGreenMeta);

        ItemStack green = new ItemStack(Material.LIME_WOOL, 1);
        ItemMeta greenMeta = gold.getItemMeta();
        greenMeta.setDisplayName(ChatColor.GREEN + "Green");
        List<String> greenLore = new ArrayList<>();
        greenLore.add(mp.hasUnlockedEmote(ChatColor.GREEN) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        greenMeta.setLore(greenLore);
        green.setItemMeta(greenMeta);

        ItemStack darkBlue = new ItemStack(Material.BLUE_WOOL, 1);
        ItemMeta darkBlueMeta = darkBlue.getItemMeta();
        darkBlueMeta.setDisplayName(ChatColor.DARK_BLUE + "Dark Blue");
        List<String> darkBlueLore = new ArrayList<>();
        darkBlueLore.add(mp.hasUnlockedEmote(ChatColor.DARK_BLUE) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        darkBlueMeta.setLore(darkBlueLore);
        darkBlue.setItemMeta(darkBlueMeta);

        ItemStack darkAqua = new ItemStack(Material.CYAN_WOOL, 1);
        ItemMeta darkAquaMeta = darkAqua.getItemMeta();
        darkAquaMeta.setDisplayName(ChatColor.DARK_AQUA + "Dark Aqua");
        List<String> darkAquaLore = new ArrayList<>();
        darkAquaLore.add(mp.hasUnlockedEmote(ChatColor.DARK_AQUA) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        darkAquaMeta.setLore(darkAquaLore);
        darkAqua.setItemMeta(darkAquaMeta);

        ItemStack lightPurple = new ItemStack(Material.MAGENTA_WOOL, 1);
        ItemMeta lightPurpleMeta = lightPurple.getItemMeta();
        lightPurpleMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Light Purple");
        List<String> lightPurpleLore = new ArrayList<>();
        lightPurpleLore.add(mp.hasUnlockedEmote(ChatColor.LIGHT_PURPLE) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        lightPurpleMeta.setLore(lightPurpleLore);
        lightPurple.setItemMeta(lightPurpleMeta);

        ItemStack darkPurple = new ItemStack(Material.PURPLE_WOOL, 1);
        ItemMeta darkPurpleMeta = darkPurple.getItemMeta();
        darkPurpleMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dark Purple");
        List<String> darkPurpleLore = new ArrayList<>();
        darkPurpleLore.add(mp.hasUnlockedEmote(ChatColor.DARK_PURPLE) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        darkPurpleMeta.setLore(darkPurpleLore);
        darkPurple.setItemMeta(darkPurpleMeta);

        ItemStack darkGray = new ItemStack(Material.GRAY_WOOL, 1);
        ItemMeta darkGrayMeta = darkGray.getItemMeta();
        darkGrayMeta.setDisplayName(ChatColor.DARK_GRAY + "Dark Gray");
        List<String> darkGrayLore = new ArrayList<>();
        darkGrayLore.add(mp.hasUnlockedEmote(ChatColor.DARK_GRAY) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked");
        darkGrayMeta.setLore(darkGrayLore);
        darkGray.setItemMeta(darkGrayMeta);

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        ItemStack whiteFiller = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta whiteFillerMeta = whiteFiller.getItemMeta();
        whiteFillerMeta.setDisplayName(ChatColor.GREEN + "CURRENT");
        whiteFiller.setItemMeta(whiteFillerMeta);

        emoteColors.add(red);
        emoteColors.add(gold);
        emoteColors.add(yellow);
        emoteColors.add(darkGreen);
        emoteColors.add(green);
        emoteColors.add(darkBlue);
        emoteColors.add(darkAqua);
        emoteColors.add(darkPurple);
        emoteColors.add(lightPurple);
        emoteColors.add(darkGray);

        for (int x = 0; x < emoteColors.size(); x++) {
            if (emoteColors.get(x).getItemMeta().getDisplayName().contains(mp.getSettings().getEmoteColor() + "")) {
                currentColor = emoteColors.get(x);
                emoteColors.remove(x);
            }
        }

        i.setItem(10, whiteFiller);
        i.setItem(11, whiteFiller);
        i.setItem(12, whiteFiller);
        i.setItem(19, whiteFiller);
        i.setItem(20, currentColor);
        i.setItem(21, whiteFiller);
        i.setItem(28, whiteFiller);
        i.setItem(29, whiteFiller);
        i.setItem(30, whiteFiller);

        i.setItem(14, emoteColors.get(0));
        i.setItem(15, emoteColors.get(1));
        i.setItem(16, emoteColors.get(2));
        i.setItem(23, emoteColors.get(3));
        i.setItem(24, emoteColors.get(4));
        i.setItem(25, emoteColors.get(5));
        i.setItem(32, emoteColors.get(6));
        i.setItem(33, emoteColors.get(7));
        i.setItem(34, emoteColors.get(8));

        for (int x = 0; x < 45; x++) {
            if (i.getItem(x) == null)
                i.setItem(x, filler);
        }

        return i;
    }

    public Inventory changeCharacter(Player p, CharacterSheet character) {
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        Inventory i = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Character: " + ChatColor.YELLOW + character.getName());

        /*this is where the magic happens*/
        ItemStack charItem = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta charItemMeta = (SkullMeta) charItem.getItemMeta();
        charItemMeta.setOwningPlayer(p);
        charItemMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Name: " + ChatColor.YELLOW + "" + ChatColor.BOLD + character.getName());
        ArrayList<String> charItemLore = new ArrayList<>();
        charItemLore.add(ChatColor.GRAY + "Race: " + ChatColor.YELLOW + character.getRace().getName());
        charItemLore.add(" ");
        charItemLore.add(ChatColor.GRAY + "Age: " + ChatColor.YELLOW + character.getAge());
        charItemLore.add(" ");
        charItemLore.add(ChatColor.GRAY + "Intro: " + ChatColor.YELLOW + character.getIntroduction());
        charItemLore.add(" ");
        charItemLore.add(ChatColor.GRAY + "Desc: " + ChatColor.YELLOW + character.getDescription());
        charItemLore.add(" ");
        charItemLore.add(ChatColor.GRAY + "Mortal Wounds: " + ChatColor.YELLOW + character.getCurrentMortalWounds() + "/" + character.getFinalMortalWounds());
        charItemLore.add(ChatColor.GRAY + "Magic Level: " + ChatColor.YELLOW + character.getFinalMagicLevel());
        charItemLore.add(ChatColor.GRAY + "Range: " + ChatColor.YELLOW + character.getFinalRange());
        charItemMeta.setLore(charItemLore);
        charItem.setItemMeta(charItemMeta);

        ItemStack changeName = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta changeNameMeta = changeName.getItemMeta();
        changeNameMeta.setDisplayName(ChatColor.AQUA + "Change Character Name");
        List<String> changeNameLore = new ArrayList<>();
        changeNameLore.add(ChatColor.GRAY + "Current: " + ChatColor.YELLOW + character.getName());
        changeNameMeta.setLore(changeNameLore);
        changeName.setItemMeta(changeNameMeta);

        ItemStack changeDescription = new ItemStack(Material.OAK_SIGN, 1);
        ItemMeta changeDescriptionMeta = changeDescription.getItemMeta();
        changeDescriptionMeta.setDisplayName(ChatColor.AQUA + "Change Character Description");
        List<String> changeDescriptionLore = new ArrayList<>();
        changeDescriptionLore.add(ChatColor.GRAY + "Current: " + ChatColor.YELLOW + character.getDescription());
        changeDescriptionMeta.setLore(changeDescriptionLore);
        changeDescription.setItemMeta(changeDescriptionMeta);

        ItemStack changeAge = new ItemStack(Material.CLOCK, 1);
        ItemMeta changeAgeMeta = changeAge.getItemMeta();
        changeAgeMeta.setDisplayName(ChatColor.AQUA + "Change Character Age");
        List<String> changeAgeLore = new ArrayList<>();
        changeAgeLore.add(ChatColor.GRAY + "Current: " + ChatColor.YELLOW + character.getAge());
        changeAgeMeta.setLore(changeAgeLore);
        changeAge.setItemMeta(changeAgeMeta);

        ItemStack changeIntroduction = new ItemStack(Material.WRITABLE_BOOK, 1);
        ItemMeta changeIntroductionMeta = changeIntroduction.getItemMeta();
        changeIntroductionMeta.setDisplayName(ChatColor.AQUA + "Change Character Intro Link");
        List<String> changeIntroductionLore = new ArrayList<>();
        changeIntroductionLore.add(ChatColor.GRAY + "Current: " + ChatColor.YELLOW + character.getIntroduction());
        changeIntroductionMeta.setLore(changeIntroductionLore);
        changeIntroduction.setItemMeta(changeIntroductionMeta);

        ItemStack finishEditing = new ItemStack(Material.GREEN_CONCRETE, 1);
        ItemMeta finishEditingMeta = finishEditing.getItemMeta();
        finishEditingMeta.setDisplayName(ChatColor.GREEN + "Finish");
        finishEditing.setItemMeta(finishEditingMeta);

        ItemStack cancelEditing = new ItemStack(Material.RED_CONCRETE, 1);
        ItemMeta cancelEditingMeta = cancelEditing.getItemMeta();
        cancelEditingMeta.setDisplayName(ChatColor.RED + "Cancel");
        cancelEditing.setItemMeta(cancelEditingMeta);


        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        i.setItem(11, changeName);
        i.setItem(13, charItem);
        i.setItem(15, changeDescription);
        i.setItem(29, changeAge);
        i.setItem(33, changeIntroduction);
        i.setItem(48, finishEditing);
        i.setItem(50, cancelEditing);

        for (int x = 0; x < 54; x++) {
            if (i.getItem(x) == null)
                i.setItem(x, filler);
        }
        return i;
    }

    public Inventory raceInventory() {
        Inventory i = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Select a race.");
        int x = 0;
        for (Race race : Race.values()) {
            ItemStack raceItem = new ItemStack(race.getDisplay(), 1);
            ItemMeta raceItemMeta = raceItem.getItemMeta();
            raceItemMeta.setDisplayName(ChatColor.AQUA + race.getName());
            raceItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            List<String> raceLore = new ArrayList<>();
            raceLore.add(ChatColor.GRAY + race.getDesc());
            raceItemMeta.setLore(raceLore);
            raceItem.setItemMeta(raceItemMeta);
            i.setItem(x, raceItem);
            x++;
        }
        return i;
    }

    public Inventory getCharsInventory(OfflinePlayer p) {
        Inventory i = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Characters: " + ChatColor.YELLOW + p.getName());

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        ArrayList<ItemStack> characters = new ArrayList<>();

        ItemStack availableSlot = new UIMCItemBuilder(Material.LIME_STAINED_GLASS_PANE).name(ChatColor.GREEN + "" + ChatColor.BOLD + "Available Slot").lore(ChatColor.GRAY + "You can have an additional character here.").amount(1).finish();

        MortalPlayer mp = main.getMortalPlayerManager().getPlayerOrRead(p.getUniqueId());
        for (CharacterSheet character : mp.getCharacters()) {
            ItemStack charItem = character.getCharacterItem(p);
            ItemMeta charItemMeta = charItem.getItemMeta();
            List<String> lore = new ArrayList<>(charItemMeta.getLore());
            lore.add(" ");
            String status = mp.getActiveChar().equals(character) ? ChatColor.GREEN + "" + ChatColor.BOLD + "ACTIVE" : ChatColor.RED + "" + ChatColor.BOLD + "INACTIVE";
            lore.add(ChatColor.GRAY + "Status: " + status);
            charItemMeta.setLore(lore);
            charItem.setItemMeta(charItemMeta);
            characters.add(charItem);
        }
        if (p.isOnline()) {
            Player onlineP = p.getPlayer();
            for (int available = 0; available < mp.getAvailableSlots(onlineP); available++) {
                characters.add(availableSlot);
            }
        }


        for (int x = 0; x < 9; x++) {
            i.setItem(x, filler);
        }
        i.setItem(9, filler);

        int y = 10;
        for (ItemStack charItem : characters) {
            i.setItem(y, charItem);
            y++;
        }


        i.setItem(17, filler);
        for (int x = 18; x < 27; x++) {
            i.setItem(x, filler);
        }

        return i;
    }

    public Inventory getCharInventory(OfflinePlayer p) {
        Inventory i = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Characters: " + ChatColor.YELLOW + p.getName());

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        MortalPlayer mp = main.getMortalPlayerManager().getPlayerOrRead(p.getUniqueId());

        i.setItem(13, mp.getActiveChar().getCharacterItem(p));
        for (int x = 0; x < 27; x++) {
            if (i.getItem(x).getType() == Material.AIR)
                i.setItem(x, filler);
        }
        return i;
    }

    public Inventory getSettingsInventory(Player p) {
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        Inventory i = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Settings");

        ItemStack global = new UIMCItemBuilder(mp.getSettings().isGlobalChat() ? Material.GREEN_WOOL : Material.RED_WOOL).name(ChatColor.AQUA + "Global Chat").lore(ChatColor.GRAY + "Toggles global chat on and off.", " ", mp.getSettings().isGlobalChat() ? ChatColor.GRAY + "Status: " + ChatColor.GREEN + "Enabled" : ChatColor.GRAY + "Status: " + ChatColor.RED + "Disabled").finish();
        ItemStack text = new UIMCItemBuilder(mp.getSettings().isTextDisplay() ? Material.GREEN_WOOL : Material.RED_WOOL).name(ChatColor.AQUA + "Text Display").lore(ChatColor.GRAY + "Toggles GUI or Text mode for character sheets.", " ", mp.getSettings().isTextDisplay() ? ChatColor.GRAY + "Status: " + ChatColor.GREEN + "Enabled" : ChatColor.GRAY + "Status: " + ChatColor.RED + "Disabled").finish();
        ItemStack staff = new UIMCItemBuilder(mp.getSettings().isStaffChat() ? Material.GREEN_WOOL : Material.RED_WOOL).name(ChatColor.AQUA + "Staff Chat").lore(ChatColor.GRAY + "Toggles staff chat on and off.", " ", mp.getSettings().isStaffChat() ? ChatColor.GRAY + "Status: " + ChatColor.GREEN + "Enabled" : ChatColor.GRAY + "Status: " + ChatColor.RED + "Disabled").finish();
        ItemStack defaultGlobal = new UIMCItemBuilder(mp.getSettings().isDefaultGlobal() ? Material.GREEN_WOOL : Material.RED_WOOL).name(ChatColor.AQUA + "Default Global").lore(ChatColor.GRAY + "Toggles default chat to global.", " ", mp.getSettings().isDefaultGlobal() ? ChatColor.GRAY + "Status: " + ChatColor.GREEN + "Enabled" : ChatColor.GRAY + "Status: " + ChatColor.RED + "Disabled").finish();
        ItemStack emote = new UIMCItemBuilder(Util.woolColorFromChatColor(mp.getSettings().getEmoteColor())).name(ChatColor.AQUA + "Emote Color").lore(ChatColor.GRAY + "Change emote color.").finish();

        i.setItem(0, global);
        i.setItem(2, text);
        i.setItem(4, staff);
        i.setItem(6, defaultGlobal);
        i.setItem(8, emote);
        return i;
    }

    public Inventory getTagInventory(Player p) {
        MortalPlayer mp = main.getMortalPlayerManager().getPlayer(p.getUniqueId());
        Inventory i = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Tags");

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        ItemStack yellow = new UIMCItemBuilder(Material.YELLOW_WOOL).name(ChatColor.YELLOW + "Yellow Tags").finish();
        ItemStack purple = new UIMCItemBuilder(Material.MAGENTA_WOOL).name(ChatColor.LIGHT_PURPLE + "Purple Tags").finish();
        ItemStack blue = new UIMCItemBuilder(Material.BLUE_WOOL).name(ChatColor.BLUE + "Blue Tags").finish();
        ItemStack red = new UIMCItemBuilder(Material.RED_WOOL).name(ChatColor.RED + "Red Tags").finish();

        List<ItemStack> tagList = new ArrayList<>();

        for (Tag tag : Tag.values()) {
            if (tag == Tag.NONE)
                continue;
            String locked = p.hasPermission(tag.getPermission()) ? ChatColor.GREEN + "" + ChatColor.BOLD + "UNLOCKED" : ChatColor.RED + "" + ChatColor.BOLD + "LOCKED";
            UIMCItemBuilder item = new UIMCItemBuilder(Material.NAME_TAG).name(tag.getSuffix()).lore(locked);

            if (mp.getTag() == tag)
                item.enchant(Enchantment.DURABILITY, 1);

            tagList.add(item.finish());
        }
        int tag = 0;
        for (int x = 1; x < 8; x+=2) {
            for (int y = 1; y < 6; y++) {
                i.setItem(x + y*9, tagList.get(tag));
                tag++;
            }
        }

        i.setItem(1, yellow);
        i.setItem(3, purple);
        i.setItem(5, blue);
        i.setItem(7, red);

        for (int y = 0; y < 54; y++) {
            if (i.getItem(y) == null)
                i.setItem(y, filler);
        }

        return i;
    }

}
