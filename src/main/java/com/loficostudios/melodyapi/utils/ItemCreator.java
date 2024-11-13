/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.utils;


import com.loficostudios.melodyapi.interfaces.ItemMetaFunction;
import lombok.experimental.UtilityClass;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

@UtilityClass
public class ItemCreator {

    public static ItemStack createItem(@NotNull Material material, @Nullable String displayName, @Nullable List<String> lore, @Nullable Color color) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta != null) {
            if (displayName != null) {
                itemMeta.setDisplayName(displayName);

            }

            if (lore != null) {
                itemMeta.setLore(lore);

            }

            item.setItemMeta(handleItemMeta(material, itemMeta, color));
        }

        return item;
    }

    public static ItemStack createItem(@NotNull String name, @NotNull Material material, @Nullable List<ItemMetaFunction> functions, @Nullable List<ItemFlag> itemFlags, @Nullable Color color) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta != null) {

            itemMeta.setDisplayName(name);

            if (functions != null) {
                for (ItemMetaFunction function : functions) {
                    if (function != null) {
                        function.apply(itemMeta);
                    }
                }
            }

            if (itemFlags != null) {
                for (ItemFlag flag : itemFlags) {
                    itemMeta.addItemFlags(flag);
                }
            }
            item.setItemMeta(handleItemMeta(material, itemMeta, color));
        }

        return item;
    }

    public static ItemStack createItem(Plugin plugin, @NotNull String id, @NotNull String name, @NotNull Material material, @Nullable List<ItemMetaFunction> functions, @Nullable List<ItemFlag> itemFlags, @Nullable Color color) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta != null) {

            itemMeta.setDisplayName(name);

            if (functions != null) {
                for (ItemMetaFunction function : functions) {
                    if (function != null) {
                        function.apply(itemMeta);
                    }
                }
            }

            if (itemFlags != null) {
                for (ItemFlag flag : itemFlags) {
                    itemMeta.addItemFlags(flag);
                }
            }



            itemMeta.getPersistentDataContainer().set(getKey(plugin),
                    PersistentDataType.STRING, id);

            item.setItemMeta(handleItemMeta(material, itemMeta, color));
        }

        return item;
    }

    public ItemMeta handleItemMeta(@NotNull Material material, @NotNull ItemMeta itemMeta, @Nullable Color color) {
        switch (material) {
            case POTION:
                PotionMeta potionMeta = (PotionMeta) itemMeta;
                if (color != null ) {
                    potionMeta.setColor(color);
                }
                return potionMeta;

            case LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS, LEATHER_HORSE_ARMOR:
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemMeta;
                if (color != null ) {
                    leatherArmorMeta.setColor(color);
                }
                leatherArmorMeta.addItemFlags(ItemFlag.HIDE_DYE);

                return leatherArmorMeta;
        }

        return itemMeta;

    }

    public boolean isCustomItem(Plugin plugin, ItemStack item) {
        if (item.getItemMeta() == null) return false;
        return item.getItemMeta().getPersistentDataContainer().has(getKey(plugin), PersistentDataType.STRING);
    }

    public NamespacedKey getKey(Plugin plugin) {
        return new NamespacedKey(plugin, "items");
    }

    public static Boolean hasMatchingItemId(Plugin plugin, ItemStack item, String id) {

        if (!Objects.requireNonNull(item.getItemMeta())
                .getPersistentDataContainer()
                .has(getKey(plugin), PersistentDataType.STRING)) return false;

        return id.equals( item
                .getItemMeta()
                .getPersistentDataContainer()
                .get(getKey(plugin), PersistentDataType.STRING));
    }
}
