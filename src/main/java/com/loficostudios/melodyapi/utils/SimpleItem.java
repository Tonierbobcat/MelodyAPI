package com.loficostudios.melodyapi.utils;

import com.loficostudios.melodyapi.utils.interfaces.ItemMetaFunction;
import lombok.experimental.UtilityClass;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.Nullable;

import java.util.List;

@UtilityClass
public class SimpleItem {

    public static ItemStack createItem(Plugin plugin, String id, Material material, @Nullable List<ItemMetaFunction> functions, @Nullable List<ItemFlag> itemFlags, @Nullable Color color) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        if (functions != null) {
            for (ItemMetaFunction function : functions) {
                function.apply(itemMeta);
            }
        }

        if (itemFlags != null) {
            for (ItemFlag flag : itemFlags) {
                itemMeta.addItemFlags(flag);
            }
        }

        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "myItems"),
                PersistentDataType.STRING, id);


        if (material == Material.POTION && itemMeta instanceof PotionMeta) {
            PotionMeta potionMeta = (PotionMeta) itemMeta;

            if (color != null ) {
                potionMeta.setColor(color);
            }

            item.setItemMeta(potionMeta);
        } else {
            item.setItemMeta(itemMeta);
        }

        return item;
    }
}
