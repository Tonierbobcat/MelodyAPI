/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @Link https://github.com/Tonierbobcat/MelodyAPI
 * @version 0.1.3
 */

package com.loficostudios.melodyapi.utils.bukkit;

import com.loficostudios.melodyapi.utils.StringUtils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStackUtils {
    public static String getDisplayNameOrElseMaterialName(ItemStack item) {

        var meta = item.getItemMeta();

        if (meta != null && !StringUtils.isNullOrEmpty(meta.getDisplayName()))
            return meta.getDisplayName();
        else
            return getFormattedMaterialName(item.getType());
    }
    public static String getFormattedMaterialName(@NotNull Material material) {
        String rawName = material.toString();

        String[] words = rawName.split("_");

        for (int i = 0; i < words.length; i++) {
            String raw = words[i];

            if (!raw.isEmpty()) {
                char firstLetter = raw.charAt(0);
                words[i] =  Character.toUpperCase(firstLetter) + raw.substring(1).toLowerCase();
            }
        }

        return String.join(" ", words);
    }
}
