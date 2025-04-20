/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @Link https://github.com/Tonierbobcat/MelodyAPI
 * @version 0.1.3
 */

package com.loficostudios.melodyapi.utils.bukkit;

import com.loficostudios.melodyapi.utils.StringUtils;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStackUtils {
    @Deprecated
    public static String getLegacyDisplayNameOrElseMaterialName(ItemStack item) {
        var meta = item.getItemMeta();

        if (meta != null && !StringUtils.isNullOrEmpty(meta.getDisplayName()))
            return meta.getDisplayName();
        else
            return getFormattedMaterialName(item.getType());
    }

    public static Component getDisplayNameOrElseMaterialName(@NotNull ItemStack item) {
        var meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName()) {
            return meta.displayName();
        } else {
            return Component.text(getFormattedMaterialName(item.getType()));
        }
    }

    public static String getFormattedMaterialName(@NotNull Material type) {
        var builder = new StringBuilder();
        var words = type.name().toLowerCase().split("_");
        for (String word : words) {
            var chars = word.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            builder.append(String.valueOf(chars)).append(" ");
        }
        return builder.toString().trim();
    }
}
