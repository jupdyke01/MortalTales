package me.jupdyke01.mtcore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UIMCItemBuilder {

    ItemStack item;

    public UIMCItemBuilder(Material material) {
        item = new ItemStack(material);
    }

    public UIMCItemBuilder(ItemStack item) {
        this.item = item;
    }

    public UIMCItemBuilder type(Material material) {
        item.setType(material);
        return this;
    }

    public UIMCItemBuilder name(String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return this;
    }

    public UIMCItemBuilder lore(String... lore) {
        lore(Arrays.asList(lore));
        return this;
    }

    public UIMCItemBuilder lore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        List<String> translatedLore = new ArrayList<>();
        lore.forEach((l) -> translatedLore.add(ChatColor.translateAlternateColorCodes('&', l)));
        meta.setLore(translatedLore);
        item.setItemMeta(meta);
        return this;
    }

    public UIMCItemBuilder durability(int durability) {
        if (item.getItemMeta() instanceof Damageable)
            ((Damageable) item.getItemMeta()).setDamage(durability);
        return this;
    }

    public UIMCItemBuilder enchant(Enchantment enchant, int level) {
        item.addUnsafeEnchantment(enchant, level);
        return this;
    }

    public UIMCItemBuilder flags(ItemFlag... flags) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flags);
        item.setItemMeta(meta);
        return this;
    }

    public UIMCItemBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public UIMCItemBuilder key(JavaPlugin main, String key, PersistentDataType type, Object value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(main,key), type, value);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStack finish() {
        return item;
    }

}
